package pl.edu.pw.elka.mtoporow.isoman.ol.extension;

import org.objectledge.context.Context;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import pl.edu.pw.elka.mtoporow.isoman.common.util.CollectionTool;

import java.util.Collection;

/**
 * Klasa nadrzędna dla wszystkich widoku
 */
public abstract class AbstractView extends AbstractBuilder {
    /**
     * Konstruuje widok
     *
     * @param context kontekst Ledge'a
     */
    public AbstractView(Context context) {
        super(context);
    }

    /**
     * Pobiera parametry zapytania
     *
     * @return
     */
    protected RequestParameters getRequestParameters() {
        return RequestParameters.getRequestParameters(context);
    }

    /**
     * Pobiera TemplatingContext
     *
     * @return
     */
    protected TemplatingContext getTemplatingContext() {
        return TemplatingContext.getTemplatingContext(context);
    }

    /**
     * Wstawia kolekcję do templatingContextu tylko, jeśli jest niepusta
     *
     * @param templatingContext
     * @param key
     * @param c
     */
    protected void putIfNotEmpty(final TemplatingContext templatingContext, final String key, final Collection c) {
        if (CollectionTool.containsElements(c)) {
            templatingContext.put(key, c);
        }
    }

    /**
     * Wstawia obiekt do kontekstu, jeśli jest niepusty
     *
     * @param templatingContext
     * @param object
     */
    protected void putIfNotNull(final TemplatingContext templatingContext, final String key, final Object object) {
        if (object != null) {
            templatingContext.put(key, object);
        }
    }
}
