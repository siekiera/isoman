package pl.edu.pw.elka.mtoporow.isoman.ajax;

import org.objectledge.ajax.annotations.AjaxMethod;
import org.objectledge.ajax.annotations.AjaxParam;
import org.objectledge.security.anotation.AccessCondition;
import org.objectledge.security.anotation.AccessConditions;
import pl.edu.pw.elka.mtoporow.isoman.constants.security.Rights;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.OsobaDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Serwis ajaksowy dla osób
 * Data utworzenia: 26.01.14, 23:30
 *
 * @author Michał Toporowski
 */
public class PersonService {

    private final OsobaDao osobaDao;

    public PersonService(OsobaDao osobaDao) {
        this.osobaDao = osobaDao;
    }

    /**
     * Pobiera listę osób zgodnych z przykładem
     *
     * @param searchParams
     * @return
     */
    @AccessConditions(
            @AccessCondition(permissions = {Rights.MANAGE_PERSONS})
    )
    @AjaxMethod("filteredPersons")
    public List<GUIPersonData> filteredPersons(
            @AjaxParam(value="searchParams", keyTypes = {String.class}, types = {Object.class}) final Map<String,?> searchParams) {
        final Osoba example = new Osoba();
        example.setNazwisko((String) searchParams.get("nazwisko"));
        List<Osoba> results = osobaDao.getByExample(example);
        return rewritePersonData(results);
    }

    /**
     * Przepisuje dane z listy encji do list klas GUI-owych
     *
     * @param osobaList
     * @return
     */
    private List<GUIPersonData> rewritePersonData(final List<Osoba> osobaList) {
        final List<GUIPersonData> result = new ArrayList<>();
        for(Osoba osoba : osobaList) {
            result.add(new GUIPersonData(osoba));
        }
        return result;
    }

    /**
     * Klasa zawierająca dane osoby wyświetlane użytkownikowi
     * Pola takie, jak w encji osoby, ale bez hasła i obiektów zależnych
     */
    public class GUIPersonData {
        private Long id;
        private String login;
        private String imie;
        private String nazwisko;
        private String pesel;
        private String email;

        /**
         * Konstruktor przepisujący pola z Osoby do GUIPersonData
         *
         * @param osoba
         */
        public GUIPersonData(final Osoba osoba) {
            this.id = osoba.getId();
            this.login = osoba.getLogin();
            this.imie = osoba.getImie();
            this.nazwisko = osoba.getNazwisko();
            this.pesel = osoba.getPesel();
            this.email = osoba.getEmail();
        }

        public Long getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        public String getImie() {
            return imie;
        }

        public String getNazwisko() {
            return nazwisko;
        }

        public String getPesel() {
            return pesel;
        }

        public String getEmail() {
            return email;
        }
    }
}
