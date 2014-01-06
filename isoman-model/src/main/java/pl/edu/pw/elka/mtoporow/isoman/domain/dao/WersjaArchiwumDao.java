package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.WersjaArchiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.id.WersjaArchiwumId;

/**
 * Dao dla wersji archiwum
 * Data utworzenia: 10.11.13, 19:11
 *
 * @author Michał Toporowski
 */
public interface WersjaArchiwumDao extends BaseUniqueDao<WersjaArchiwum, WersjaArchiwumId> {

    /**
     * Pobiera wersję archiwum o największym numerze
     *
     * @param archiwumId
     * @return
     */
    WersjaArchiwum getLast(final Long archiwumId);

    /**
     * Pobiera aktualną wersję archiwum (dla której pokazywany = true)
     *
     * @param archiwumId
     * @return
     */
    WersjaArchiwum getCurrent(final Long archiwumId);
}
