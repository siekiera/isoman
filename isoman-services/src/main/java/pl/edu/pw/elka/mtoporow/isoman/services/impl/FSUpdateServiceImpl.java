package pl.edu.pw.elka.mtoporow.isoman.services.impl;

import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.FolderDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.PlikDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.WersjaArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Plik;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.WersjaArchiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.id.WersjaArchiwumId;
import pl.edu.pw.elka.mtoporow.isoman.generator.ArchiveGenerator;
import pl.edu.pw.elka.mtoporow.isoman.generator.GeneratorException;
import pl.edu.pw.elka.mtoporow.isoman.services.FSUpdateService;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.ArchiveReport;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.FSReport;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementacja serwisu do aktualizacji struktury systemu plików
 * Data utworzenia: 18.12.13, 11:12
 *
 * @author Michał Toporowski
 */
public class FSUpdateServiceImpl implements FSUpdateService {

    private final ArchiwumDao archiwumDao;
    private final WersjaArchiwumDao wersjaArchiwumDao;
    private final FolderDao folderDao;
    private final PlikDao plikDao;
    private final ArchiveGenerator archiveGenerator;

    public FSUpdateServiceImpl(ArchiwumDao archiwumDao, WersjaArchiwumDao wersjaArchiwumDao, FolderDao folderDao, PlikDao plikDao, ArchiveGenerator archiveGenerator) {
        this.archiwumDao = archiwumDao;
        this.wersjaArchiwumDao = wersjaArchiwumDao;
        this.folderDao = folderDao;
        this.plikDao = plikDao;
        this.archiveGenerator = archiveGenerator;
    }


    @Override
    public List<FSReport> reportUpdates(Archiwum archiwum, final boolean includeNotChanged) {
        FSUpdateReporter reporter = new FSUpdateReporter(archiwum, includeNotChanged);
        return reporter.getReportList();
    }

    @Override
    public List<ArchiveReport> reportUpdatedArchives() {
        List<ArchiveReport> archiveReports = new ArrayList<>();
        List<Archiwum> archiwa = archiwumDao.getAll();
        if (archiwa != null) {
            for (Archiwum archiwum : archiwa) {
                FSUpdateReporter reporter = new FSUpdateReporter(archiwum, false);
                archiveReports.add(reporter.getArchiveReport());
            }
        }
        return archiveReports;
    }

    @Override
    public WersjaArchiwum update(final long archiveId, boolean switchVersions) throws ServiceException {
        Archiwum archiwum = archiwumDao.getById(archiveId);
        WersjaArchiwum wersjaArchiwum = wersjaArchiwumDao.getLast(archiwum.getId());
        long nextVer;
        if (wersjaArchiwum == null) {
            switchVersions = false;
            nextVer = 0;
        } else {
            nextVer = (wersjaArchiwum.getId().getNr()) + 1;
        }
        //generuj nowe archiwum
        String nextLoc;
        try {
            nextLoc = archiveGenerator.generate(archiwum.getFolderGlowny().getNazwa(), nextVer);
        } catch (GeneratorException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        //zapisz
        WersjaArchiwum newVersion = newArchiveVersion(archiwum, nextVer, nextLoc, switchVersions);
        //zaktualizuj stukturę katalogów
        updateFolder(archiwum.getFolderGlowny());
        //zapisz
        wersjaArchiwumDao.save(wersjaArchiwum);
        return newVersion;
    }

    /**
     * Aktualizuje folder i jego podfoldery
     *
     * @param folder folder do aktualizacji
     */
    private void updateFolder(Folder folder) {
        List<Folder> newChildren = new ArrayList<>();
        List<Plik> newFiles = new ArrayList<>();
        for (Folder child : folder.getPodrzedne()) {
            if (!child.getDoUsuniecia()) {
                updateFolder(child);
                child.setAktualny(true);
                newChildren.add(child);
            } else {
                folderDao.deleteTree(child);
            }
        }
        for (Plik child : folder.getPliki()) {
            if (!child.getDoUsuniecia()) {
                child.setAktualny(true);
                newFiles.add(child);
            } else {
                plikDao.delete(child);
            }
        }
        folder.setPodrzedne(newChildren);
        folder.setPliki(newFiles);
    }

    /**
     * Tworzy nową wersję archiwum
     *
     * @param archiwum
     * @param nextVer
     * @param nextLoc
     * @param switchVersion
     * @return
     */
    private WersjaArchiwum newArchiveVersion(Archiwum archiwum, long nextVer, String nextLoc, boolean switchVersion) {
        WersjaArchiwum wersjaArchiwum = new WersjaArchiwum();
        wersjaArchiwum.setId(new WersjaArchiwumId(nextVer, archiwum.getId()));
        wersjaArchiwum.setArchiwum(archiwum);
        wersjaArchiwum.setDataUtworzenia(new Date());
        wersjaArchiwum.setSciezka(nextLoc);
        wersjaArchiwum.setPokazywana(switchVersion);

        //zaznacz starą wersję jako nieaktywną
        if (switchVersion) {
            WersjaArchiwum current = wersjaArchiwumDao.getCurrent(archiwum.getId());
            current.setPokazywana(false);
        }

        //dodaj
//        archiwum.getWersje().add(wersjaArchiwum);
        return wersjaArchiwum;
    }
}
