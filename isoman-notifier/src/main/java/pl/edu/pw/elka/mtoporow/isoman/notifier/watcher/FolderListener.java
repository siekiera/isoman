package pl.edu.pw.elka.mtoporow.isoman.notifier.watcher;

import java.nio.file.Path;

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
    void created(final Path path);

    /**
     * Zdarzenie zmiany pliku
     *
     * @param path
     */
    void changed(final Path path);

    /**
     * Zdarzenie usunięcia pliku
     *
     * @param path
     */
    void deleted(final Path path);

}
