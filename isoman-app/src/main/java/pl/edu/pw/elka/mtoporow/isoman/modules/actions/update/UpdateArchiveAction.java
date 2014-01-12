package pl.edu.pw.elka.mtoporow.isoman.modules.actions.update;

import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import pl.edu.pw.elka.mtoporow.isoman.constants.ParameterConstants;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractAction;
import pl.edu.pw.elka.mtoporow.isoman.services.FSUpdateService;

/**
 * Akcja aktualizacji archiwum
 * Data utworzenia: 05.01.14, 17:43
 *
 * @author Michał Toporowski
 */
public class UpdateArchiveAction extends AbstractAction {

    private final FSUpdateService fsUpdateService;

    public UpdateArchiveAction(FSUpdateService fsUpdateService) {
        this.fsUpdateService = fsUpdateService;
    }

    @Override
    public void process(Context context) throws ProcessingException {
        RequestParameters requestParameters = getRequestParameters(context);
        long archiveId = requestParameters.getLong(ParameterConstants.ARCHIVE_ID);
        boolean switchVersions = requestParameters.getBoolean(ParameterConstants.SWITCH_VERSIONS, true);
        //wygeneruj archiwum i zaktualizuj wersje
        Transaction tx = getSession(context).beginTransaction();
        try {
            fsUpdateService.update(archiveId, switchVersions);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ProcessingException(e);
        }
    }
}
