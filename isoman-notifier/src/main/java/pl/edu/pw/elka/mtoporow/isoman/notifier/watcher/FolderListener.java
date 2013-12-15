package pl.edu.pw.elka.mtoporow.isoman.notifier.watcher;

/**
 * Słuchacz zmian w katalogach
 * Data utworzenia: 06.12.13, 12:55
 *
 * @author Michał Toporowski
 */
public interface FolderListener {

    /**
     * Zdarzenie zmiany w katalogu
     *
     * @param eventType typ zdarzenia
     * @param path ścieżka zmienionego pliku/katalogu
     * @param fsid identyfikator systemowy (tylko w przypadku FOLDER_CREATED)
     */
    void eventFired(final FolderEventType eventType, final String path, final Long fsid);
}
