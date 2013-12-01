package pl.edu.pw.elka.mtoporow.isoman.common.config;

/**
 * Abstrakcyjny Menedżer konfiguracji
 * Data utworzenia: 01.12.13, 10:57
 *
 * @author Michał Toporowski
 */
public interface BasePropertyManager {

    /**
     * Pobiera parametr na podstawie klucza
     *
     * @param key
     * @return
     */
    String get(final String key);

    /**
     * Pobiera parametr na podstawie klucza
     *
     * @param key
     * @param defValue
     * @return
     */
    String get(final String key, final String defValue);
}
