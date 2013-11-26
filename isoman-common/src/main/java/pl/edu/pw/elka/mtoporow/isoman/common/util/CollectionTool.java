package pl.edu.pw.elka.mtoporow.isoman.common.util;

import java.util.Collection;
import java.util.List;

/**
 * Klasa narzędziowa do operacji na kolekcjach
 * Data utworzenia: 25.11.13, 22:05
 *
 * @author Michał Toporowski
 */
public class CollectionTool {

    /**
     * Sprawdza, czy kolekcja zawiera elementy
     *
     * @param c kolekcja
     * @return true, jeśli c != null i kolekcja nie jest pusta
     */
    public static boolean containsElements(Collection c) {
        return c != null && !c.isEmpty();
    }

    /**
     * Pobiera pierwszy element z listy
     *
     * @param list lista
     * @param <T>
     * @return pierwszy element z listy, lub null, jeśli taki nie istnieje
     */
    public static <T> T getFirst(List<T> list) {
        if (containsElements(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
