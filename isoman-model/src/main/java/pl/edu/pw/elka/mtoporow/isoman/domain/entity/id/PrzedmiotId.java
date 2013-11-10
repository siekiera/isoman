package pl.edu.pw.elka.mtoporow.isoman.domain.entity.id;

import java.io.Serializable;

/**
 * Klasa reprezentująca id przedmiotu
 * Data utworzenia: 03.11.13, 16:02
 *
 * @author Michał Toporowski
 */
public class PrzedmiotId implements Serializable {
    private Long idJednostki;
    private String kodPrzedmiotu;

    /**
     * Konstruktor domyślny
     */
    public PrzedmiotId() {
    }

    /**
     * Konstruktor z parametrami obowiązkowymi
     *
     * @param idJednostki
     * @param kodPrzedmiotu
     */
    public PrzedmiotId(Long idJednostki, String kodPrzedmiotu) {
        this.idJednostki = idJednostki;
        this.kodPrzedmiotu = kodPrzedmiotu;
    }

    public Long getIdJednostki() {
        return idJednostki;
    }

    public void setIdJednostki(Long idJednostki) {
        this.idJednostki = idJednostki;
    }

    public String getKodPrzedmiotu() {
        return kodPrzedmiotu;
    }

    public void setKodPrzedmiotu(String kodPrzedmiotu) {
        this.kodPrzedmiotu = kodPrzedmiotu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrzedmiotId that = (PrzedmiotId) o;

        if (!idJednostki.equals(that.idJednostki)) return false;
        if (!kodPrzedmiotu.equals(that.kodPrzedmiotu)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idJednostki.hashCode();
        result = 31 * result + kodPrzedmiotu.hashCode();
        return result;
    }
}
