package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Venda;
import com.app.appvenda.modelos.MVenda;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 09/01/2017.
 */

public class ConversorVenda extends Conversor<MVenda, Venda> {
    @Override
    public Venda converterDePara(MVenda mVenda) {
        Venda venda = new Venda(null);
        if (mVenda.getmVendedor() != null)
            venda.setId_vendedor(mVenda.getmVendedor().getIdVendedor());
        if (mVenda.getmCliente() != null)
            venda.setId_cliente(mVenda.getmCliente().getId());
        if (mVenda.getmVendedor() != null)
            venda.setId_vendedor(mVenda.getmVendedor().getIdVendedor());
        if (mVenda.getmPedido() != null)
            venda.setId_pedido(mVenda.getmPedido().getIdPedido());
        if (mVenda.getEnumStatusVenda() != null)
            venda.setId_status_venda(mVenda.getEnumStatusVenda().getNumVal());
        venda.setSincronizado(mVenda.isSincronizado());
        venda.setId_venda(mVenda.getIdVenda());
        return venda;
    }

    @Override
    public MVenda converterParaDe(Venda venda) {
        MVenda mVenda = new MVenda();
        mVenda.setIdVenda(venda.getId_venda());
        mVenda.setSincronizado(venda.isSincronizado());
        return mVenda;
    }
}
