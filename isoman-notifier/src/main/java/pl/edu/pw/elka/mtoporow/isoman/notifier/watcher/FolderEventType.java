package pl.edu.pw.elka.mtoporow.isoman.notifier.watcher;

/**
 * Typ zdarzenia zmiany w katalogu
 * Data utworzenia: 13.12.13, 11:56
 *
 * @author Michał Toporowski
 */
public enum FolderEventType {
    FILE_CREATED,
    FILE_CHANGED,
    FILE_DELETED,
    FOLDER_CREATED,
    FOLDER_CHANGED,
    FOLDER_DELETED;
}
