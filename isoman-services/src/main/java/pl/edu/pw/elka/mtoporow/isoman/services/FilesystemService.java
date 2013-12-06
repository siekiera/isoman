package pl.edu.pw.elka.mtoporow.isoman.services;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

/**
 * Serwis do zarządzania encjami związanymi z systemem plików
 * Data utworzenia: 06.12.13, 17:24
 *
 * @author Michał Toporowski
 */
public interface FilesystemService {

    /**
     * Pobiera encję folderu na podstawie ścieżki
     *
     * @param path ścieżka
     * @return encja folderu lub null, jeśli nie znaleziono
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    Folder getFolderByPath(final String path) throws ServiceException;

    /**
     * Pobiera encję folderu na podstawie ścieżki, lub tworzy ją, jeśli nie istnieje
     *
     * @param path ścieżka
     * @return encja folderu
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    Folder getOrCreateFolderByPath(final String path) throws ServiceException;
}
