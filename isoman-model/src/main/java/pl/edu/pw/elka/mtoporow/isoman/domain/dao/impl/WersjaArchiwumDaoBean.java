package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.WersjaArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.WersjaArchiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.id.WersjaArchiwumId;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Implementacja dao dla wersji archiwum
 * Data utworzenia: 10.11.13, 19:17
 *
 * @author Michał Toporowski
 */
public class WersjaArchiwumDaoBean extends AbstractGenericUniqueDao<WersjaArchiwum, WersjaArchiwumId> implements WersjaArchiwumDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    protected WersjaArchiwumDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }
}
