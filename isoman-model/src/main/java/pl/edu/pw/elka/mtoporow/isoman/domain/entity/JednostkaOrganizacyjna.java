package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import java.util.List;

/**
 * Encja jednostki organizacyjnej
 * Data utworzenia: 03.11.13, 12:45
 *
 * @author Michał Toporowski
 */
public class JednostkaOrganizacyjna extends UniqueEntity<Long> {
    private Long id;
    private String nazwa;
    private TypJednostki typ;
    private JednostkaOrganizacyjna nadrzedna;
    private List<JednostkaOrganizacyjna> podrzedne;
    private List<Przedmiot> przedmioty;
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

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public JednostkaOrganizacyjna getNadrzedna() {
        return nadrzedna;
    }

    public void setNadrzedna(JednostkaOrganizacyjna nadrzedna) {
        this.nadrzedna = nadrzedna;
    }

    public List<JednostkaOrganizacyjna> getPodrzedne() {
        return podrzedne;
    }

    public void setPodrzedne(List<JednostkaOrganizacyjna> podrzedne) {
        this.podrzedne = podrzedne;
    }

    public TypJednostki getTyp() {
        return typ;
    }

    public void setTyp(TypJednostki typ) {
        this.typ = typ;
    }

    public List<Przedmiot> getPrzedmioty() {
        return przedmioty;
    }

    public void setPrzedmioty(List<Przedmiot> przedmioty) {
        this.przedmioty = przedmioty;
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }

    public void setOsoby(List<Osoba> osoby) {
        this.osoby = osoby;
    }
}

