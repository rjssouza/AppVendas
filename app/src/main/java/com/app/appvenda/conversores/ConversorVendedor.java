package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 09/01/2017.
 */

public class ConversorVendedor extends Conversor<MVendedor, Vendedor> {

    @Override
    public Vendedor converterDePara(MVendedor mVendedor) {
        Vendedor vendedor = new Vendedor(null);
        vendedor.setId_vendedor(mVendedor.getIdVendedor());
        vendedor.setNome(mVendedor.getNome());
        vendedor.setPerc_venda(mVendedor.getPercVenda());
        return vendedor;
    }

    @Override
    public MVendedor converterParaDe(Vendedor vendedor) {
        MVendedor mVendedor = new MVendedor();
        mVendedor.setNome(vendedor.getNome());
        mVendedor.setIdVendedor(vendedor.getId_vendedor());
        mVendedor.setPercVenda(vendedor.getPerc_venda());
        return mVendedor;
    }
}
