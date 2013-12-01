package pl.edu.pw.elka.mtoporow.isoman.modules.actions.security.authentication;

import org.jcontainer.dna.Logger;
import org.objectledge.authentication.PasswordDigester;
import org.objectledge.context.Context;
import org.objectledge.modules.actions.security.authentication.BaseAuthenticationAction;
import org.objectledge.parameters.Parameters;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.security.SecurityContext;
import org.objectledge.security.SecurityManager;
import org.objectledge.security.exception.LoginFailException;
import org.objectledge.security.exception.UnknownEntityException;
import org.objectledge.security.exception.WrongPasswordException;
import org.objectledge.security.object.SecurityUser;
import org.objectledge.security.util.SecurityUtils;
import org.objectledge.web.HttpContext;
import org.objectledge.web.mvc.MVCContext;
import org.objectledge.web.mvc.ProcessingStage;
import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;

import javax.servlet.http.HttpSession;

/**
 * Akcja logowania dla ISOMana
 * Data utworzenia: 25.11.13, 21:44
 *
 * @author Michał Toporowski
 */
public class IsomanLogin extends BaseAuthenticationAction implements Valve {

    private final OsobaDao osobaDao;
    private final PasswordDigester passwordDigester;
    private final ConfigManager configManager;

    /**
     * Action constructor.
     *
     * @param logger           the logger.
     * @param securityManager  the user manager.
     * @param osobaDao
     * @param passwordDigester
     * @param configManager
     */
    public IsomanLogin(Logger logger, SecurityManager securityManager, OsobaDao osobaDao, PasswordDigester passwordDigester, ConfigManager configManager) {
        super(logger, securityManager);
        this.osobaDao = osobaDao;
        this.passwordDigester = passwordDigester;
        this.configManager = configManager;
    }

    @Override
    public void process(Context context) throws ProcessingException {

        final Parameters parameters = RequestParameters.getRequestParameters(context);
        final String login = parameters.get(LOGIN_PARAM, null);
        final String password = parameters.get(PASSWORD_PARAM, null);
        parameters.remove(LOGIN_PARAM);
        parameters.remove(PASSWORD_PARAM);

        checkLoginAndPassword(login, password);

        Osoba osoba = osobaDao.getByLogin(login);
        //TODO:: lepsze wykrywanie błędów

        String securityLogin, securityPass;

        //brak zdefiniowanej osoby w Isomanie - próbuj bezpośrednio zalogować
        if (osoba == null) {
            securityLogin = login;
            securityPass = password;
        } else {
            if (osoba.getRola() == null) {
                throw new LoginFailException(login);
            }
            if (!verifyPassword(osoba, password)) {
                throw new WrongPasswordException(login);
            }

            //przeszliśmy weryfikację Isomana - próbujemy się zalogować jako użytkownik SB Ledge'a z kodem roli jako loginem
            securityLogin = osoba.getRola().getKod();
            securityPass = configManager.get("login.security.user.password");
        }
        loginSecurityUser(securityLogin, securityPass, context);
    }

    /**
     * Weryfikuje hasło użytkownika
     *
     * @param osoba
     * @param password
     * @return
     */
    private boolean verifyPassword(final Osoba osoba, final String password) {
        return osoba.getHaslo() != null && osoba.getHaslo().equals(passwordDigester.digestPassword(password));
    }

    /**
     * Loguje użytkownika systemu bezpieczeństwa Ledge'a
     *
     * @param login
     * @param password
     * @param context
     * @throws ProcessingException
     */
    private void loginSecurityUser(final String login, final String password, Context context) throws ProcessingException {
        final HttpContext httpContext = HttpContext.getHttpContext(context);
        final HttpSession session = httpContext.getRequest().getSession();
        final SecurityUser anonymous = getAnonymousAccount();

        SecurityUser user = null;
        boolean authenticated;
        LoginFailException exception = null;
        try {
            user = securityManager.getUserByName(login);
            checkUserAuthentication(login, password, user);
            SecurityUtils.clearSession(session);
            authenticated = true;
        } catch (UnknownEntityException e) {
            logger.debug("unknown username " + login);
            user = anonymous;
            authenticated = false;
            exception = new WrongPasswordException(login);
        } catch (LoginFailException e) {
            user = anonymous;
            authenticated = false;
            exception = e;
        }

        final SecurityContext securityContext =
                securityManager.createSecurityContext(user, authenticated);

        try {
            session.setAttribute(SecurityManager.SECURITY_CONTEXT_SESSION_KEY, securityContext);
            session.setMaxInactiveInterval(user.getMaxInactiveInterval());
            context.setAttribute(SecurityContext.class, securityContext);
            if (authenticated) {
                securityManager.setUserLastLogin(user);
            }
        } catch (Exception e) {
            throw new ProcessingException(e);
        }

        if (exception != null) {
            throw exception;
        }

        MVCContext mvcContext = MVCContext.getMVCContext(context);
        mvcContext.setStage(ProcessingStage.POST_AUTHENTICATION);
    }
}
