package com.app.appvenda.conversores;

import com.app.appvenda.entidade.StatusVenda;
import com.app.appvenda.enums.EnumStatusVenda;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 02/02/2017.
 */

public class ConversorStatusVenda extends Conversor<EnumStatusVenda, StatusVenda> {

    @Override
    public StatusVenda converterDePara(EnumStatusVenda enumStatusVenda) {
        StatusVenda statusVenda = new StatusVenda(null);
        statusVenda.setId_status_venda(enumStatusVenda.getNumVal());
        statusVenda.setCod_status(enumStatusVenda.getNumVal());
        statusVenda.setDescr_status(enumStatusVenda.toString());
        return statusVenda;
    }

    @Override
    public EnumStatusVenda converterParaDe(StatusVenda statusVenda) {

        if (statusVenda.getId_status_venda() == 2)
            return EnumStatusVenda.NAO_PAGO;
        else
            return EnumStatusVenda.PAGO;

    }
}
