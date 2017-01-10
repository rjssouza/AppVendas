package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Venda;
import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.modelos.MVenda;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 09/01/2017.
 */

public class ConversorVenda extends Conversor<MVenda, Venda> {
    @Override
    public Venda converterDePara(MVenda mVenda) {
        Venda venda = new Venda(null);
        venda.setId_vendedor(mVenda.getIdVendedor());
        venda.setId_cliente(mVenda.getIdCliente());
        venda.setId_vendedor(mVenda.getIdVendedor());
        venda.setId_pedido(mVenda.getIdPedido());
        venda.setId_status_venda(mVenda.getIdStatusVenda());
        venda.setSincronizado(mVenda.isSincronizado());
        return venda;
    }

    @Override
    public MVenda converterParaDe(Venda venda) {
        MVenda mVenda = new MVenda();
        mVenda.setIdVendedor(venda.getId_vendedor());
        mVenda.setIdCliente(venda.getId_cliente());
        mVenda.setIdVendedor(venda.getId_vendedor());
        mVenda.setIdPedido(venda.getId_pedido());
        mVenda.setIdStatusVenda(venda.getId_status_venda());
        mVenda.setSincronizado(venda.isSincronizado());
        return mVenda;
    }
}
