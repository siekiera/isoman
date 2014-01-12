package pl.edu.pw.elka.mtoporow.isoman.modules.actions;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.HttpContext;
import org.objectledge.web.mvc.MVCContext;
import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;
import pl.edu.pw.elka.mtoporow.isoman.constants.ParameterConstants;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.WersjaArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.WersjaArchiwum;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractAction;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Akcja do pobierania plików (archiwów)
 * Data utworzenia: 12.01.14, 16:50
 *
 * @author Michał Toporowski
 */
@AccessConditions(
        @AccessCondition(permissions = {Rights.DOWNLOAD_ARCHIVES})
)
public class GetFile extends AbstractAction {

    private final WersjaArchiwumDao wersjaArchiwumDao;
    private final String rootFolder;

    public GetFile(WersjaArchiwumDao wersjaArchiwumDao, ConfigManager configManager) {
        this.wersjaArchiwumDao = wersjaArchiwumDao;
        this.rootFolder = configManager.get("generator.out.dir");
    }

    @Override
    public void process(Context context) throws ProcessingException {
        final RequestParameters requestParameters = getRequestParameters(context);
        final HttpContext httpContext = HttpContext.getHttpContext(context);
        final TemplatingContext tc = getTemplatingContext(context);

        final long archiveId = requestParameters.getLong(ParameterConstants.ARCHIVE_ID);

        WersjaArchiwum wersjaArchiwum = wersjaArchiwumDao.getCurrent(archiveId);
        if (wersjaArchiwum == null) {
            tc.put("errorResult", "Archiwum nie istnieje!");
            return;
        }

        String filename = wersjaArchiwum.getSciezka();
        File file = new File(rootFolder, filename);
        if (!file.exists()) {
            tc.put("errorResult", "Błąd: plik nie istnieje");
        }
        //przekazujemy plik bezpośrednio do odpowiedzi servletu
        HttpServletResponse resp = httpContext.getResponse();
        resp.setHeader("Content-Length", String.valueOf(file.length()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + wersjaArchiwum.getArchiwum().getNazwa() + ".iso\"");
        Path filePath = file.toPath();
        try {
            httpContext.setContentType(Files.probeContentType(filePath));
            Files.copy(filePath, httpContext.getOutputStream());
        } catch (Exception e) {
            throw new ProcessingException(e);
        }
    }
}
