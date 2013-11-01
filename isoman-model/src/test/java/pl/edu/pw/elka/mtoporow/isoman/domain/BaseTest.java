package pl.edu.pw.elka.mtoporow.isoman.domain;

import junit.framework.TestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.impl.SessionFactoryMockImpl;

import java.lang.reflect.Constructor;
import java.net.URL;

/**
 * Klasa bazowa dla testów DBUnit
 *
 * @author Michał Toporowski
 */
public abstract class BaseTest extends TestCase {

    private static final String DBUNIT_DRIVER_CLASS = "org.postgresql.Driver";
    private static final String DBUNIT_CONNECTION = "jdbc:postgresql://localhost:5432/isoman_test";
    private static final String DBUNIT_USERNAME = "isoman_test";
    private static final String DBUNIT_PASSWORD = "zxcvbnm";
    private static final String DBUNIT_SCHEMA = "public";

    private IDatabaseTester databaseTester;

    private Context context;
    private SessionFactory sessionFactory;

    /**
     * Pobiera plik z zestawem danych
     *
     * @return
     */
    protected abstract URL getDataSetFile();


    @Override
    protected void setUp() throws Exception {
        databaseTester = new JdbcDatabaseTester(DBUNIT_DRIVER_CLASS, DBUNIT_CONNECTION, DBUNIT_USERNAME, DBUNIT_PASSWORD, DBUNIT_SCHEMA);

        databaseTester.getConnection().getConfig().setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);

        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getDataSetFile());
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();

    }

    @Override
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    /**
     * Tworzy implementację dao
     *
     * @param daoClass
     * @param <B>
     * @return
     * @throws ReflectiveOperationException
     */
    protected <B> B createDao(Class daoClass) throws ReflectiveOperationException {
        Constructor constructor = daoClass.getConstructor(Context.class, SessionFactory.class);
        return (B) constructor.newInstance(getContext(), getSessionFactory());
    }

    private SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new SessionFactoryMockImpl();
        }
        return sessionFactory;
    }

    private Context getContext() {
        if (context == null) {
            context = new Context();
        }
        return context;
    }
}
