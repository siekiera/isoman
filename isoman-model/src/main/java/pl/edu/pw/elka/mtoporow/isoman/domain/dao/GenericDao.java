package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import org.hibernate.Transaction;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.GenericEntity;

import java.util.List;

/**
 * Interfejs dla wszystkich DAO
 */
public interface GenericDao<T extends GenericEntity> {


    /**
     * Zapisuje encję
     *
     * @param entity
     */
    void save(T entity);

    /**
     * Aktualizuje encję
     *
     * @param entity
     */
    void update(T entity);

    /**
     * Aktualizuje lub zapisuje encję
     *
     * @param entity
     */
    void saveOrUpdate(T entity);

    /**
     * Usuwa encję
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * Pobiera listę wszystkich encji danego typu
     *
     * @return lista encji
     */
    List<T> getAll();

    /**
     * Rozpoczyna transakcję dla bieżącej sesji
     *
     * @return
     */
    Transaction beginTransaction();
}
