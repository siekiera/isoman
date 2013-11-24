// 
// Copyright (c) 2003, Caltha - Gajda, Krzewski, Mach, Potempski Sp.J. 
// All rights reserved. 
// 
// Redistribution and use in source and binary forms, with or without modification,  
// are permitted provided that the following conditions are met: 
//  
// * Redistributions of source code must retain the above copyright notice,  
//	 this list of conditions and the following disclaimer. 
// * Redistributions in binary form must reproduce the above copyright notice,  
//	 this list of conditions and the following disclaimer in the documentation  
//	 and/or other materials provided with the distribution. 
// * Neither the name of the Caltha - Gajda, Krzewski, Mach, Potempski Sp.J.  
//	 nor the names of its contributors may be used to endorse or promote products  
//	 derived from this software without specific prior written permission. 
// 
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"  
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED  
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
// IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,  
// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,  
// BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
// OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,  
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)  
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE  
// POSSIBILITY OF SUCH DAMAGE. 
// 
package pl.edu.pw.elka.mtoporow.isoman.modules.views;

import org.objectledge.context.Context;
import org.objectledge.i18n.I18nContext;
import org.objectledge.modules.components.security.menu.SecureMenuCoolmenusParam;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.HttpContext;
import org.objectledge.web.mvc.MVCContext;
import org.objectledge.web.mvc.builders.AbstractBuilder;
import org.objectledge.web.mvc.builders.BuildException;

/**
 * A default view.
 *  
 * @author <a href="mailto:rafal@caltha.pl">Rafal Krzewski</a>
 * @version $Id: Default.java,v 1.6 2011-06-21 12:38:25 mgolebsk Exp $
 */
public class Default extends AbstractBuilder
{
    private static final SecureMenuCoolmenusParam MENU_PARAM = new SecureMenuCoolmenusParam("default");
   /**
     * Creates new Default builder instance.
     * 
     * @param context the processing context.
     */
    public Default(Context context)
    {
        super(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build(final Template template, final String embeddedBuildResults)
    throws BuildException, ProcessingException
    {
        
        final TemplatingContext templatingContext = TemplatingContext.getTemplatingContext(context);
        templatingContext.put("i18nContext", I18nContext.getI18nContext(context));
        templatingContext.put("mvcContext", MVCContext.getMVCContext(context));
        templatingContext.put("httpContext", HttpContext.getHttpContext(context));
        templatingContext.put("cparam", MENU_PARAM);
        
        return super.build(template, embeddedBuildResults);
    }
    
}
