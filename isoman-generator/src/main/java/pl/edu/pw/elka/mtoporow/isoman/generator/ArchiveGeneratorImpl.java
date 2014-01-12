package pl.edu.pw.elka.mtoporow.isoman.generator;

import pl.edu.pw.elka.mtoporow.isoman.common.config.ConfigManager;

import java.io.File;

/**
 * Implementacja generatora archiwów
 * Data utworzenia: 05.01.14, 12:20
 *
 * @author Michał Toporowski
 */
public class ArchiveGeneratorImpl implements ArchiveGenerator {

    private final ConfigManager configManager;
    private final String outputRoot;
    private final String sourceRoot;

    /**
     * Konstruktor
     *
     * @param configManager
     */
    public ArchiveGeneratorImpl(ConfigManager configManager) {
        this.configManager = configManager;

        this.outputRoot = configManager.get("generator.out.dir");
        this.sourceRoot = configManager.get("notifier.root.dir");
    }


    @Override
    public String generate(String source, long versionNumber) throws GeneratorException {
        String destDir = appendPath(outputRoot, source);
        String filename = versionNumber + ".iso";
        String isoDest = appendPath(destDir, filename);
        String relDest = appendPath(source, filename);
        createFoldersIfNotExist(destDir);
        ExecutionUtil.exec(isoDest, appendPath(sourceRoot, source));
        return relDest;
    }

    /**
     * Tworzy folder o podanej ścieżce razem z nadfolderami, jeśli nie istnieją
     *
     * @param path ścieżka
     */
    private void createFoldersIfNotExist(final String path) throws GeneratorException {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean success = dir.mkdirs();
            if (!success) {
                throw new GeneratorException(String.format("Creating directory %s failed", path));
            }
        }
    }

    /**
     * Dodaje człon do ścieżki
     *
     * @param path
     * @param rest
     * @return
     */
    private String appendPath(final String path, final String rest) {
        return path + "/" + rest;
    }
}
