package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

/**
 * Interfejs dla encji reprezentujących system plików (pliki lub foldery)
 * Data utworzenia: 14.12.13, 11:26
 *
 * @author Michał Toporowski
 */
public abstract class FSEntity<T> extends UniqueEntity<T> {

    public abstract String getNazwa();

    public abstract void setNazwa(String nazwa);

    public abstract Boolean getDoUsuniecia();

    public abstract void setDoUsuniecia(Boolean doUsuniecia);

    public abstract Boolean getAktualny();

    public abstract void setAktualny(Boolean aktualny);
}
