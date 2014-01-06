package pl.edu.pw.elka.mtoporow.isoman.generator;

import org.apache.log4j.Logger;
import pl.edu.pw.elka.mtoporow.isoman.common.util.StringTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Klasa służąca do wywoływania generacji
 * Data utworzenia: 05.01.14, 12:29
 *
 * @author Michał Toporowski
 */
public class ExecutionUtil {
    private static final Logger LOGGER = Logger.getLogger(ExecutionUtil.class);

    private static final String COMMAND = "mkisofs -input-charset utf8 -o %s %s";

    /**
     * Wykonuje generację dla podanych danych
     *
     * @return
     */
    public static void exec(final String isoDest, final String source) throws GeneratorException {
        LOGGER.trace(String.format("Executing archive generation for archive: %s", isoDest));
        String command = String.format(COMMAND, isoDest, source);
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(command);
            String out = readOutput(proc.getInputStream());
            String errors = readOutput(proc.getErrorStream());

            if (StringTool.containsNonWhiteChars(errors)) {
                LOGGER.warn("Error messages during execution: ".concat(errors));
            }
        } catch (IOException e) {
            LOGGER.error("Exception during generation execution", e);
            throw new GeneratorException("Exception during generation execution", e);
        }

    }

    /**
     * Odczytuje zawartość strumienia
     *
     * @param is
     * @return
     * @throws IOException
     */
    private static String readOutput(final InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
