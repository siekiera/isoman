package pl.edu.pw.elka.mtoporow.isoman.domain.entity;

/**
 * Encja pliku
 * Data utworzenia: 10.11.13, 16:16
 *
 * @author Michał Toporowski
 */
public class Plik extends FSEntity<Long> {
    private Long id;
    private Folder folder;
    private String nazwa;
    private Boolean aktualny;
    private Boolean doUsuniecia;

    public Plik() {
    }

    public Plik(Folder folder, String nazwa, Boolean aktualny, Boolean doUsuniecia) {
        this.folder = folder;
        this.nazwa = nazwa;
        this.aktualny = aktualny;
        this.doUsuniecia = doUsuniecia;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
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
}
