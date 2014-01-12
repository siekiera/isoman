package pl.edu.pw.elka.mtoporow.isoman.modules.views.archive;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.web.mvc.builders.BuildException;
import pl.edu.pw.elka.mtoporow.isoman.constants.ParameterConstants;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractView;
import pl.edu.pw.elka.mtoporow.isoman.security.AuthenticationChecker;

import java.util.List;

/**
 * Lista archiwów/podręczników dostępnych użytkownikowi
 * Data utworzenia: 11.01.14, 14:54
 *
 * @author Michał Toporowski
 */
@AccessConditions(
        @AccessCondition(permissions = {Rights.VIEW_ARCHIVES})
)
public class UserArchivesIndex extends AbstractView {

    private final AuthenticationChecker authenticationChecker;
    private final ArchiwumDao archiwumDao;

    /**
     * Konstruuje widok
     *
     * @param context               kontekst Ledge'a
     * @param authenticationChecker
     * @param archiwumDao
     */
    public UserArchivesIndex(Context context, AuthenticationChecker authenticationChecker, ArchiwumDao archiwumDao) {
        super(context);
        this.authenticationChecker = authenticationChecker;
        this.archiwumDao = archiwumDao;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        final RequestParameters requestParameters = getRequestParameters();
        final Osoba loggedPerson = authenticationChecker.getLoggedPerson(context);
        List<Archiwum> archives;
        if (requestParameters.isDefined(ParameterConstants.SUBJECT_ID)) {
            Long subjectId = requestParameters.getLong(ParameterConstants.SUBJECT_ID);
            archives = archiwumDao.getUserArchives(loggedPerson.getId(), subjectId);
        } else {
            archives = archiwumDao.getUserArchives(loggedPerson.getId());
        }
        getTemplatingContext().put("archives", archives);
        return super.build(template, embeddedBuildResults);
    }
}
