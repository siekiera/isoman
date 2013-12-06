package pl.edu.pw.elka.mtoporow.isoman.services.impl;

import junit.framework.Assert;
import pl.edu.pw.elka.mtoporow.isoman.domain.BaseTest;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.FolderDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl.ArchiwumDaoBean;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl.FolderDaoBean;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.services.FilesystemService;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

import java.net.URL;

/**
 * [Klasa X]
 * Data utworzenia: 06.12.13, 18:13
 *
 * @author Michał Toporowski
 */
public class FilesystemServiceImplTest extends BaseTest {

    private FilesystemService filesystemService;
    private ArchiwumDao archiwumDao;
    private FolderDao folderDao;

    public void testGetFolderByPath() throws ServiceException {
        Folder folder;

        folder = filesystemService.getFolderByPath("archiwkoF/folderwA/podfolderek");
        Assert.assertEquals(folder.getId().longValue(), 1010L);

        folder = filesystemService.getFolderByPath("drugieF");
        Assert.assertEquals(folder.getId().longValue(), 2L);

        folder = filesystemService.getFolderByPath("drugieF/blabla");
        Assert.assertNull(folder);

        try {
            filesystemService.getFolderByPath("skjhsdfsd/sdfsdf");
            Assert.fail();
        } catch (ServiceException e) {
            //Powinno wyjątkiem rzucić
        }
    }

    @Override
    public void setUp() throws Exception {
        columnSensing = true;
        super.setUp();
        archiwumDao = createDao(ArchiwumDaoBean.class);
        folderDao = createDao(FolderDaoBean.class);
        filesystemService = new FilesystemServiceImpl(archiwumDao, folderDao);
    }

    @Override
    protected URL getDataSetFile() {
        return getClass().getResource("/dbunit/FilesystemService.dbunit.xml");
    }
}
