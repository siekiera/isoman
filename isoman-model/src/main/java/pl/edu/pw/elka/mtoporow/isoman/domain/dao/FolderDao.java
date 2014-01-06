package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;

/**
 * Dao encji folderu
 * Data utworzenia: 06.12.13, 17:29
 *
 * @author Michał Toporowski
 */
public interface FolderDao extends BaseUniqueDao<Folder, Long> {

    /**
     * Pobiera folder po fsId
     *
     * @param fsid
     * @return
     */
    Folder getByFsid(final Long fsid);

    /**
     * Usuwa folder i wszystkie jego podfoldery
     *
     * @param folder
     */
    void deleteTree(final Folder folder);
}
