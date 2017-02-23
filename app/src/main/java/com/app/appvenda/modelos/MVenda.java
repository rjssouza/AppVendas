package com.app.appvenda.modelos;

import com.app.appvenda.enums.EnumStatusVenda;
import com.app.appvenda.utils.InformacoesVendedor;

/**
 * Created by Robson on 30/11/2016.
 */

public class MVenda {

    private Long idVenda;
    private boolean sincronizado;

    private MCliente mCliente;
    private MVendedor mVendedor;
    private MPedido mPedido;
    private EnumStatusVenda enumStatusVenda;

    public MVenda(MVendedor mVendedor) {
        mPedido = new MPedido();
        enumStatusVenda = EnumStatusVenda.NAO_PAGO;
        sincronizado = false;

        this.mVendedor = mVendedor;
    }

    public MCliente getmCliente() {
        return mCliente;
    }

    public void setmCliente(MCliente mCliente) {
        this.mCliente = mCliente;
    }

    public MVendedor getmVendedor() {
        return mVendedor;
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

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

}
