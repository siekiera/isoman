/*
* Inicjalizacja mechanizmów jQuery na stronie
* Autor: Michał Toporowski
 */

//Inicjalizacja jQuery
jQuery(document).ready(function() {
    //menu
    jQuery("#menu").children("ul").menubar();
    //style dla tabelek
    applyContentTableStyle();
    //style dla przycisków
    $("input[type=submit], button").button();

    //inicjalizacja dialogów
    infoDialog($(".action-result, .action-error-result"));

    //inicjalna wysokość tabelki
    $("#maintbl").css({"height": $(document).height() - 25 + "px"});

    //wyśrodkowanie menu
    function centerUl(ul) {
        var lastChild = ul.children("li:last");
        var realWidth = lastChild.offset().left + lastChild.width() - ul.offset().left;
        var offset = (ul.innerWidth() - realWidth) / 2;
        ul.css({"padding-left": offset + "px"});
    }
    centerUl($("#menu").children("ul"));


});
