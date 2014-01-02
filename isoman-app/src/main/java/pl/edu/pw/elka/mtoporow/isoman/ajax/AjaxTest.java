package pl.edu.pw.elka.mtoporow.isoman.ajax;

import org.objectledge.ajax.annotations.AjaxMethod;

import java.util.Date;

/**
 * Testowy serwis ajaksowy
 * Data utworzenia: 28.12.13, 00:30
 *
 * @author Michał Toporowski
 */
public class AjaxTest {

    @AjaxMethod("serverTime")
    public java.util.Date getTime()
    {
        return new Date();
    }
}
