package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.Query;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.GenericUniqueDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.UniqueEntity;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Generyczna implementacja Dao dla encji identyfikowalnych
 *
 * @author Michał Toporowski
 */
public abstract class AbstractGenericUniqueDao<T extends UniqueEntity<ID>, ID>
        extends AbstractGenericDao<T>
        implements GenericUniqueDao<T, ID> {

    /**
     * Konstruktor klasy abstrakcyjnej
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    protected AbstractGenericUniqueDao(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }


    @Override
    public T getById(ID id) {
        String queryString = String.format("SELECT e FROM %s e WHERE e.id=:id", getEntityClass().getSimpleName());
        Query query = getSession().createQuery(queryString);
        query.setParameter("id", id);
        return (T) query.uniqueResult();
    }
}
