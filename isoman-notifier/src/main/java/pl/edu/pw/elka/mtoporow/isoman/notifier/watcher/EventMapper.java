package pl.edu.pw.elka.mtoporow.isoman.notifier.watcher;

import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa pomocniczna do mapowania typów zdarzeń
 * Data utworzenia: 13.12.13, 12:29
 *
 * @author Michał Toporowski
 */
public class EventMapper {

    private static Map<WatchEvent.Kind, FolderEventType> fileEventMap;
    private static Map<WatchEvent.Kind, FolderEventType> folderEventMap;

    static {
        fileEventMap = new HashMap<>();
        fileEventMap.put(StandardWatchEventKinds.ENTRY_CREATE, FolderEventType.FILE_CREATED);
        fileEventMap.put(StandardWatchEventKinds.ENTRY_DELETE, FolderEventType.FILE_DELETED);
        fileEventMap.put(StandardWatchEventKinds.ENTRY_MODIFY, FolderEventType.FILE_CHANGED);
        folderEventMap = new HashMap<>();
        folderEventMap.put(StandardWatchEventKinds.ENTRY_CREATE, FolderEventType.FOLDER_CREATED);
        folderEventMap.put(StandardWatchEventKinds.ENTRY_MODIFY, FolderEventType.FOLDER_CHANGED);
        folderEventMap.put(StandardWatchEventKinds.ENTRY_DELETE, FolderEventType.FOLDER_DELETED);
    }

    public static final FolderEventType mapEvent(final WatchEvent.Kind watchEventKind, final boolean isDirectory) {
        if (isDirectory) {
            return folderEventMap.get(watchEventKind);
        } else {
            return fileEventMap.get(watchEventKind);
        }
    }
}
