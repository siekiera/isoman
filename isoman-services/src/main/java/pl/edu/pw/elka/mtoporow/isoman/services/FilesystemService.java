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
     * @param path
     * @return
     */
    Folder getFolderByPath(final String path) throws ServiceException;
}
