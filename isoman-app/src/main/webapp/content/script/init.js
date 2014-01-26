/*
* Inicjalizacja mechanizmów jQuery na stronie
* Autor: Michał Toporowski
 */

//Inicjalizacja jQuery
jQuery(document).ready(function() {
    //menu
    jQuery("#menu").children("ul").menubar();
    //style dla tabelek
    jQuery(".ctab th").each(function(){

        jQuery(this).addClass("ui-state-default");

    });
    jQuery(".ctab td").each(function(){

        jQuery(this).addClass("ui-widget-content");

    });
    jQuery(".ctab tr").hover(
        function()
        {
            jQuery(this).children("td").addClass("ui-state-hover");
        },
        function()
        {
            jQuery(this).children("td").removeClass("ui-state-hover");
        }
    );
    jQuery(".ctab tr").click(function(){

        jQuery(this).children("td").toggleClass("ui-state-highlight");
    });
    //style dla przycisków
    $("input[type=submit], button").button();
    //linki wewnątrz tabelek też, ale z mniejszym marginesem
    $(".ctab a").button().find('.ui-button-text').css({'padding-top': '0', 'padding-bottom': '0'});
    //inicjalna wysokość tabelki
    $("#maintbl").css({"height": $(document).height() - 25 + "px"});

});
