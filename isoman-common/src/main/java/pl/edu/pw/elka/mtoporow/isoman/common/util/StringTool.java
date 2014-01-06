package pl.edu.pw.elka.mtoporow.isoman.common.util;

/**
 * Klasa narzędziowa do operacji na łańcuchach
 * Data utworzenia: 05.01.14, 12:45
 *
 * @author Michał Toporowski
 */
public class StringTool {
    /**
     * Sprawdza, czy łańcuch zawiera elementy
     *
     * @param c łańcuch
     * @return true, jeśli c != null i łańcuch nie jest pusty
     */
    public static boolean containsElements(String c) {
        return c != null && !c.isEmpty();
    }

    /**
     * Sprawdza, czy łańcuch zawiera elementy inne, niż białe znaki
     *
     * @param c łańcuch
     * @return true, jeśli c != null i łańcuch nie jest pusty
     */
    public static boolean containsNonWhiteChars(String c) {
        return c != null && !c.trim().isEmpty();
    }
}
