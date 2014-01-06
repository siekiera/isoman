package pl.edu.pw.elka.mtoporow.isoman.services;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.WersjaArchiwum;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.ArchiveReport;
import pl.edu.pw.elka.mtoporow.isoman.services.entity.FSReport;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

import java.util.List;

/**
 * Serwis do aktualizacji struktury systemu plików
 * Data utworzenia: 15.12.13, 19:53
 *
 * @author Michał Toporowski
 */
public interface FSUpdateService {

    /**
     * Tworzy raport dla danego archiwum
     *
     * @param archiwum          archiwum
     * @param includeNotChanged czy uwzględnić niezmienione obiekty
     * @return lista obiektów FSReport
     */
    public List<FSReport> reportUpdates(final Archiwum archiwum, final boolean includeNotChanged);

    /**
     * Tworzy raport dla wszystkich archiwów
     *
     * @return lista obiektół ArchiveReport
     */
    public List<ArchiveReport> reportUpdatedArchives();

    /**
     * Aktualizuje strukturę dla danego archiwum
     *
     * @param archiveId      id archiwum
     * @param switchVersions czy przełączyć pokazywaną wersję
     * @return encja nowej wersji archiwum
     */
    public WersjaArchiwum update(final long archiveId, final boolean switchVersions) throws ServiceException;
}
