package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.PrzedmiotDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Przedmiot;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Implementacja dao dla przedmiotu
 * Data utworzenia: 10.11.13, 19:05
 *
 * @author Michał Toporowski
 */
public class PrzedmiotDaoBean extends AbstractGenericUniqueDao<Przedmiot, Long> implements PrzedmiotDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    protected PrzedmiotDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }
}
