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
    @Override
    public Session getSession(Context context) {
        return new Configuration().configure().buildSessionFactory().openSession();
    }
}
