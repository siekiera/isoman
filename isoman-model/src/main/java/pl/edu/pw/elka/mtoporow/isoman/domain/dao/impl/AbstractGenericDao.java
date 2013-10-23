package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.GenericDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.GenericEntity;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Generyczna implementacja dao
 *
 * @author Michał Toporowski
 */
public abstract class AbstractGenericDao<T extends GenericEntity> implements GenericDao<T> {
    private Context context;
    private SessionFactory sessionFactory;

    /**
     * Konstruktor klasy abstrakcyjnej
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    protected AbstractGenericDao(Context context, SessionFactory sessionFactory) {
        this.context = context;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public List<T> getAll() {
        String queryString = "SELECT e FROM " + getEntityClass().getSimpleName() + "  e";
        Query query = getSession().createQuery(queryString);
        return query.list();
    }

    /**
     * Pobiera sesję
     *
     * @return
     */
    protected Session getSession() {
        return sessionFactory.getSession(context);
    }

    /**
     * Pobiera klasę encji
     *
     * @return
     */
    protected Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class) type.getActualTypeArguments()[0];

    }
}
