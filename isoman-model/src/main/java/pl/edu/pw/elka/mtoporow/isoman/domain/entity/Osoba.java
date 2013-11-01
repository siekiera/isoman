package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.Set;

/**
 * Encja osoby
 *
 * @author Michał Toporowski
 */
public class Osoba extends UniqueEntity<Long> {
    private Long nr;
    private String login;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String email;
    private Set<Przedmiot> przedmiotyProwadzone;
    private Set<Rola> role;

    public Long getNr() {
        return nr;
    }

    @Override
    public Long getId() {
        return nr;
    }

    @Override
    public void setId(Long id) {
        this.nr = id;
    }

    public void setNr(Long nr) {
        this.nr = nr;
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
                .append(os.nr, this.nr)
                .append(os.login, this.login)
                .append(os.imie, this.imie)
                .isEquals();
    }

    @Override
    public int hashCode() {
        //FIXME
        return 0;
    }

    public Set<Rola> getRole() {
        return role;
    }

    public void setRole(Set<Rola> role) {
        this.role = role;
    }
}
