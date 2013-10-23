package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

/**
 * Encja przedmiotu
 *
 * @author Michał Toporowski
 */
public class Przedmiot extends GenericEntity {
    String kod;
    String nazwa;
    String opis;
    Osoba wykladowca;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNazwa() {
        return nazwa;
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
}
