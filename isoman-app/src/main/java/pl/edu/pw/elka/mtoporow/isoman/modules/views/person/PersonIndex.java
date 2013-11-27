package pl.edu.pw.elka.mtoporow.isoman.modules.views.person;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.BuildException;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractView;

import java.util.List;

/**
 * Indeks osób
 *
 * @author Michał Toporowski
 */
@AccessConditions({
        @AccessCondition(permissions = {Rights.MANAGE_PERSONS})
})
public class PersonIndex extends AbstractView {

    private static final Logger logger = Logger.getLogger(PersonIndex.class);

    private OsobaDao osobaDao;

    /**
     * Konstruktor
     *
     * @param context
     */
    public PersonIndex(Context context, OsobaDao osobaDao) {
        super(context);
        this.osobaDao = osobaDao;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        TemplatingContext templatingContext = getTemplatingContext();
        List<Osoba> users = osobaDao.getAll();
        logger.info("Obtained: " + users);
        templatingContext.put("persons", users);
        return super.build(template, embeddedBuildResults);
    }
}
