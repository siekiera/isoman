package pl.edu.pw.elka.mtoporow.isoman.domain;

import junit.framework.Assert;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl.ArchiwumDaoBean;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;

import java.net.URL;

/**
 * Test DAO dla archiwów
 * Data utworzenia: 06.12.13, 16:37
 *
 * @author Michał Toporowski
 */
public class ArchiwumDaoTest extends BaseTest {

    private ArchiwumDao archiwumDao;

    public void testGetByRoot() {
        Archiwum archiwko = archiwumDao.getByRootFolder("archiwkoF");
        Archiwum drugie = archiwumDao.getByRootFolder("drugieF");
        Archiwum niema = archiwumDao.getByRootFolder("niema");

        Assert.assertEquals(archiwko.getNazwa(), "archiwko");
        Assert.assertEquals(drugie.getNazwa(), "drugie");
        Assert.assertNull(niema);
    }



    @Override
    public void setUp() throws Exception {
        super.setUp();
        archiwumDao = createDao(ArchiwumDaoBean.class);
    }

    @Override
    protected URL getDataSetFile() {
        return getClass().getResource("/dbunit/ArchiwumDao.dbunit.xml");
    }

}
