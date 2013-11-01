package pl.edu.pw.elka.mtoporow.isoman.modules.actions.person;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractAction;

/**
 * Akcja dla dodawania osób
 * Data utworzenia: 01.11.13, 16:44
 *
 * @author Michał Toporowski
 */
public class AddPerson extends AbstractAction {

    private final Context context;
    private final OsobaDao osobaDao;

    public AddPerson(Context context, OsobaDao osobaDao) {
        this.context = context;
        this.osobaDao = osobaDao;
    }

    @Override
    public void process(Context context) throws ProcessingException {
        final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
        final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);

        final String name = requestParameters.get("name");
        final String surname = requestParameters.get("surname");
        final String email = requestParameters.get("email");
        final String pesel = requestParameters.get("pesel");


        Osoba osoba = new Osoba();

        osoba.setImie(name);
        osoba.setNazwisko(surname);
        osoba.setPesel(pesel);
        osoba.setEmail(email);
        osoba.setLogin(name);

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            osobaDao.save(osoba);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ProcessingException(e);
        }
    }
}
