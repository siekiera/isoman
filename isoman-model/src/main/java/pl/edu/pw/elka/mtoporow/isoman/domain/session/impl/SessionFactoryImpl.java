package pl.edu.pw.elka.mtoporow.isoman.domain.session.impl;

import org.hibernate.Session;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

/**
 * Implementacja SessionFactory wykorzystująca mechanizm ledge'a
 *
 * @author Michał Toporowski
 */
public class SessionFactoryImpl implements SessionFactory {

    @Override
    public Session getSession(Context context) {
        return HibernateSessionContext.getHibernateSessionContext(context).getSession();
    }
}
