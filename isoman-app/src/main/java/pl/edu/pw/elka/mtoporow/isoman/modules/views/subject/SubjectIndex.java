package pl.edu.pw.elka.mtoporow.isoman.modules.views.subject;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.BuildException;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.JODao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.JednostkaOrganizacyjna;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Przedmiot;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractView;

import java.util.List;


/**
 * Indeks przedmiotów
 * Data utworzenia: 10.11.13, 21:58
 *
 * @author Michał Toporowski
 */
public class SubjectIndex extends AbstractView {

    private static final Logger logger = Logger.getLogger(SubjectIndex.class);

    private final JODao joDao;

    public SubjectIndex(Context context, JODao joDao) {
        super(context);
        this.joDao = joDao;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        TemplatingContext templatingContext = getTemplatingContext();
        //FIXME::zmienić
        JednostkaOrganizacyjna jo = joDao.getById(5000L);
        List<Przedmiot> subjects = jo.getPrzedmioty();
        logger.info("Obtained: " + subjects);
        templatingContext.put("subjects", subjects);
        templatingContext.put("unitname", jo.getNazwa());
        return super.build(template, embeddedBuildResults);
    }
}
