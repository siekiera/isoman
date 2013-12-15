package pl.edu.pw.elka.mtoporow.isoman.services.impl;

import org.hibernate.envers.tools.Pair;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.FolderDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.FSEntity;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Plik;
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
        return getFolderByPath(path, false, null);
    }

    @Override
    public Folder getOrCreateFolderByPath(String path, final Long fsid) throws ServiceException {
        return getFolderByPath(path, true, fsid);
    }

    @Override
    public Plik getFileByPath(String path) throws ServiceException {
        return getFileByPath(path, false);
    }

    @Override
    public Plik getOrCreateFileByPath(String path) throws ServiceException {
        return getFileByPath(path, true);
    }

    @Override
    public Plik markFile(String path) throws ServiceException {
        Plik plik = getFileByPath(path, true);
        plik.setAktualny(true);
        return plik;
    }

    @Override
    public Folder markCreatedFolder(String path, Long fsid) throws ServiceException {
        //To wystarczy - patrz createFolder()
        return getOrCreateFolderByPath(path, fsid);
    }

    @Override
    public Folder markChangedFolder(String path) throws ServiceException {
        Folder folder = getFolderByPath(path);
        if (folder == null) {
            throw new ServiceException("Folder not found " + path);
        }
        folder.setAktualny(false);
        return folder;
    }

    @Override
    public FSEntity markAsDeleted(String path) throws ServiceException {
        FSEntity toDelete = getFileByPath(path);
        if (toDelete == null) {
            toDelete = getFolderByPath(path);
        }
        if (toDelete == null) {
            throw new ServiceException(String.format("Cannot delete. File or folder %s not found.", path));
        }
        toDelete.setDoUsuniecia(true);
        return toDelete;
    }

    @Override
    public void clearHibernateSession() {
        folderDao.clearSession();
    }

    /**
     * Pobiera encję pliku na podstawie ścieżki
     *
     * @param path              ścieżka
     * @param createIfNotExists czy tworzyć encję, jeśli nie istnieje
     * @return encja pliku (lub null, jeśli nie znaleziono i createIfNotExists = false)
     * @throws ServiceException
     */
    private Plik getFileByPath(String path, boolean createIfNotExists) throws ServiceException {
        Pair<String, String> extracted = getRestAndLast(path);
        String folderPath = extracted.getFirst();
        String filename = extracted.getSecond();

        Folder folder = getFolderByPath(folderPath, createIfNotExists, null);
        if (folder != null) {
            Plik plik = getFileWithName(folder, filename);
            if (plik == null) {
                if (!createIfNotExists) {
                    return null;
                }
                plik = new Plik(folder, filename, false, false);
                //TODO?? dddać do folderu?
            }
            return plik;
        }
        return null;
    }


    /**
     * Pobiera encję folderu na podstawie ścieżki
     *
     * @param path              ścieżka
     * @param createIfNotExists czy tworzyć encję, jeśli nie istnieje
     * @param fsid              identyfikator systemu plików
     * @return encja folderu (lub null, jeśli nie znaleziono i createIfNotExists = false)
     * @throws ServiceException jeśli archiwum nie istnieje
     */
    private Folder getFolderByPath(String path, boolean createIfNotExists, Long fsid) throws ServiceException {
        Pair<String, String> extPaths = getFirstAndRest(path);

        Archiwum archiwum = archiwumDao.getByRootFolder(extPaths.getFirst());
        if (archiwum != null && archiwum.getFolderGlowny() != null) {
            return getChildFolder(archiwum.getFolderGlowny(), extPaths.getSecond(), createIfNotExists, fsid);
        }
        throw new ServiceException("Archive not found");
    }

    /**
     * Pobiera folder o podanej ścieżce podrzędny względem folderu parent
     *
     * @param parent
     * @param relativePath
     * @param createIfNotExists czy tworzyć encję, jeśli nie udało się znaleźć
     * @param fsid              identyfikator systemu plików
     * @return encja folderu (lub null, jeśli nie udało się znaleźć i createIfNotExists=false)
     */
    private Folder getChildFolder(final Folder parent, final String relativePath, final boolean createIfNotExists, final Long fsid) {
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
            childFolder = createFolder(parent, childName, fsid);
            //TODO ?? parent.getPodrzedne().add(childFolder);
        }
        return getChildFolder(childFolder, restOfPath, createIfNotExists, fsid);
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
     * Tworzy folder o podanych parametrach
     *
     * @param parent
     * @param name
     * @param fsid
     * @return
     */
    private Folder createFolder(final Folder parent, final String name, final Long fsid) {
        //sprawdzamy najpierw, czy nie ma folderu do usunięcia o tym samym FSID
        //dzieje się to, gdy zmieniamy nazwę folderu - wówczas stary jest usuwany, tworzony nowy
        Folder existing = folderDao.getByFsid(fsid);
        if (existing != null && existing.getDoUsuniecia()) {
            existing.setNadrzedny(parent);
            existing.setNazwa(name);
            existing.setDoUsuniecia(false);
            existing.setAktualny(false);
            return existing;
        }
        return new Folder(fsid, name, false, false, parent);
    }

    /**
     * Znajduje wśród dzieci danego folderu plik o podanej nazwie
     *
     * @param parent
     * @param name
     * @return encja folderu lub null, jeśli nie znaleziono
     */
    private Plik getFileWithName(final Folder parent, final String name) {
        if (parent.getPliki() != null) {
            for (Plik plik : parent.getPliki()) {
                if (name.equals(plik.getNazwa())) {
                    return plik;
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
