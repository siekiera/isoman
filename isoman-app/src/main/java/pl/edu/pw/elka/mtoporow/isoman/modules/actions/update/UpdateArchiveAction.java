package pl.edu.pw.elka.mtoporow.isoman.modules.actions.update;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import pl.edu.pw.elka.mtoporow.isoman.constants.ParameterConstants;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.WersjaArchiwum;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractAction;
import pl.edu.pw.elka.mtoporow.isoman.services.FSUpdateService;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

/**
 * Akcja aktualizacji archiwum
 * Data utworzenia: 05.01.14, 17:43
 *
 * @author Michał Toporowski
 */
public class UpdateArchiveAction extends AbstractAction {

    private final FSUpdateService fsUpdateService;
    private final ArchiwumDao archiwumDao;

    public UpdateArchiveAction(FSUpdateService fsUpdateService, ArchiwumDao archiwumDao) {
        this.fsUpdateService = fsUpdateService;
        this.archiwumDao = archiwumDao;
    }

    @Override
    public void process(Context context) throws ProcessingException {
        RequestParameters requestParameters = getRequestParameters(context);
        long archiveId = requestParameters.getLong(ParameterConstants.ARCHIVE_ID);
        boolean switchVersions = requestParameters.getBoolean(ParameterConstants.SWITCH_VERSIONS, false);
        //wygeneruj archiwum i zaktualizuj wersje
        WersjaArchiwum wersjaArchiwum;
        try {
            wersjaArchiwum = fsUpdateService.update(archiveId, switchVersions);
        } catch (ServiceException e) {
            throw new ProcessingException(e);
        }
        processSave(archiwumDao, wersjaArchiwum);
    }
}
