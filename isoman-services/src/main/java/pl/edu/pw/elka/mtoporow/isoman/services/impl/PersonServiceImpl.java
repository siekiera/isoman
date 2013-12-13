package pl.edu.pw.elka.mtoporow.isoman.services.impl;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.objectledge.security.SecurityManager;
import org.objectledge.security.object.Group;
import org.objectledge.security.object.Role;
import org.objectledge.security.object.SecurityUser;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.JODao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.RolaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Rola;
import pl.edu.pw.elka.mtoporow.isoman.services.PersonService;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

/**
 * Serwis do zarządzania osobami
 * Data utworzenia: 21.11.13, 18:37
 *
 * @author Michał Toporowski
 */
@Deprecated
public class PersonServiceImpl implements PersonService {

    private final Logger LOG = Logger.getLogger(PersonServiceImpl.class);

    private final OsobaDao osobaDao;
    private final RolaDao rolaDao;
    private final JODao joDao;
    private final SecurityManager securityManager;

    public PersonServiceImpl(OsobaDao osobaDao, RolaDao rolaDao, JODao joDao, SecurityManager securityManager) {

        this.osobaDao = osobaDao;
        this.rolaDao = rolaDao;
        this.joDao = joDao;
        this.securityManager = securityManager;
    }

    @Override
    public void addPerson(Osoba osoba, String password) throws ServiceException {
        LOG.trace("addPerson: start");
        Transaction tx = osobaDao.beginTransaction();
        try {
            //zapisz osobę
            osobaDao.save(osoba);

            //Dodaj użytkownika Ledge Security
            SecurityUser user = securityManager.createAccount(osoba.getLogin(), osoba.getImie(), osoba.getNazwisko(), password);

            user.setLocked(false);
            user.setConfirmed(true);
            securityManager.saveUser(user);

            Group globalGroup = securityManager.getGlobalGroup();

            //przypisz role
//            for (Rola rola : osoba.getRole()) {
            Role securityRole = securityManager.getRoleByName(osoba.getRola().getNazwa());
            securityManager.grant(user, globalGroup, securityRole);
//            }

            tx.commit();
            LOG.info("addPerson: Transaction committed");
        } catch (Exception e) {
            tx.rollback();
            LOG.error("addPerson: Error during saving person", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void addPerson(Osoba person, String password, Long roleId) throws ServiceException {
        Rola rola = rolaDao.getById(roleId);
        person.setRola(rola);
        //FIXME:: usunąć hardkod
        person.setJednostkaOrganizacyjna(joDao.getById(5000L));
        addPerson(person, password);
    }
}
