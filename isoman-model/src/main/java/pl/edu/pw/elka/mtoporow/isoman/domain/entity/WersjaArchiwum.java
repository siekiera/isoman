package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.id.WersjaArchiwumId;

import java.util.Date;

/**
 * Encja wersji archiwum
 * Data utworzenia: 10.11.13, 15:17
 *
 * @author Michał Toporowski
 */
public class WersjaArchiwum extends UniqueEntity<WersjaArchiwumId> {
    private WersjaArchiwumId id;
    private Date dataUtworzenia;
    private Boolean pokazywana;
    private String sciezka;
    private String opis;
    private Archiwum archiwum;

    public WersjaArchiwum() {
    }

    public WersjaArchiwum(WersjaArchiwumId id, Date dataUtworzenia, Boolean pokazywana, String sciezka, String opis, Archiwum archiwum) {
        this.id = id;
        this.dataUtworzenia = dataUtworzenia;
        this.pokazywana = pokazywana;
        this.sciezka = sciezka;
        this.opis = opis;
        this.archiwum = archiwum;
    }

    public WersjaArchiwumId getId() {
        return id;
    }

    public void setId(WersjaArchiwumId id) {
        this.id = id;
    }

    public Date getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Date dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public Boolean getPokazywana() {
        return pokazywana;
    }

    public void setPokazywana(Boolean pokazywana) {
        this.pokazywana = pokazywana;
    }

    public String getSciezka() {
        return sciezka;
    }

    public void setSciezka(String sciezka) {
        this.sciezka = sciezka;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Archiwum getArchiwum() {
        return archiwum;
    }

    public void setArchiwum(Archiwum archiwum) {
        this.archiwum = archiwum;
    }
}
