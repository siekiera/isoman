package pl.edu.pw.elka.mtoporow.isoman.modules.components;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.BuildException;
import org.objectledge.web.mvc.components.AbstractComponent;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.security.AuthenticationChecker;

/**
 * Komponent wyświetlający informacje o zalogowanym użytkowniku
 * Data utworzenia: 11.01.14, 11:53
 *
 * @author Michał Toporowski
 */
public class LoginInfo extends AbstractComponent {

    private final AuthenticationChecker authenticationChecker;

    /**
     * Constructs a component instance.
     *
     * @param context               application context for use by this component.
     * @param authenticationChecker
     */
    public LoginInfo(Context context, AuthenticationChecker authenticationChecker) {
        super(context);
        this.authenticationChecker = authenticationChecker;
    }

    @Override
    public String build(Template template) throws BuildException, ProcessingException {
        final TemplatingContext tc = TemplatingContext.getTemplatingContext(context);
        Osoba osoba = authenticationChecker.getLoggedPerson(context);
        if (osoba != null) {
            tc.put("person", osoba);
            tc.put("role", osoba.getRola().getKod());
        } else {
            String user = authenticationChecker.getLoggedLedgeUser(context);
            if (user != null) {
                tc.put("role", user);
            }
        }
        return super.build(template);
    }
}
