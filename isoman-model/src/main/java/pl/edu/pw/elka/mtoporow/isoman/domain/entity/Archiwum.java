package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

import java.util.List;

/**
 * Encja archiwum
 * Data utworzenia: 03.11.13, 12:27
 *
 * @author Michał Toporowski
 */
public class Archiwum extends UniqueEntity<Long> {

    private Long id;
    private String nazwa;
    private String opis;
    private Przedmiot przedmiot;
    private Folder folderGlowny;
    private List<WersjaArchiwum> wersje;

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

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    public Folder getFolderGlowny() {
        return folderGlowny;
    }

    public void setFolderGlowny(Folder folderGlowny) {
        this.folderGlowny = folderGlowny;
    }

    public List<WersjaArchiwum> getWersje() {
        return wersje;
    }

    public void setWersje(List<WersjaArchiwum> wersje) {
        this.wersje = wersje;
    }
}
