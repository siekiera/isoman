package pl.edu.pw.elka.mtoporow.isoman.notifier.tasks;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.threads.Task;
import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;
import pl.edu.pw.elka.mtoporow.isoman.notifier.watcher.FolderListener;
import pl.edu.pw.elka.mtoporow.isoman.notifier.watcher.FolderWatcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Zadanie monitorowania katalogów
 * Data utworzenia: 04.12.13, 17:14
 *
 * @author Michał Toporowski
 */
public class NotifierTask extends Task {

    private static final Logger LOGGER = Logger.getLogger(NotifierTask.class);
    private final ConfigManager configManager;
    private final FolderWatcher folderWatcher;
    private final FolderListener folderListener;
    private final Path rootPath;

    public NotifierTask(ConfigManager configManager) throws IOException {
        this.configManager = configManager;
        this.folderListener = new NotifyingFolderListener();
        this.rootPath = Paths.get(configManager.get("notifier.root.dir"));
        this.folderWatcher = new FolderWatcher(rootPath, folderListener);
    }

    @Override
    public void process(Context context) throws ProcessingException {
        folderWatcher.process();
    }

    @Override
    public String getName() {
        return "Directory change notifier task";
    }

    private class NotifyingFolderListener implements FolderListener {
        @Override
        public void created(Path path) {
            //TODO
        }

        @Override
        public void changed(Path path) {
            //TODO
        }

        @Override
        public void deleted(Path path) {
            //TODO
        }
    }


}
