package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import java.util.List;
import java.util.Set;

/**
 * Encja przedmiotu
 *
 * @author Michał Toporowski
 */
public class Przedmiot extends UniqueEntity<Long> {
    private Long id;
    private JednostkaOrganizacyjna jednostka;
    private String kod;
    private String nazwa;
    private String opis;
    private Osoba wykladowca;
    private Set<Osoba> uczestnicy;
    private List<Archiwum> archiwa;

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

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Osoba getWykladowca() {
        return wykladowca;
    }

    public void setWykladowca(Osoba wykladowca) {
        this.wykladowca = wykladowca;
    }

    public Set<Osoba> getUczestnicy() {
        return uczestnicy;
    }

    public void setUczestnicy(Set<Osoba> uczestnicy) {
        this.uczestnicy = uczestnicy;
    }

    public List<Archiwum> getArchiwa() {
        return archiwa;
    }

    public void setArchiwa(List<Archiwum> archiwa) {
        this.archiwa = archiwa;
    }
}
