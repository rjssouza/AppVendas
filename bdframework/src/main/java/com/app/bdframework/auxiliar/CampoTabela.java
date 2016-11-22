package com.app.bdframework.auxiliar;

/**
 * Created by Robson on 12/11/2016.
 */

public @interface CampoTabela {
    String nomeCampo();
    boolean isprimary();
    TipoCampo tipoCampo();
}
