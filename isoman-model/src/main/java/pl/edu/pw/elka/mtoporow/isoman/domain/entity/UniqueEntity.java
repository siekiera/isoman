package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

/**
 * Klasa reprezentująca encję z identyfikatorem
 *
 * @author Michał Toporowski
 */
public abstract class UniqueEntity<IDTYPE> extends GenericEntity {

    /**
     * Pobiera id
     * @return
     */
    public abstract IDTYPE getId();

    /**
     * Ustawia id
     *
     * @param id
     */
    public abstract void setId(IDTYPE id);

}
