package pl.edu.pw.elka.mtoporow.isoman.generator;

/**
 * Wyjątek rzucany przez generator
 * Data utworzenia: 06.01.14, 11:08
 *
 * @author Michał Toporowski
 */
public class GeneratorException extends Exception {
    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratorException() {
        super();
    }

    public GeneratorException(String message) {
        super(message);
    }
}
