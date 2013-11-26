package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.common.util.CollectionTool;
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
    public List<Osoba> getAllOsobas() {
        String queryString = "SELECT u FROM Osoba u";
        Query query = getSession().createQuery(queryString);
        return query.list();
    }

    @Deprecated
    public void deleteById(Long id) {
        String queryString = "DELETE FROM Osoba u WHERE u.id = " + id;
        Query query = getSession().createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public Osoba getByLogin(String name) {
        List<Osoba> osoby = getFiltered(Restrictions.eq("login", name));
        return CollectionTool.getFirst(osoby);
    }
}
