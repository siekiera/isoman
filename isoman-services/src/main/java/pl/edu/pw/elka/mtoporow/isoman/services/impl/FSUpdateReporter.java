package pl.edu.pw.elka.mtoporow.isoman.services.impl;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Plik;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.ArchiveReport;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.FSReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa budująca raport zaktualizowanych plików
 * Data utworzenia: 18.12.13, 11:27
 *
 * @author Michał Toporowski
 */
public class FSUpdateReporter {

    private final Archiwum archiwum;
    private final boolean includeNotChanged;
    private final List<FSReport> reportList;
    private int modifiedFiles = 0;
    private int modifiedFolders = 0;
    private int deletedFiles = 0;
    private int deletedFolders = 0;

    private static final String PATH_DELIMITER = "/";

    /**
     * Konstruktor
     *
     * @param archiwum          archiwum, dla którego będzie zbudowany raport
     * @param includeNotChanged czy uwzględniać niezmienione pliki
     */
    public FSUpdateReporter(Archiwum archiwum, boolean includeNotChanged) {
        this.archiwum = archiwum;
        this.includeNotChanged = includeNotChanged;
        this.reportList = new ArrayList<>();
        addFolderTreeToReport(archiwum.getFolderGlowny(), "", false, false);
    }

    /**
     * Pobiera listę raportów
     *
     * @return lista raportów
     */
    public List<FSReport> getReportList() {
        return reportList;
    }

    /**
     * Tworzy raport dla archiwum
     *
     * @return raport dla archiwum (ArchiveReport)
     */
    public ArchiveReport getArchiveReport() {
        return new ArchiveReport(archiwum, modifiedFiles, deletedFiles, modifiedFolders, deletedFolders);
    }

    /**
     * Dodaje gałąź drzewa katalogów do listy raportów
     *
     * @param folder
     * @param pathInArchive
     */
    private void addFolderTreeToReport(final Folder folder, final String pathInArchive, final boolean parentUpdated, final boolean parentDeleted) {

        //dodaj foldery
        if (folder.getPodrzedne() != null) {
            for (Folder child : folder.getPodrzedne()) {
                boolean forRemoval = child.getDoUsuniecia() || parentDeleted;
                boolean forUpdate = !forRemoval && (!child.getAktualny() || parentUpdated);
                String childPath = pathInArchive + PATH_DELIMITER + child.getNazwa();
                if (includeNotChanged || forRemoval || forUpdate) {
                    reportList.add(new FSReport(archiwum.getNazwa(), childPath, true, forRemoval, forUpdate));
                }
                if (forRemoval) deletedFolders++;
                if (forUpdate) modifiedFolders++;
                //rekurencyjnie dla podfolderu
                addFolderTreeToReport(child, childPath, forUpdate, forRemoval);
            }
        }
        //dodaj pliki
        if (folder.getPliki() != null) {
            for (Plik child : folder.getPliki()) {
                boolean forRemoval = child.getDoUsuniecia();
                boolean forUpdate = !child.getAktualny();
                String childPath = pathInArchive + PATH_DELIMITER + child.getNazwa();
                if (includeNotChanged || forRemoval || forUpdate) {
                    reportList.add(new FSReport(archiwum.getNazwa(), childPath, false, forRemoval, forUpdate));
                }
                if (forRemoval) deletedFiles++;
                if (forUpdate) modifiedFiles++;
            }
        }
    }
}
