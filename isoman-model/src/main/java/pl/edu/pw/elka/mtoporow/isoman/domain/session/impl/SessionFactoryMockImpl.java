package pl.edu.pw.elka.mtoporow.isoman.domain.session.impl;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;


/**
 * Implementacja SessionFactory do testów BD
 *
 * @author Michał Toporowski
 */
public class SessionFactoryMockImpl implements SessionFactory {
    private Session session;

    @Override
    public Session getSession(Context context) {
        if (session == null || !session.isOpen()) {
            session = new Configuration().configure().buildSessionFactory().openSession();
        }
        return session;
    }
}
