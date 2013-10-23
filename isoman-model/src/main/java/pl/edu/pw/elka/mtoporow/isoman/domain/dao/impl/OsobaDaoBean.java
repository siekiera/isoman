package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

import java.util.List;

/**
 * Implementacja dao dla osoby
 *
 * @author Michał Toporowski
 */
public class OsobaDaoBean extends AbstractGenericUniqueDao<Osoba, Long> implements OsobaDao {
    private static final Logger logger = Logger.getLogger(OsobaDaoBean.class);

    public OsobaDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }

    @Deprecated
    public Osoba getById(Long id) {
        return (Osoba) getSession().get(Osoba.class, id);
    }

    @Deprecated
    public List<Osoba> getAllOsobas() {
        String queryString = "SELECT u FROM Osoba u";
        Query query = getSession().createQuery(queryString);
        return query.list();
    }

    public void deleteById(Long id) {
        String queryString = "DELETE FROM Osoba u WHERE u.nr = " + id;
        Query query = getSession().createQuery(queryString);
        query.executeUpdate();
    }
}