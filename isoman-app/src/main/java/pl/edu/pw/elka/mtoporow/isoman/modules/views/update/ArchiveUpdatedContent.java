package pl.edu.pw.elka.mtoporow.isoman.modules.views.update;

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
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractView;
import pl.edu.pw.elka.mtoporow.isoman.services.FSUpdateService;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.FSReport;

import java.util.List;

/**
 * Lista zawartości archiwum do aktualizacji
 * Data utworzenia: 27.12.13, 19:12
 *
 * @author Michał Toporowski
 */
@AccessConditions(
        @AccessCondition(permissions = {Rights.UPDATE_ARCHIVES})
)
public class ArchiveUpdatedContent extends AbstractView {

    private static final Logger LOGGER = Logger.getLogger(ArchiveUpdatedContent.class);

    private final FSUpdateService fsUpdateService;
    private final ArchiwumDao archiwumDao;

    public ArchiveUpdatedContent(Context context, FSUpdateService fsUpdateService, ArchiwumDao archiwumDao) {
        super(context);
        this.fsUpdateService = fsUpdateService;
        this.archiwumDao = archiwumDao;
    }

    @Override
    public String build(Template template, String embeddedBuildResults) throws BuildException, ProcessingException {
        TemplatingContext tc = getTemplatingContext();
        RequestParameters rp = getRequestParameters();
        Long archiveId = rp.getLong(ParameterConstants.ARCHIVE_ID);
        Archiwum archiwum = archiwumDao.getById(archiveId);
        //TODO:: if not null
        List<FSReport> reportList = fsUpdateService.reportUpdates(archiwum, false);
        tc.put("reportList", reportList);
        tc.put("archiwum", archiwum);
        LOGGER.trace("Obtained reports: " + reportList);
        return super.build(template, embeddedBuildResults);
    }
}
