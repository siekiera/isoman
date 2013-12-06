package pl.edu.pw.elka.mtoporow.isoman.services.impl;

import org.hibernate.envers.tools.Pair;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.FolderDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.services.FilesystemService;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

/*
 * Implementacja serwisu do zarządzania encjami związanymi z systemem plików
 * Data utworzenia: 06.12.13, 17:25
 *
 * @author Michał Toporowski
 */
public class FilesystemServiceImpl implements FilesystemService {

    private final ArchiwumDao archiwumDao;
    private final FolderDao folderDao;

    /**
     * konstruktor
     *
     * @param archiwumDao
     * @param folderDao
     */
    public FilesystemServiceImpl(ArchiwumDao archiwumDao, FolderDao folderDao) {
        this.archiwumDao = archiwumDao;
        this.folderDao = folderDao;
    }


    @Override
    public Folder getFolderByPath(String path) throws ServiceException {
        return getFolderByPath(path, false);
    }

    @Override
    public Folder getOrCreateFolderByPath(String path) throws ServiceException {
        return getFolderByPath(path, true);
    }

    /**
     * Pobiera encję folderu na podstawie ścieżki
     *
     * @param path ścieżka
     * @param createIfNotExists czy tworzyć encję, jeśli nie istnieje
     * @return encja folderu (lub null, jeśli nie znaleziono i createIfNotExists = false)
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    private Folder getFolderByPath(String path, boolean createIfNotExists) throws ServiceException {
        Pair<String, String> extPaths = getFirstAndRest(path);

        Archiwum archiwum = archiwumDao.getByRootFolder(extPaths.getFirst());
        if (archiwum != null && archiwum.getFolderGlowny() != null) {
            return getChildFolder(archiwum.getFolderGlowny(), extPaths.getSecond(), createIfNotExists);
        }
        throw new ServiceException("Archive not found");
    }

    /**
     * Pobiera folder o podanej ścieżce podrzędny względem folderu parent
     *
     * @param parent
     * @param relativePath
     * @param createIfNotExists czy tworzyć encję, jeśli nie udało się znaleźć
     * @return encja folderu (lub null, jeśli nie udało się znaleźć i createIfNotExists=false)
     */
    private Folder getChildFolder(final Folder parent, final String relativePath, final boolean createIfNotExists) {
        //koniec rekurencji
        if (relativePath == null || relativePath.isEmpty()) {
            return parent;
        }
        Pair<String, String> extracted = getFirstAndRest(relativePath);
        String childName = extracted.getFirst();
        String restOfPath = extracted.getSecond();

        Folder childFolder = getChildWithName(parent, childName);
        if (childFolder == null) {
            if (!createIfNotExists) {
                return null;
            }
            childFolder = new Folder(childName, parent);
        }
        return getChildFolder(childFolder, restOfPath, createIfNotExists);
    }

    /**
     * Znajduje wśród dzieci danego folderu folder o podanej nazwie
     *
     * @param parent
     * @param name
     * @return encja folderu lub null, jeśli nie znaleziono
     */
    private Folder getChildWithName(final Folder parent, final String name) {
        if (parent.getPodrzedne() != null) {
            for (Folder child : parent.getPodrzedne()) {
                if (name.equals(child.getNazwa())) {
                    return child;
                }
            }
        }
        return null;
    }

    /**
     * Zwraca pierwszą część ścieżki i resztę
     *
     * @param path
     * @return para (pierwsza część, reszta)
     */
    private Pair<String, String> getFirstAndRest(final String path) {
        int index = path.indexOf("/");
        if (index < 0) {
            return Pair.make(path, null);
        }
        return Pair.make(
                path.substring(0, index),
                path.substring(index + 1)
        );
    }

    /**
     * Zwraca resztę i ostatnią część ścieżki
     *
     * @param path
     * @return para (reszta, ostatnia część)
     */
    private Pair<String, String> getRestAndLast(final String path) {
        int index = path.lastIndexOf("/");
        if (index < 0) {
            return Pair.make(null, path);
        }
        return Pair.make(
                path.substring(0, index),
                path.substring(index + 1)
        );
    }
}
