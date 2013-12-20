package pl.edu.pw.elka.mtoporow.isoman.modules.views.update;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.BuildException;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractView;
import pl.edu.pw.elka.mtoporow.isoman.services.FSUpdateService;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.ArchiveReport;

import java.util.List;

/**
 * Indeks archiwów do aktualizacji
 * Data utworzenia: 20.12.13, 14:53
 *
 * @author Michał Toporowski
 */
@AccessConditions(
        @AccessCondition(permissions = {Rights.UPDATE_ARCHIVES})
)
public class ArchivesForUpdateIndex extends AbstractView {

    private static final Logger LOGGER = Logger.getLogger(ArchivesForUpdateIndex.class);

    private final FSUpdateService fsUpdateService;

    public ArchivesForUpdateIndex(Context context, FSUpdateService fsUpdateService) {
        super(context);
        this.fsUpdateService = fsUpdateService;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        TemplatingContext tc = getTemplatingContext();
        List<ArchiveReport> reportList = fsUpdateService.reportUpdatedArchives();
        tc.put("reportList", reportList);
        LOGGER.trace("Obtained reports: " + reportList);
        return super.build(template, embeddedBuildResults);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
