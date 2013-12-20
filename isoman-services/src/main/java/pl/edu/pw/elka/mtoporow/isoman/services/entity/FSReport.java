package pl.edu.pw.elka.mtoporow.isoman.services.entity;

/**
 * Raport zmian dla danego archiwum
 * Data utworzenia: 15.12.13, 19:51
 *
 * @author Michał Toporowski
 */
public class FSReport {
    private String archiveName;
    private String pathInArchive;
    private boolean isDirectory;
    private boolean forRemoval;
    private boolean forUpdate;

    public FSReport(String archiveName, String pathInArchive, boolean isDirectory, boolean forRemoval, boolean forUpdate) {
        this.archiveName = archiveName;
        this.pathInArchive = pathInArchive;
        this.isDirectory = isDirectory;
        this.forRemoval = forRemoval;
        this.forUpdate = forUpdate;
    }
}
