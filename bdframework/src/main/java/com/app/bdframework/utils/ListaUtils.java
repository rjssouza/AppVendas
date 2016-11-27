package com.app.bdframework.utils;

/**
 * Created by Robson on 27/11/2016.
 */

public class ListaUtils {
    public static <TValor> boolean contem(TValor[] listaValidar, TValor valor) {
        if (listaValidar != null) {
            for (TValor tValor : listaValidar) {
                if (tValor.equals(valor))
                    return true;
            }
        }
        return false;
    }
}
