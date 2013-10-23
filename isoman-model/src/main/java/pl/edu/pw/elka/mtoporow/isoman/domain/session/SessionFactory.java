package pl.edu.pw.elka.mtoporow.isoman.domain.session;

import org.hibernate.Session;
import org.objectledge.context.Context;

/**
 * Interfejs zwracający sesję hibernate
 *
 * @author Michał Toporowski
 */
public interface SessionFactory {
    /**
     * Zwraca sesję hibernate na podstawie kontekstu
     *
     * @return sesja hibernate
     */
    abstract Session getSession(Context context);
}
