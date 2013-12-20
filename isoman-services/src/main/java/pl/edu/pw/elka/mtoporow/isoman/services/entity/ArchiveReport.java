package pl.edu.pw.elka.mtoporow.isoman.services.entity;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;

/**
 * Raport aktualizacji dla archiwum
 * Data utworzenia: 18.12.13, 11:09
 *
 * @author Michał Toporowski
 */
public class ArchiveReport {
    private Archiwum archiwum;
    private int modifiedFiles;
    private int deletedFiles;
    private int modifiedFolders;
    private int deletedFolders;

    public ArchiveReport(Archiwum archiwum, int modifiedFiles, int deletedFiles, int modifiedFolders, int deletedFolders) {
        this.archiwum = archiwum;
        this.modifiedFiles = modifiedFiles;
        this.deletedFiles = deletedFiles;
        this.modifiedFolders = modifiedFolders;
        this.deletedFolders = deletedFolders;
    }

    public Archiwum getArchiwum() {
        return archiwum;
    }

    public int getModifiedFiles() {
        return modifiedFiles;
    }

    public int getDeletedFiles() {
        return deletedFiles;
    }

    public int getModifiedFolders() {
        return modifiedFolders;
    }

    public int getDeletedFolders() {
        return deletedFolders;
    }
}
