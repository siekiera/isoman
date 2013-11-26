package pl.edu.pw.elka.mtoporow.isoman.domain;

import junit.framework.Assert;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl.OsobaDaoBean;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.impl.SessionFactoryMockImpl;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * Test klasy osoba dao
 *
 * @author Michał Toporowski
 */
public class OsobaDaoTest extends BaseTest {

    private OsobaDao osobaDao;

    /**
     * Test metod pobierania osób
     */
    public void testGetAllOsobas() {
        List<Osoba> list2 = osobaDao.getAll();
        Assert.assertEquals(list2.size(), 2);

        Osoba zdzichu = osobaDao.getById(2L);
        Assert.assertEquals(zdzichu.getLogin(), "zdzichu");
    }

    public void testGetByLogin() {
        Osoba osoba = osobaDao.getByLogin("zdzichu");
        Assert.assertNotNull(osoba);
        Assert.assertEquals(osoba.getLogin(), "zdzichu");
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        osobaDao = createDao(OsobaDaoBean.class);
    }

    @Override
    protected URL getDataSetFile() {
        return getClass().getResource("/dbunit/OsobaDao.dbunit.xml");
    }
}
