package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import java.util.List;

/**
 * Encja typu jednostki
 * Data utworzenia: 03.11.13, 12:46
 *
 * @author Michał Toporowski
 */
public class TypJednostki extends UniqueEntity<Long> {
    private Long id;
    private String nazwa;
    private TypJednostki nadrzedny;
    private List<TypJednostki> podrzedne;

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

    public TypJednostki getNadrzedny() {
        return nadrzedny;
    }

    public void setNadrzedny(TypJednostki nadrzedny) {
        this.nadrzedny = nadrzedny;
    }

    public List<TypJednostki> getPodrzedne() {
        return podrzedne;
    }

    public void setPodrzedne(List<TypJednostki> podrzedne) {
        this.podrzedne = podrzedne;
    }
}
