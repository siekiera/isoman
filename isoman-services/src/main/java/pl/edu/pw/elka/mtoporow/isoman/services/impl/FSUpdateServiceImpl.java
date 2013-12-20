package pl.edu.pw.elka.mtoporow.isoman.services.impl;

import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Plik;
import pl.edu.pw.elka.mtoporow.isoman.services.FSUpdateService;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.ArchiveReport;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.FSReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacja serwisu do aktualizacji struktury systemu plików
 * Data utworzenia: 18.12.13, 11:12
 *
 * @author Michał Toporowski
 */
public class FSUpdateServiceImpl implements FSUpdateService {

    private final ArchiwumDao archiwumDao;

    public FSUpdateServiceImpl(ArchiwumDao archiwumDao) {
        this.archiwumDao = archiwumDao;
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
    public void update(Archiwum archiwum) {
        updateFolder(archiwum.getFolderGlowny());
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
            }
        }
        for (Plik child : folder.getPliki()) {
            if (!child.getDoUsuniecia()) {
                child.setAktualny(true);
                newFiles.add(child);
            }
        }
        folder.setPodrzedne(newChildren);
        folder.setPliki(newFiles);
    }


}
