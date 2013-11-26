package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.Set;

/**
 * Encja osoby
 *
 * @author Michał Toporowski
 */
public class Osoba extends UniqueEntity<Long> {
    private Long id;
    private String login;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String email;
    private String haslo;
    private Set<Przedmiot> przedmiotyProwadzone;
    private Rola rola;
    private JednostkaOrganizacyjna jednostkaOrganizacyjna;
    private Set<Przedmiot> przedmioty;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Przedmiot> getPrzedmiotyProwadzone() {
        return przedmiotyProwadzone;
    }

    public void setPrzedmiotyProwadzone(Set<Przedmiot> przedmiotyProwadzone) {
        this.przedmiotyProwadzone = przedmiotyProwadzone;
    }

    public Rola getRola() {
        return rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }

    public JednostkaOrganizacyjna getJednostkaOrganizacyjna() {
        return jednostkaOrganizacyjna;
    }

    public void setJednostkaOrganizacyjna(JednostkaOrganizacyjna jednostkaOrganizacyjna) {
        this.jednostkaOrganizacyjna = jednostkaOrganizacyjna;
    }

    public Set<Przedmiot> getPrzedmioty() {
        return przedmioty;
    }

    public void setPrzedmioty(Set<Przedmiot> przedmioty) {
        this.przedmioty = przedmioty;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Osoba os = (Osoba) o;


        return new EqualsBuilder()
                .append(os.id, this.id)
                .append(os.login, this.login)
                .append(os.imie, this.imie)
                .isEquals();
    }

    @Override
    public int hashCode() {
        //FIXME
        return 0;
    }
}
