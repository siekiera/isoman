package pl.edu.pw.elka.mtoporow.isoman.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu aplikacji webowej
 * Data utworzenia: 08.01.14, 18:20
 *
 * @author Michał Toporowski
 */
public class MenuItems {
    /**
     * Główny element menu
     */
    private static final MenuItem MENU_ROOT = new MenuItem();
    /**
     * Element login - dołączany dodatkowo
     */
    private static final MenuItem MENU_LOGIN_ITEM = new MenuItem("Zaloguj", "Login");

    static {
        MENU_ROOT.addSubItem(new MenuItem("Strona główna", "Index"));

        MenuItem subjectsMenu = new MenuItem("Przedmioty", null);
        subjectsMenu.addSubItem(new MenuItem("Lista", "subject.UserSubjectIndex"));
        subjectsMenu.addSubItem(new MenuItem("Zarządzanie", "subject.SubjectIndex"));

        MENU_ROOT.addSubItem(subjectsMenu);

        MenuItem manualsMenu = new MenuItem("Podręczniki", null);
        manualsMenu.addSubItem(new MenuItem("Lista", "archive.UserArchivesIndex"));

        MENU_ROOT.addSubItem(manualsMenu);

        MenuItem personsMenu = new MenuItem("Osoby", null);
        personsMenu.addSubItem(new MenuItem("Lista", "person.PersonIndex"));
        personsMenu.addSubItem(new MenuItem("Dodaj", "person.AddPerson"));
        MENU_ROOT.addSubItem(personsMenu);

        MENU_ROOT.addSubItem(new MenuItem("Aktualizacja", "update.ArchivesForUpdateIndex"));
        MENU_ROOT.addSubItem(new MenuItem("Wyloguj", "Login", "security.authentication.Logout"));
    }

    public static MenuItem getMenuRoot() {
        return MENU_ROOT;
    }

    public static MenuItem getMenuLoginItem() {
        return MENU_LOGIN_ITEM;
    }

    /**
     * Element menu
     */
    public static class MenuItem {
        private String name;
        private String view;
        private String action;
        private List<MenuItem> subItems;

        public MenuItem() {
        }

        public MenuItem(String name, String view) {
            this.name = name;
            this.view = view;
        }

        public MenuItem(String name, String view, String action) {
            this.name = name;
            this.view = view;
            this.action = action;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setView(String view) {
            this.view = view;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getName() {
            return name;
        }

        public String getView() {
            return view;
        }

        public String getAction() {
            return action;
        }

        public List<MenuItem> getSubItems() {
            return subItems;
        }

        public void setSubItems(List<MenuItem> subItems) {
            this.subItems = subItems;
        }

        public void addSubItem(MenuItem subItem) {
            if (subItems == null) {
                subItems = new ArrayList<>();
            }
            subItems.add(subItem);
        }
    }
}
