package pl.edu.pw.elka.mtoporow.isoman.ol.extension;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.templating.TemplatingContext;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.BaseDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.GenericEntity;

public abstract class AbstractAction implements Valve {
    protected RequestParameters getRequestParameters(Context context) {
        return RequestParameters.getRequestParameters(context);
    }

    protected Session getSession(Context context) {
        return HibernateSessionContext.getHibernateSessionContext(context).getSession();
    }

    protected TemplatingContext getTemplatingContext(Context context) {
        return TemplatingContext.getTemplatingContext(context);
    }

    /**
     * Przeprowadza operację zapisu encji
     *
     * @param dao
     * @param entity
     */
    protected void processSave(final BaseDao dao, final GenericEntity entity) throws ProcessingException {
        Transaction tx = null;
        try {
            tx = dao.beginTransaction();
            dao.save(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new ProcessingException(e);
        }
    }
}
