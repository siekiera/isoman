package pl.edu.pw.elka.mtoporow.isoman.modules.components;

import org.objectledge.context.Context;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.security.PolicyCheckerTool;
import org.objectledge.security.WebPolicyCheckerToolFactory;
import org.objectledge.templating.Template;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.mvc.builders.BuildException;
import org.objectledge.web.mvc.components.AbstractComponent;
import org.objectledge.web.mvc.tools.LinkTool;
import org.objectledge.web.mvc.tools.LinkToolFactory;
import pl.edu.pw.elka.mtoporow.isoman.common.util.CollectionTool;
import pl.edu.pw.elka.mtoporow.isoman.constants.MenuItems;

/**
 * Komponent menu aplikacji webowej
 * Data utworzenia: 08.01.14, 15:34
 *
 * @author Michał Toporowski
 */
public class Menu extends AbstractComponent {

    private final LinkToolFactory linkToolFactory;
    private final WebPolicyCheckerToolFactory webPolicyCheckerToolFactory;

    public Menu(Context context, LinkToolFactory linkToolFactory, WebPolicyCheckerToolFactory webPolicyCheckerToolFactory) {
        super(context);
        this.linkToolFactory = linkToolFactory;
        this.webPolicyCheckerToolFactory = webPolicyCheckerToolFactory;
    }

    @Override
    public String build(Template template) throws BuildException, ProcessingException {
        TemplatingContext tc = TemplatingContext.getTemplatingContext(context);
        MenuItems.MenuItem root = getFilteredItem(MenuItems.getMenuRoot());

        tc.put("menu", root);
        return super.build(template);
    }

    /**
     * Pobiera gałąź menu przefiltrowaną dla uprawnień użytkownika
     *
     * @param sourceItem
     * @return
     */
    public MenuItems.MenuItem getFilteredItem(MenuItems.MenuItem sourceItem) {
        MenuItems.MenuItem newItem = null;
        if (checkMenuItem(sourceItem)) {
            newItem = new MenuItems.MenuItem(sourceItem.getName(), sourceItem.getView(), sourceItem.getAction());
            if (CollectionTool.containsElements(sourceItem.getSubItems())) {
                for (MenuItems.MenuItem subItem : sourceItem.getSubItems()) {
                    MenuItems.MenuItem newSubItem = getFilteredItem(subItem);
                    if (newSubItem != null) {
                        newItem.addSubItem(newSubItem);
                    }
                }
                //jeśli nie jest linkiem i wszystkie podelementy niedostępne - usuń
                if (sourceItem.getView() == null && !CollectionTool.containsElements(newItem.getSubItems())) {
                    newItem = null;
                }
            }
        }
        return newItem;
    }

    /**
     * Sprawdza, czy użytkownik posiada uprawnienia do danego elementu menu
     *
     * @param item
     * @return
     */
    private boolean checkMenuItem(final MenuItems.MenuItem item) {
        LinkTool link = getLinkTool().view(item.getView());
        if (item.getAction() != null) {
            link = link.action(item.getAction());
        }
        PolicyCheckerTool policyCheckerTool = (PolicyCheckerTool) webPolicyCheckerToolFactory.getTool();
        return policyCheckerTool.check(link, false);
    }

    /**
     * Dostarcza LinkTool
     *
     * @return
     */
    private LinkTool getLinkTool() {
        try {
            return (LinkTool) linkToolFactory.getTool();
        } catch (ProcessingException e) {
            //should never happen
            return null;
        }
    }
}
