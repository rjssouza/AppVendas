package com.app.appvenda.utils;

import com.app.appvenda.modelos.MVendedor;

/**
 * Created by Robson on 30/01/2017.
 */

public class InformacoesVendedor {

    private static MVendedor mVendedor;

    public static MVendedor getmVendedor() {
        return mVendedor;
    }

    public static void setmVendedor(MVendedor _mVendedor) {
        mVendedor = _mVendedor;
    }
}
