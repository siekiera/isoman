package pl.edu.pw.elka.mtoporow.isoman.notifier.tasks;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.threads.Task;

/**
 * Zadanie monitorowania katalogów
 * Data utworzenia: 04.12.13, 17:14
 *
 * @author Michał Toporowski
 */
public class NotifierTask extends Task {

    @Override
    public void process(Context context) throws ProcessingException {
        //TODO
    }

    @Override
    public String getName() {
        return "Directory change notifier task";
    }
}
