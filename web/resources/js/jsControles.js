/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function limpiarFormulario(idFormulario) {
    document.getElementById(idFormulario).reset();
}

//function limpiaProveedor(frmRegistrarProveedores){
//    document.getElementById(frmRegistrarProveedores);
//}
function validaRFC(e, solicitar) {
    // Admitir solo letras
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return true;
    patron = /[a-zA-Z0-9]/;
    te = String.fromCharCode(tecla);
    if (!patron.test(te))
        return false;
}
function validar(e, solicitar) {
    // Admitir solo letras
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return true;
    patron = /[\D\s]/;
    te = String.fromCharCode(tecla);
    if (!patron.test(te))
        return false;
    // No admitir espacios iniciales y convertir 1ª letra a mayúscula
    txt = solicitar.value;

    if (txt.length == 0 || txt.substr(txt.length - 1, 1) == ' ') {
        solicitar.value = txt + te.toUpperCase();
        return false;
    }
}

function valida(e) {
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla == 8) {
        return true;
    }

    // Patron de entrada, en este caso solo acepta numeros
    patron = /[0-9]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}

