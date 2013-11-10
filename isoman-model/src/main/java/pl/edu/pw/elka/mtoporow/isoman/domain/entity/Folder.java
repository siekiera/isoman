package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import java.util.List;

/**
 * Encja folderu
 * Data utworzenia: 10.11.13, 16:10
 *
 * @author Michał Toporowski
 */
public class Folder extends UniqueEntity<Long> {
    private Long id;
    private String nazwa;
    private Folder nadrzedny;
    private List<Folder> podrzedne;
    private List<Plik> pliki;
    private Archiwum archiwum;

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

    public Folder getNadrzedny() {
        return nadrzedny;
    }

    public void setNadrzedny(Folder nadrzedny) {
        this.nadrzedny = nadrzedny;
    }

    public List<Folder> getPodrzedne() {
        return podrzedne;
    }

    public void setPodrzedne(List<Folder> podrzedne) {
        this.podrzedne = podrzedne;
    }

    public List<Plik> getPliki() {
        return pliki;
    }

    public void setPliki(List<Plik> pliki) {
        this.pliki = pliki;
    }

    public Archiwum getArchiwum() {
        return archiwum;
    }

    public void setArchiwum(Archiwum archiwum) {
        this.archiwum = archiwum;
    }
}
