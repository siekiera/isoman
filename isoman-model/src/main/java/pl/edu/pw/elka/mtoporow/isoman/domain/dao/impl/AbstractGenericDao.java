package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.BaseDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.GenericEntity;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * Generyczna implementacja dao
 *
 * @author Michał Toporowski
 */
public abstract class AbstractGenericDao<T extends GenericEntity> implements BaseDao<T> {
    private final Context context;
    private final SessionFactory sessionFactory;

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
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String queryString = "SELECT e FROM " + getEntityClass().getSimpleName() + "  e";
        Query query = getSession().createQuery(queryString);
        return query.list();
    }

    @Override
    public Transaction beginTransaction() {
        return getSession().beginTransaction();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getFiltered(List<Criterion> criteria) {
        Criteria hbCriteria = getSession().createCriteria(getEntityClass());
        for (Criterion criterion : criteria) {
            hbCriteria.add(criterion);
        }
        return hbCriteria.list();
    }

    @Override
    public List<T> getFiltered(Criterion... criteria) {
        return getFiltered(Arrays.asList(criteria));
    }

    @Override
    public void clearSession() {
        getSession().clear();
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
     * Tworzy kryteria dla klasy
     *
     * @return
     */
    protected Criteria createCriteria() {
        return getSession().createCriteria(getEntityClass());
    }

    /**
     * Wyciąga listę z kryteriów
     *
     * @param criteria kryteria
     * @return lista wyników
     */
    @SuppressWarnings("unchecked")
    protected List<T> evaluateCriteria(final Criteria criteria) {
        return criteria.list();
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
