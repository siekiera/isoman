package pl.edu.pw.elka.mtoporow.isoman.services.exception;

/**
 * Klasa wyjątków rzucanych przez metody serwisowe
 * Data utworzenia: 22.11.13, 10:48
 *
 * @author Michał Toporowski
 */
public class ServiceException extends Exception {

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
