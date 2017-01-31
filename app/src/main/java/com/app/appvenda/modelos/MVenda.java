package com.app.appvenda.modelos;

import com.app.appvenda.enums.EnumStatusVenda;

/**
 * Created by Robson on 30/11/2016.
 */

public class MVenda {

    private int idVenda;
    private boolean sincronizado;

    private MCliente mCliente;
    private MVendedor mVendedor;
    private MPedido mPedido;
    private EnumStatusVenda enumStatusVenda;

    public MCliente getmCliente() {
        return mCliente;
    }

    public void setmCliente(MCliente mCliente) {
        this.mCliente = mCliente;
    }

    public MVendedor getmVendedor() {
        return mVendedor;
    }

    public void setmVendedor(MVendedor mVendedor) {
        this.mVendedor = mVendedor;
    }

    public MPedido getmPedido() {
        return mPedido;
    }

    public void setmPedido(MPedido mPedido) {
        this.mPedido = mPedido;
    }

    public EnumStatusVenda getEnumStatusVenda() {
        return enumStatusVenda;
    }

    public void setEnumStatusVenda(EnumStatusVenda enumStatusVenda) {
        this.enumStatusVenda = enumStatusVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

}
