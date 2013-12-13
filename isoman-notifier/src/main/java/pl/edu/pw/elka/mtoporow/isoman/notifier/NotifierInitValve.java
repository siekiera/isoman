package pl.edu.pw.elka.mtoporow.isoman.notifier;

import org.jcontainer.dna.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.pipeline.Valve;
import org.objectledge.threads.ThreadPool;
import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;
import pl.edu.pw.elka.mtoporow.isoman.notifier.tasks.NotifierTask;

import java.io.IOException;

/**
 * Valve inicjalizujący zadanie nasłuchujące zmiany w katalogach
 * Data utworzenia: 04.12.13, 17:13
 *
 * @author Michał Toporowski
 */
public class NotifierInitValve implements Valve {

    private final ThreadPool threadPool;
    private final ConfigManager configManager;
    private final Logger logger;
    private NotifierTask notifierTask;

    public NotifierInitValve(ThreadPool threadPool, ConfigManager configManager, Logger logger) {
        this.threadPool = threadPool;
        this.configManager = configManager;
        this.logger = logger;
        try {
            //zainicjalizuj wątek powiadamiacza
            notifierTask = new NotifierTask(configManager);
            threadPool.runWorker(notifierTask);
        } catch (IOException e) {
            logger.error("Error during notifier task initialization", e);
        }
    }

    @Override
    public void process(Context context) throws ProcessingException {

    }
}
