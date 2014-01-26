/**
 * Funkcje narzędziowe w javaScripcie
 *
 * Autor: Michał Toporowski
 *
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