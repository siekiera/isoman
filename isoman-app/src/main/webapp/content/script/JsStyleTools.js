/**
 * Funkcje narzędziowe dla styli w JavaScripcie
 *
 * Autor: Michał Toporowski
 *
 */

/**
 * Aktualizuje styl dla tabeli
 *
 * @param table
 */
function applyTableStyle(table) {
    //style dla elementów tabelki
    table.find("th").each(function () {
        jQuery(this).addClass("ui-state-default");
    });
    table.find("td").each(function () {
        jQuery(this).addClass("ui-widget-content");
    });
    table.find("tr").hover(
        function () {
            jQuery(this).children("td").addClass("ui-state-hover");
        },
        function () {
            jQuery(this).children("td").removeClass("ui-state-hover");
        }
    ).click(function () {
            jQuery(this).children("td").toggleClass("ui-state-highlight");
        });

    //linki wewnątrz tabelek zamieniamy na przyciski, ale z mniejszym marginesem
    //zmieniamy tylko te linki, które nie są w li, objętym przez funkcję createMenuButton
    table.find("a").not("li a").button().find('.ui-button-text').css({'padding-top': '0', 'padding-bottom': '0'});
}

/*
 * Tworzy przycisk z rozwijanym menu
 * Uwaga: niezbędna struktura:
 * <tr><td>
 * <div>
 *     <button>Przycisk przekazany jako parametr</button>
 * </div>
 * <ul>
 *     <li>Elementy menu</li>
 *     ...
 * </ul>
 * </td></tr>
 */
function createMenuButton(button) {
    jQuery(button).button({
        text: false,
        icons: {
            primary: "ui-icon-triangle-1-e"
        }
    })
        .click(function () {
            var menu = jQuery(this).parent().next().css({"position": "absolute"}).show().position({
                my: "left top",
                at: "right top",
                of: jQuery(this).parent().parent().parent() //względem tr
            });
            jQuery(document).one("click", function () {
                menu.hide();
            });
            return false;
        })
        .parent()
        .next()
        .hide()
        .menu();
}
/**
 * Aktualizuje styl dla tabeli .ctab
 */
function applyContentTableStyle() {
    applyTableStyle($(".ctab"));
    $(".moreBtn").each(function () {
        createMenuButton(this);
    })
}