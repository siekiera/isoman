/**
 * Created with IntelliJ IDEA.
 * User: michalek
 * Date: 29.12.13
 * Time: 13:21
 *
 * Funkcje narżędziowe do obsługi Ajaxu
 */

function postAjax(url, service, method, params, callback) {
    $.post( url, JSON.stringify({service: service, method: method, params: params}), function(response) {
        callback(response.returnedValue);
    });
}

