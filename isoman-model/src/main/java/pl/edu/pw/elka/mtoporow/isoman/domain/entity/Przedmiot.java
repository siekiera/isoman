package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.id.PrzedmiotId;

import java.util.List;
import java.util.Set;

/**
 * Encja przedmiotu
 *
 * @author Michał Toporowski
 */
public class Przedmiot extends UniqueEntity<PrzedmiotId> {
    private PrzedmiotId id;
    private JednostkaOrganizacyjna jednostka;
    private String nazwa;
    private String opis;
    private Osoba wykladowca;
    private Set<Osoba> uczestnicy;
    private List<Archiwum> archiwa;

    @Override
    public PrzedmiotId getId() {
        return id;
    }

    @Override
    public void setId(PrzedmiotId id) {
        this.id = id;
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

    public JednostkaOrganizacyjna getJednostka() {
        return jednostka;
    }

    public void setJednostka(JednostkaOrganizacyjna jednostka) {
        this.jednostka = jednostka;
    }
}
