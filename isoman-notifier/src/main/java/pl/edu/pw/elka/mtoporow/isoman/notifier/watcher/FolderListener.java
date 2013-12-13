package pl.edu.pw.elka.mtoporow.isoman.notifier.watcher;

/**
 * Słuchacz zmian w katalogach
 * Data utworzenia: 06.12.13, 12:55
 *
 * @author Michał Toporowski
 */
public interface FolderListener {

    /**
     * Zdarzenie dodania pliku
     *
     * @param path
     */
    void fileCreated(final String path);

    /**
     * Zdarzenie zmiany pliku
     *
     * @param path
     */
    void fileChanged(final String path);

    /**
     * Zdarzenie usunięcia pliku
     *
     * @param path
     */
    void fileDeleted(final String path);

    /**
     * Zdarzenie dodania katalogu
     *
     * @param path ścieżka
     * @param fsid identyfikator systemowy (inode pod unixem)
     */
    void folderCreated(final String path, final Long fsid);

    /**
     * Zdarzenie zmiany katalogu
     *
     * @param path ścieżka
     */
    void folderChanged(final String path);

    /**
     * Zdarzenie usunięcia katalogu
     *
     * @param path
     */
    void folderDeleted(final String path);

}
