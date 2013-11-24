package pl.edu.pw.elka.mtoporow.isoman.modules.views.person;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.web.mvc.builders.BuildException;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.RolaDao;
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
        @AccessCondition(permissions = {Rights.RIGHT_MANAGE_PERSONS})
})
public class AddPerson extends AbstractView {

    private static final Logger LOGGER = Logger.getLogger(AddPerson.class);

    private final RolaDao rolaDao;

    public AddPerson(Context context, RolaDao rolaDao) {
        super(context);
        this.rolaDao = rolaDao;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        List<Rola> availableRoles = rolaDao.getAll();
        LOGGER.trace("build: roles " + availableRoles);

        getTemplatingContext().put("roles", availableRoles);
        return super.build(template, embeddedBuildResults);
    }
}
