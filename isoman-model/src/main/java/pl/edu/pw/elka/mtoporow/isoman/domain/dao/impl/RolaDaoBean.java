package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.RolaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Rola;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Implementacja dao dla roli
 * Data utworzenia: 26.10.13, 21:57
 *
 * @author Michał Toporowski
 */
public class RolaDaoBean extends AbstractGenericUniqueDao<Rola, Long> implements RolaDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    public RolaDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }
}
