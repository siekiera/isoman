package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import java.util.List;

/**
 * Encja roli
 *
 * @author Michał Toporowski
 */
public class Rola extends UniqueEntity<Long> {
    private Long id;
    private String kod;
    private String nazwa;
    private String opis;
    private List<Osoba> osoby;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }

    public void setOsoby(List<Osoba> osoby) {
        this.osoby = osoby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rola rola = (Rola) o;

        if (id != null ? !id.equals(rola.id) : rola.id != null) return false;
        if (nazwa != null ? !nazwa.equals(rola.nazwa) : rola.nazwa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        return result;
    }
}
