package pl.edu.pw.elka.mtoporow.isoman.common.config.impl;

import pl.edu.pw.elka.mtoporow.isoman.common.config.BasePropertyManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Abstrakcyjna implementacja menedżera konfiguracji
 * Data utworzenia: 01.12.13, 11:11
 *
 * @author Michał Toporowski
 */
public abstract class AbstractPropertyManager implements BasePropertyManager {

    @Override
    public String get(final String key) {
        return getProperties().getProperty(key);
    }

    @Override
    public String get(final String key, final String defValue) {
        return getProperties().getProperty(key, defValue);
    }

    /**
     * Pobiera properties dla danej implementacji
     *
     * @return
     */
    protected abstract Properties getProperties();

    /**
     * Ładuje plik properties z zasobów
     *
     * @param filename nazwa pliku properties
     * @return zainicjalizowane properties
     */
    protected Properties loadFromResource(final String filename) {
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
            properties.load(is);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Error while initializing properties", e);
        }
    }
}
