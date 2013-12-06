package pl.edu.pw.elka.mtoporow.isoman.notifier;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.threads.ThreadPool;
import pl.edu.pw.elka.mtoporow.isoman.notifier.tasks.NotifierTask;

/**
 * Valve inicjalizujący zadanie nasłuchujące zmiany w katalogach
 * Data utworzenia: 04.12.13, 17:13
 *
 * @author Michał Toporowski
 */
public class NotifierInitValve implements Valve {

    private final ThreadPool threadPool;
    private NotifierTask notifierTask;

    public NotifierInitValve(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void process(Context context) throws ProcessingException {
        //zainicjalizuj wątek powiadamiacza
        notifierTask = new NotifierTask();
        threadPool.runWorker(notifierTask);
    }
}
