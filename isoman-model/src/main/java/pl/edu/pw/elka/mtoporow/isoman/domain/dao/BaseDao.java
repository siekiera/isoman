package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.GenericEntity;

import java.util.List;

/**
 * Interfejs dla wszystkich DAO
 */
public interface BaseDao<T extends GenericEntity> {


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

    /**
     * Pobiera listę wyników przefiltrowaną na podstawie kryteriów hibernate'owych
     *
     * @param criteria lista kryteriów
     * @return
     */
    List<T> getFiltered(List<Criterion> criteria);

    /*
     * Pobiera listę wyników przefiltrowaną na podstawie kryteriów hibernate'owych
     *
     * @param criteria lista kryteriów
     * @return
     */
    List<T> getFiltered(Criterion... criteria);

}
