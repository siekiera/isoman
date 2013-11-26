package pl.edu.pw.elka.mtoporow.isoman.domain;

import junit.framework.Assert;
import org.hibernate.Transaction;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.RolaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl.OsobaDaoBean;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl.RolaDaoBean;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Rola;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Test dao dla roli
 * Data utworzenia: 26.10.13, 22:06
 *
 * @author Michał Toporowski
 */
public class RolaDaoTest extends BaseTest {

    private RolaDao rolaDao;
    private OsobaDao osobaDao;

    public void testGetPersonsForRole() throws Exception {
        List<Rola> role = rolaDao.getAll();
        Rola admin = role.get(0);

        List<Osoba> admins = admin.getOsoby();
        Assert.assertEquals(admins.size(), 1);
    }

    public void testInsertAndRemoveRole() throws Exception {

        Transaction tx = rolaDao.beginTransaction();

        Rola testRole = new Rola();
        testRole.setId(1000L);
        testRole.setKod("test");
        testRole.setNazwa("test");
        testRole.setOsoby(new ArrayList<>(osobaDao.getAll()));

        try {
            rolaDao.save(testRole);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        Rola dbRole = rolaDao.getById(1000L);
        Assert.assertEquals(testRole, dbRole);

        tx = rolaDao.beginTransaction();
        try {
            rolaDao.delete(dbRole);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }


        dbRole = rolaDao.getById(1000L);
        Assert.assertNull(dbRole);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        rolaDao = createDao(RolaDaoBean.class);
        osobaDao = createDao(OsobaDaoBean.class);
    }

    @Override
    protected URL getDataSetFile() {
        return getClass().getResource("/dbunit/RolaDao.dbunit.xml");
    }
}
