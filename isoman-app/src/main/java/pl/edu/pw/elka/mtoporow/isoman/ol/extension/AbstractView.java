package pl.edu.pw.elka.mtoporow.isoman.ol.extension;

import org.hibernate.Session;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;
import org.objectledge.parameters.RequestParameters;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;

public class AbstractView extends AbstractBuilder {
    public AbstractView(Context context) {
        super(context);
    }

    protected RequestParameters getRequestParameters(Context context) {
        return RequestParameters.getRequestParameters(context);
    }

    protected Session getSession(Context context) {
        return HibernateSessionContext.getHibernateSessionContext(context).getSession();
    }

    protected TemplatingContext getTemplatingContext(Context context) {
        return TemplatingContext.getTemplatingContext(context);
    }

    protected TemplatingContext getTemplatingContext() {
        return TemplatingContext.getTemplatingContext(context);
    }
}
