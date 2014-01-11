package pl.edu.pw.elka.mtoporow.isoman.security;

import org.objectledge.context.Context;
import org.objectledge.security.SecurityContext;
import org.objectledge.web.HttpContext;
import pl.edu.pw.elka.mtoporow.isoman.constants.ParameterConstants;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;

/**
 * Klasa sprawdzająca aktualnie zalogowaną osobę
 * Data utworzenia: 11.01.14, 10:55
 *
 * @author Michał Toporowski
 */
public class AuthenticationChecker {

    private final OsobaDao osobaDao;

    public AuthenticationChecker(OsobaDao osobaDao) {
        this.osobaDao = osobaDao;
    }

    /**
     * Zwraca aktualnie zalogowaną osobę.
     * null, jeśli osoba niezalogowana
     *
     * @param context kontekst Ledge'a
     * @return encja osoby lub null
     */
    public Osoba getLoggedPerson(final Context context) {
        final SecurityContext securityContext = SecurityContext.getSecurityContext(context);
        final HttpContext httpContext = HttpContext.getHttpContext(context);
        //Sprawdź uwierzytelnienie
        if (!securityContext.isUserAuthenticated()) {
            return null;
        }
        //Pobierz osobę
        Object idAttr = httpContext.getSessionAttribute(ParameterConstants.SESSION_PERSON_ID);
        if (idAttr == null || !(idAttr instanceof Long)) {
            return null;
        }
        Osoba osoba = osobaDao.getById((Long) idAttr);
        //Dodatkowa weryfikacja - czy nazwa osoby zgadza się z użytkownikiem systemu bezp. Ledge'a
        if (!(osoba.getRola().getKod().equals(securityContext.getUser().getName()))) {
            return null;
        }
        return osoba;
    }

    /**
     * Zwraca aktualnie zalogowanego użytkownika systemu bezpieczeństwa Ledge
     * null, jeśli osoba niezalogowana
     *
     * @param context kontekst Ledge'a
     * @return nazwa użytkownika lub null
     */
    public String getLoggedLedgeUser(final Context context) {
        final SecurityContext securityContext = SecurityContext.getSecurityContext(context);
        if (securityContext.isUserAuthenticated()) {
            return securityContext.getUser().getName();
        }
        return null;
    }
}
