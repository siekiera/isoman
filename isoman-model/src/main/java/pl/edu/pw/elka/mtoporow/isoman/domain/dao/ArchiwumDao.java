package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;

/**
 * Dao dla archiwum
 * Data utworzenia: 10.11.13, 19:10
 *
 * @author Michał Toporowski
 */
public interface ArchiwumDao extends BaseUniqueDao<Archiwum, Long> {

    /**
     * Pobiera archiwum na podstawie folderu głównego
     *
     * @param rootPath
     * @return archiwum, null jeśli nie znaleziono
     */
    Archiwum getByRootFolder(final String rootPath);
}
