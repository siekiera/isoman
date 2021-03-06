package pl.edu.pw.elka.mtoporow.isoman.services;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.FSEntity;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Plik;
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
     * @param fsid identyfikator systemu plików
     * @return encja folderu
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    Folder getOrCreateFolderByPath(final String path, final Long fsid) throws ServiceException;

    /**
     * Pobiera encję pliku na podstawie ścieżki
     *
     * @param path ścieżka
     * @return encja pliku lub null, jeśli nie znaleziono
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    Plik getFileByPath(final String path) throws ServiceException;

    /**
     * Pobiera encję pliku na podstawie ścieżki, lub tworzy ją, jeśli nie istnieje
     *
     * @param path ścieżka
     * @return encja pliku
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    Plik getOrCreateFileByPath(final String path) throws ServiceException;

    /**
     * Oznacza plik jako zmieniony.
     * Tworzy go, jeśli nie istnieje
     *
     * @param path
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    Plik markFile(final String path) throws ServiceException;

    /**
     * Oznacza nowoutworzony katalog jako zmieniony
     *
     * @param path
     * @param fsid
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    Folder markCreatedFolder(final String path, final Long fsid) throws ServiceException;

    /**
     * Oznacza katalog jako zmieniony
     *
     * @param path
     * @throws ServiceException jeśli katalog lub archiwum nie istnieje
     */
    Folder markChangedFolder(final String path) throws ServiceException;

    /**
     * Oznacza plik lub katalog jako usunięty
     * Jedna metoda - po usunięciu nie wiemy, co usunęliśmy     *
     *
     * @param path
     * @throws ServiceException jeśli katalog lub archiwum nie istnieje
     */
    FSEntity markAsDeleted(final String path) throws ServiceException;

    /**
     * Czyści sesję Hibernate'a
     */
    void clearHibernateSession();
}
