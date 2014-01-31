package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;

/**
 * Dao dla osoby
 *
 * @author Michał Toporowski
 */
public interface OsobaDao extends BaseUniqueDao<Osoba, Long> {

    /**
     * Pobiera osobę po nazwie
     *
     * @param name
     */
    public Osoba getByLogin(final String name);

}
