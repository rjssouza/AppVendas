package com.app.appvenda.utils;

import android.content.Context;

import com.app.appvenda.dao.VendedorDAO;
import com.app.appvenda.modelos.MVendedor;

/**
 * Created by Robson on 30/01/2017.
 */

public class InformacoesVendedor {

    private MVendedor mVendedor;

    private VendedorDAO vendedorDAO;

    private static InformacoesVendedor informacoesVendedor;

    public static InformacoesVendedor getInstance(Context context) {
        if (informacoesVendedor == null || informacoesVendedor.mVendedor == null) {
            informacoesVendedor = new InformacoesVendedor(context);
        }
        return informacoesVendedor;
    }

    private InformacoesVendedor(Context context) {
        vendedorDAO = new VendedorDAO(context);
        mVendedor = vendedorDAO.obterVendedorAtivo();
    }

    public static MVendedor getmVendedor(Context context) {
        InformacoesVendedor informacoesVendedor = InformacoesVendedor.getInstance(context);
        return informacoesVendedor.mVendedor;
    }

    public static String getNomeVendedor(Context context){
        MVendedor mVendedor = InformacoesVendedor.getmVendedor(context);
        if(mVendedor != null) {
            return mVendedor.getNome();
        }
        return "";
    }
}
