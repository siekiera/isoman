package pl.edu.pw.elka.mtoporow.isoman.notifier.tasks;

import org.apache.log4j.Logger;
import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.threads.Task;
import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * Zadanie monitorowania katalogów
 * Data utworzenia: 04.12.13, 17:14
 *
 * @author Michał Toporowski
 */
public class NotifierTask extends Task {

    private static final Logger LOGGER = Logger.getLogger(NotifierTask.class);
    private final ConfigManager configManager;
    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final boolean trace;
    private final boolean recursive = true;

    public NotifierTask(ConfigManager configManager) throws IOException {
        this.configManager = configManager;
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        this.trace = true;

        this.registerDirs(Paths.get(configManager.get("notifier.root.dir")));
    }

    @Override
    public void process(Context context) throws ProcessingException {
        LOGGER.debug("Started watching directories for changes");
        while (true) {


            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                LOGGER.warn("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                // print out event
                LOGGER.debug(String.format("%s: %s\n", event.kind().name(), child));

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == StandardWatchEventKinds.ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) {
                            registerDirs(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
        LOGGER.debug("Ended watching directories for changes");
    }

    @Override
    public String getName() {
        return "Directory change notifier task";
    }

    /**
     * Rejestruje katalog i wszystkie jego podkatalogi w słuchaczu
     *
     * @param root
     */
    private void registerDirs(final Path root) throws IOException {
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDir(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Rejestruje katalog w WatchServisie
     *
     * @param dir
     */
    private void registerDir(final Path dir) throws IOException {
        WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                LOGGER.trace(String.format("register: %s\n", dir));
            } else {
                if (!dir.equals(prev)) {
                    LOGGER.trace(String.format("update: %s -> %s\n", prev, dir));
                }
            }
        }
        keys.put(key, dir);
    }


    @SuppressWarnings("unchecked")
    private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }
}
