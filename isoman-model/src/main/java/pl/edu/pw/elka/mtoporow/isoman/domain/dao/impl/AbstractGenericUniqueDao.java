package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.BaseUniqueDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.UniqueEntity;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

import java.util.List;

/**
 * Generyczna implementacja Dao dla encji identyfikowalnych
 *
 * @author Michał Toporowski
 */
public abstract class AbstractGenericUniqueDao<T extends UniqueEntity<ID>, ID>
        extends AbstractGenericDao<T>
        implements BaseUniqueDao<T, ID> {

    /**
     * Konstruktor
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


    @Override
    public List<T> getByExample(final T entity) {
        final Example example = Example.create(entity)
                .ignoreCase()
                .enableLike(MatchMode.START);
        return evaluateCriteria(createCriteria().add(example));
    }
}
