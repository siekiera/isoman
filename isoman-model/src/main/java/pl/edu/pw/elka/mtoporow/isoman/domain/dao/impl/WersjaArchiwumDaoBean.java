package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.common.util.CollectionTool;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.WersjaArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.WersjaArchiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.id.WersjaArchiwumId;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

import java.util.List;

/**
 * Implementacja dao dla wersji archiwum
 * Data utworzenia: 10.11.13, 19:17
 *
 * @author Michał Toporowski
 */
public class WersjaArchiwumDaoBean extends AbstractGenericUniqueDao<WersjaArchiwum, WersjaArchiwumId> implements WersjaArchiwumDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    public WersjaArchiwumDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }

    @Override
    public WersjaArchiwum getLast(Long archiwumId) {
        Criteria c = createCriteria();
        c.add(Restrictions.eq("id.idArchiwum", archiwumId));
        c.addOrder(Order.desc("id.nr"));
        return CollectionTool.getFirst((List<WersjaArchiwum>) c.list());
    }

    @Override
    public WersjaArchiwum getCurrent(Long archiwumId) {
        List<WersjaArchiwum> results = getFiltered(
                Restrictions.eq("id.idArchiwum", archiwumId),
                Restrictions.eq("pokazywana", true)
        );
        return CollectionTool.getFirst(results);
    }
}
