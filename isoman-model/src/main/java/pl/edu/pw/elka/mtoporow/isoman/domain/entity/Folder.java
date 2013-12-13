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
    private Long fsid;
    private String nazwa;
    private Boolean aktualny;
    private Boolean doUsuniecia;
    private Folder nadrzedny;
    private List<Folder> podrzedne;
    private List<Plik> pliki;

    /**
     * Konstruktor domyślny
     */
    public Folder() {
    }

    public Folder(Long fsid, String nazwa, Boolean aktualny, Boolean doUsuniecia, Folder nadrzedny) {
        this.fsid = fsid;
        this.nazwa = nazwa;
        this.aktualny = aktualny;
        this.doUsuniecia = doUsuniecia;
        this.nadrzedny = nadrzedny;
    }

    public Long getFsid() {
        return fsid;
    }

    public void setFsid(Long fsid) {
        this.fsid = fsid;
    }

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

    public Boolean getAktualny() {
        return aktualny;
    }

    public void setAktualny(Boolean aktualny) {
        this.aktualny = aktualny;
    }

    public Boolean getDoUsuniecia() {
        return doUsuniecia;
    }

    public void setDoUsuniecia(Boolean doUsuniecia) {
        this.doUsuniecia = doUsuniecia;
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
}
