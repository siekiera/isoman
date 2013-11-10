package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Implementacja Dao dla archiwów
 * Data utworzenia: 10.11.13, 19:14
 *
 * @author Michał Toporowski
 */
public class ArchiwumDaoBean extends AbstractGenericUniqueDao<Archiwum, Long> implements ArchiwumDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    protected ArchiwumDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }
}
