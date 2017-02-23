package com.app.appvenda.conversores;

import com.app.appvenda.entidade.TipoPedido;
import com.app.appvenda.enums.EnumTipoPedido;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 02/02/2017.
 */

public class ConversorTipoPedido extends Conversor<EnumTipoPedido, TipoPedido> {
    @Override
    public TipoPedido converterDePara(EnumTipoPedido enumTipoPedido) {
        TipoPedido tipoPedido = new TipoPedido(null);
        tipoPedido.setId_tipo_pedido(Long.parseLong(enumTipoPedido.getNumVal().toString()));
        tipoPedido.setCod_tipo_pedido(enumTipoPedido.getNumVal());
        tipoPedido.setDescr_tipo_pedido(enumTipoPedido.toString());
        return tipoPedido;
    }

    @Override
    public EnumTipoPedido converterParaDe(TipoPedido tipoPedido) {
        switch (tipoPedido.getCod_tipo_pedido()) {
            case 1:
                return EnumTipoPedido.VENDA;
            case 2:
                return EnumTipoPedido.TROCA;
            default:
                return EnumTipoPedido.DEVOLUCAO;
        }
    }
}
