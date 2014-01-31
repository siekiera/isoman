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
    table.find("a").button().find('.ui-button-text').css({'padding-top': '0', 'padding-bottom': '0'});
}

/**
 * Aktualizuje styl dla tabeli .ctab
 */
function applyContentTableStyle() {
    applyTableStyle($(".ctab"));
}