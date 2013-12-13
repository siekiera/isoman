package pl.edu.pw.elka.mtoporow.isoman.notifier.watcher.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Klasa pomocnicza do pobierania Inode'ów dla danego pliku
 * Data utworzenia: 07.12.13, 14:32
 *
 * @author Michał Toporowski
 */
public class InodeUtil {

    private static final Logger LOGGER = Logger.getLogger(InodeUtil.class);

    private static final String COMMAND = "stat -c %i ";

    /**
     * Pobiera nr inode'a dla danego pliku
     *
     * @param filePath
     * @return
     */
    public static Long getInodeNr(final String filePath) {
        Long result = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(COMMAND + filePath);
            String s = readLine(proc.getInputStream());

            if (s != null) {
                result = Long.parseLong(s);
                LOGGER.debug(String.format("Fetched inode: %s for file: %s", result, filePath));
            }
        } catch (IOException | NumberFormatException e) {
            LOGGER.error("Exception during inode fetching", e);
        }
        return result;
    }

    /**
     * Odczytuje pojedynczą linię ze strumienia
     *
     * @param is
     * @return
     * @throws IOException
     */
    private static String readLine(final InputStream is) throws IOException {
        try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(is))) {
            return stdInput.readLine();
        }
    }
}
