package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.JODao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.JednostkaOrganizacyjna;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Implementacja Dao dla jednostki organizacyjnej
 * Data utworzenia: 10.11.13, 19:18
 *
 * @author Michał Toporowski
 */
public class JODaoBean extends AbstractGenericUniqueDao<JednostkaOrganizacyjna, Long> implements JODao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    protected JODaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }
}
