package pl.edu.pw.elka.mtoporow.isoman.services;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;
import pl.edu.pw.elka.mtoporow.isoman.services.exception.ServiceException;

/**
 * Serwis do zarządzania osobami
 * Data utworzenia: 21.11.13, 18:37
 *
 * @author Michał Toporowski
 */
@Deprecated
public interface PersonService {

    /**
     * Dodaje osobę, aktualizując jednocześnie użytkownika systemu bezpieczeństwa Ledge'a
     *
     * @param person
     * @param password
     * @throws ServiceException
     */
    void addPerson(Osoba person, String password) throws ServiceException;

    /**
     * Dodaje osobę, aktualizując jednocześnie użytkownika systemu bezpieczeństwa Ledge'a
     *
     * @param person
     * @param password
     * @param roleId
     * @throws ServiceException
     */
    void addPerson(Osoba person, String password, Long roleId) throws ServiceException;
}
