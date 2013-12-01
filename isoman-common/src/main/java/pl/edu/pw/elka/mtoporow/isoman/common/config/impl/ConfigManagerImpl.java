package pl.edu.pw.elka.mtoporow.isoman.common.config.impl;

import pl.edu.pw.elka.mtoporow.isoman.common.config.BasePropertyManager;
import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Menedżer konfiguracji
 * Data utworzenia: 30.11.13, 15:55
 *
 * @author Michał Toporowski
 */
public class ConfigManagerImpl extends AbstractPropertyManager implements ConfigManager {

    private static final String PROPERTIES_FILE = "config.properties";
    private final Properties properties;

    public ConfigManagerImpl() {
        this.properties = loadFromResource(PROPERTIES_FILE);
    }

    @Override
    protected Properties getProperties() {
        return properties;
    }
}
