package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.entidade.StatusVenda;
import com.app.appvenda.entidade.Venda;
import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.enums.EnumStatusVenda;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MPedido;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.conversor.Conversor;
import com.app.bdframework.conversor.ConversorHelper;

/**
 * Created by Robson on 09/01/2017.
 */

public class ConversorVenda extends Conversor<MVenda, Venda> {
    @Override
    public Venda converterDePara(MVenda mVenda) {
        Venda venda = new Venda(null);

        venda.setSincronizado(mVenda.isSincronizado());
        venda.setId_venda(mVenda.getIdVenda());

        if (mVenda.getmVendedor() != null) {
            venda.setId_vendedor(mVenda.getmVendedor().getIdVendedor());
            venda.setVendedor(ConversorHelper.converterDePara(mVenda.getmVendedor(), Vendedor.class));
        }
        if (mVenda.getmCliente() != null) {
            venda.setId_cliente(mVenda.getmCliente().getId());
            venda.setCliente(ConversorHelper.converterDePara(mVenda.getmCliente(), Cliente.class));
        }
        if (mVenda.getmPedido() != null) {
            venda.setId_pedido(mVenda.getmPedido().getIdPedido());
            venda.setPedido(ConversorHelper.converterDePara(mVenda.getmPedido(), Pedido.class));
        }
        if (mVenda.getEnumStatusVenda() != null) {
            venda.setId_status_venda(mVenda.getEnumStatusVenda().getNumVal());
            venda.setStatusVenda(ConversorHelper.converterDePara(mVenda.getEnumStatusVenda(), StatusVenda.class));
        }
        venda.setStatusVenda(ConversorHelper.converterDePara(mVenda.getEnumStatusVenda(), StatusVenda.class));

        return venda;
    }

    @Override
    public MVenda converterParaDe(Venda venda) {
        MVenda mVenda = new MVenda(ConversorHelper.converterParaDe(venda.getVendedor(), MVendedor.class));

        mVenda.setIdVenda(venda.getId_venda());
        mVenda.setSincronizado(venda.isSincronizado());

        mVenda.setmCliente(ConversorHelper.converterParaDe(venda.getCliente(), MCliente.class));
        mVenda.setmPedido(ConversorHelper.converterParaDe(venda.getPedido(), MPedido.class));

        return mVenda;
    }
}
