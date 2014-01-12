package pl.edu.pw.elka.mtoporow.isoman.modules.views.subject;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.BuildException;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Przedmiot;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractView;
import pl.edu.pw.elka.mtoporow.isoman.security.AuthenticationChecker;

import java.util.Collection;

/**
 * Indeks przedmiotów dostępnych dla użytkownika
 * Data utworzenia: 11.01.14, 12:57
 *
 * @author Michał Toporowski
 */
@AccessConditions(
        @AccessCondition(permissions = {Rights.VIEW_SUBJECTS})
)
public class UserSubjectIndex extends AbstractView {
    private static final Logger logger = Logger.getLogger(SubjectIndex.class);

    private final AuthenticationChecker authenticationChecker;

    public UserSubjectIndex(Context context, AuthenticationChecker authenticationChecker) {
        super(context);
        this.authenticationChecker = authenticationChecker;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        final TemplatingContext templatingContext = getTemplatingContext();
        final Osoba loggedPerson = authenticationChecker.getLoggedPerson(context);
        if (loggedPerson != null) {
            Collection<Przedmiot> subjects = loggedPerson.getPrzedmioty();
            logger.debug("Obtained: " + subjects);
            putIfNotEmpty(templatingContext, "subjects", subjects);
        }
        return super.build(template, embeddedBuildResults);
    }
}
