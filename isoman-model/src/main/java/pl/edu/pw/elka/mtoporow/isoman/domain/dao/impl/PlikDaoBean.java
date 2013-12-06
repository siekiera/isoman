package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.PlikDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Plik;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Implementacja dao dla plików
 * Data utworzenia: 06.12.13, 17:32
 *
 * @author Michał Toporowski
 */
public class PlikDaoBean extends AbstractGenericUniqueDao<Plik, Long> implements PlikDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    public PlikDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }
}
