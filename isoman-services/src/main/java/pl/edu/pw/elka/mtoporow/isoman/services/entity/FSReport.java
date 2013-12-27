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

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public String getPathInArchive() {
        return pathInArchive;
    }

    public void setPathInArchive(String pathInArchive) {
        this.pathInArchive = pathInArchive;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public boolean isForRemoval() {
        return forRemoval;
    }

    public void setForRemoval(boolean forRemoval) {
        this.forRemoval = forRemoval;
    }

    public boolean isForUpdate() {
        return forUpdate;
    }

    public void setForUpdate(boolean forUpdate) {
        this.forUpdate = forUpdate;
    }
}
