package pl.edu.pw.elka.mtoporow.isoman.modules.actions.person;

import org.hibernate.Session;
import org.objectledge.authentication.PasswordDigester;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.pipeline.ProcessingException;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.RolaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.ol.extension.AbstractAction;
import pl.edu.pw.elka.mtoporow.isoman.services.PersonService;

/**
 * Akcja dla dodawania osób
 * Data utworzenia: 01.11.13, 16:44
 *
 * @author Michał Toporowski
 */
public class AddPersonAction extends AbstractAction {

    private final Context context;
    private final OsobaDao osobaDao;
    private final RolaDao rolaDao;
    private final PersonService personService;
    private final PasswordDigester passwordDigester;

    public AddPersonAction(Context context, OsobaDao osobaDao, RolaDao rolaDao, PersonService personService, PasswordDigester passwordDigester) {
        this.context = context;
        this.osobaDao = osobaDao;
        this.rolaDao = rolaDao;
        this.personService = personService;
        this.passwordDigester = passwordDigester;
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
        osoba.setHaslo(passwordDigester.digestPassword(password));
        osoba.setLogin(name);
        osoba.setRola(rolaDao.getById(roleId));

//TODO:: obsługa błędów
//TODO::sprawdzić czy rola istnieje w Ledge'u??

        processSave(osobaDao, osoba);

//        try {
//            personService.addPerson(osoba, password, roleId);
//        } catch (ServiceException e) {
//            throw new ProcessingException(e);
//        }

    }
}
