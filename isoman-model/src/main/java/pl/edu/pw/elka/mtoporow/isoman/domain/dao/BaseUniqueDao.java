package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.UniqueEntity;

import java.util.List;

/**
 * Dao dla encji identyfikowalnych
 *
 * @author Michał Toporowski
 */
public interface BaseUniqueDao<T extends UniqueEntity<IDTYPE>, IDTYPE> extends BaseDao<T> {

    /**
     * Pobiera encję na podstawie id
     *
     * @param id
     * @return
     */
    T getById(IDTYPE id);


    /**
     * Pobiera encję po przykładzie
     *
     * @param osoba
     * @return
     */
    public List<T> getByExample(final T entity);
}
