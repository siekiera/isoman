package pl.edu.pw.elka.mtoporow.isoman.notifier.tasks;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionValve;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.threads.Task;
import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.GenericEntity;
import pl.edu.pw.elka.mtoporow.isoman.notifier.watcher.FolderEventType;
import pl.edu.pw.elka.mtoporow.isoman.notifier.watcher.FolderListener;
import pl.edu.pw.elka.mtoporow.isoman.notifier.watcher.FolderWatcher;
import pl.edu.pw.elka.mtoporow.isoman.services.FilesystemService;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

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
    private final HibernateSessionValve hibernateSessionValve;
    private final FilesystemService filesystemService;
    private final ConfigManager configManager;
    private final FolderWatcher folderWatcher;
    private final FolderListener folderListener;
    private final Path rootPath;

    /**
     * Konstruktor zadania
     *
     * @param hibernateSessionValve
     * @param filesystemService
     * @param configManager         @throws IOException
     */
    public NotifierTask(HibernateSessionValve hibernateSessionValve, FilesystemService filesystemService, ConfigManager configManager) throws IOException {
        this.hibernateSessionValve = hibernateSessionValve;
        this.filesystemService = filesystemService;
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

    /**
     * Pobiera sesję Hibernate'a
     *
     * @return
     */
    private Session getSession() {
        return hibernateSessionValve.getSession();
    }

    /**
     * Słuchacz katalogów powiadamiający bazę danych o zmianach
     */
    private class NotifyingFolderListener implements FolderListener {

        @Override
        public void eventFired(FolderEventType eventType, String path, Long fsid) {
            Transaction tx = getSession().beginTransaction();
            try {
                GenericEntity entityToSave = getEntityToSave(eventType, path, fsid);
                getSession().save(entityToSave);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                LOGGER.warn("Exception during folder listening", e);
            }

        }

        private GenericEntity getEntityToSave(FolderEventType eventType, String path, Long fsid) throws ServiceException {
            filesystemService.clearHibernateSession();
            switch (eventType) {
                case FILE_CHANGED:
                case FILE_CREATED:
                    return filesystemService.markFile(path);
                case FILE_DELETED:
                case FOLDER_DELETED:
                    return filesystemService.markAsDeleted(path);
                case FOLDER_CHANGED:
                    return filesystemService.markChangedFolder(path);
                case FOLDER_CREATED:
                    return filesystemService.markCreatedFolder(path, fsid);
            }
            return null;

        }
    }


}
