package pl.edu.pw.elka.mtoporow.isoman.domain.entity.id;

import java.io.Serializable;

/**
 * Klasa reprezentująca identyfikator wersji archiwum
 * Data utworzenia: 10.11.13, 15:19
 *
 * @author Michał Toporowski
 */
public class WersjaArchiwumId implements Serializable {
    private Long nr;
    private Long idArchiwum;

    public WersjaArchiwumId() {

    }

    public WersjaArchiwumId(final Long nr, final Long idArchiwum) {
        this.nr = nr;
        this.idArchiwum = idArchiwum;
    }

    public Long getNr() {
        return nr;
    }

    public void setNr(Long nr) {
        this.nr = nr;
    }

    public Long getIdArchiwum() {
        return idArchiwum;
    }

    public void setIdArchiwum(Long idArchiwum) {
        this.idArchiwum = idArchiwum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WersjaArchiwumId that = (WersjaArchiwumId) o;

        if (idArchiwum != null ? !idArchiwum.equals(that.idArchiwum) : that.idArchiwum != null) return false;
        if (nr != null ? !nr.equals(that.nr) : that.nr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nr != null ? nr.hashCode() : 0;
        result = 31 * result + (idArchiwum != null ? idArchiwum.hashCode() : 0);
        return result;
    }
}
