package pl.edu.pw.elka.mtoporow.isoman.modules.views.person;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.BuildException;
import pl.edu.pw.elka.mtoporow.isoman.constants.ParameterConstants;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.RolaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Rola;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractView;

import java.util.List;

/**
 * Widok dodawania osoby
 * Data utworzenia: 22.11.13, 10:57
 *
 * @author Michał Toporowski
 */
@AccessConditions({
        @AccessCondition(permissions = {Rights.MANAGE_PERSONS})
})
public class AddPerson extends AbstractView {

    private static final Logger LOGGER = Logger.getLogger(AddPerson.class);

    private final RolaDao rolaDao;
    private final OsobaDao osobaDao;

    public AddPerson(Context context, RolaDao rolaDao, OsobaDao osobaDao) {
        super(context);
        this.rolaDao = rolaDao;
        this.osobaDao = osobaDao;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        final RequestParameters requestParameters = getRequestParameters();
        final TemplatingContext templatingContext = getTemplatingContext();
        if (requestParameters.isDefined(ParameterConstants.PERSON_ID)) {
            //tryb edycji
            Long personId = requestParameters.getLong(ParameterConstants.PERSON_ID);
            Osoba personToEdit = osobaDao.getById(personId);
            putIfNotNull(templatingContext, "person", personToEdit);
        }

        //dodaj listę ról
        List<Rola> availableRoles = rolaDao.getAll();
        LOGGER.trace("build: roles " + availableRoles);

        templatingContext.put("roles", availableRoles);
        return super.build(template, embeddedBuildResults);
    }
}
