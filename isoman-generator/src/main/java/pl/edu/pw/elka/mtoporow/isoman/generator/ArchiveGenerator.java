package pl.edu.pw.elka.mtoporow.isoman.generator;

/**
 * Interfejs generatora archiwów
 * Data utworzenia: 05.01.14, 12:19
 *
 * @author Michał Toporowski
 */
public interface ArchiveGenerator {

    /**
     * Generuje archiwum
     *
     * @param source        nazwa katalogu głównego archiwum do wygenerowania
     * @param versionNumber nr wersji nowego archiwum
     * @return pełna ścieżka do wygenerowanego pliku
     */
    String generate(final String source, final long versionNumber) throws GeneratorException;
}
