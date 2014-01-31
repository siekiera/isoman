/**
 * Funkcje narzędziowe w javaScripcie
 *
 * Autor: Michał Toporowski
 *
 */

/**
 * Tworzy dialog jQuery
 *
 * @param object DOM z treścią
 */
function infoDialog(object) {
    object.dialog({
        modal: true,
        buttons: {
            Ok: function() {
                $( this ).dialog( "close" );
            }
        }
    });
}