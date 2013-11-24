package pl.edu.pw.elka.mtoporow.isoman.modules.actions.person;

import org.hibernate.Session;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractAction;
import pl.edu.pw.elka.mtoporow.isoman.services.PersonService;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

/**
 * Akcja dla dodawania osób
 * Data utworzenia: 01.11.13, 16:44
 *
 * @author Michał Toporowski
 */
public class AddPersonAction extends AbstractAction {

    private final Context context;
    private final OsobaDao osobaDao;
    private final PersonService personService;

    public AddPersonAction(Context context, OsobaDao osobaDao, PersonService personService) {
        this.context = context;
        this.osobaDao = osobaDao;
        this.personService = personService;
    }

    @Override
    public void process(Context context) throws ProcessingException {
        final Session session = HibernateSessionContext.getHibernateSessionContext(context).getSession();
        final RequestParameters requestParameters = RequestParameters.getRequestParameters(context);

        final String name = requestParameters.get("name");
        final String surname = requestParameters.get("surname");
        final String email = requestParameters.get("email");
        final String pesel = requestParameters.get("pesel");
        final String password = requestParameters.get("password");
        final long roleId = requestParameters.getLong("role");


        Osoba osoba = new Osoba();

        osoba.setImie(name);
        osoba.setNazwisko(surname);
        osoba.setPesel(pesel);
        osoba.setEmail(email);
        osoba.setLogin(name);
//TODO:: obsługa błędów

//        processSave(osobaDao, osoba);

        try {
            personService.addPerson(osoba, password, roleId);
        } catch (ServiceException e) {
            throw new ProcessingException(e);
        }

    }
}
