package com.app.appvenda.conversores;

import com.app.appvenda.entidade.FormaPagto;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 09/01/2017.
 */

public class ConversorFormaPagto extends Conversor<MFormaPagamento, FormaPagto> {

    @Override
    public FormaPagto converterDePara(MFormaPagamento mFormaPagamento) {
        FormaPagto formaPagto = new FormaPagto(null);
        formaPagto.setCod_forma_pagto(mFormaPagamento.getCodFormaPagto());
        formaPagto.setDescr_forma_pagto(mFormaPagamento.getDescrFormaPagto());
        formaPagto.setId_forma_pagto(mFormaPagamento.getIdFormaPagto());
        formaPagto.setVal_perc(mFormaPagamento.getValPerc());
        return formaPagto;
    }

    @Override
    public MFormaPagamento converterParaDe(FormaPagto formaPagto) {
        MFormaPagamento mFormaPagamento = new MFormaPagamento();
        mFormaPagamento.setCodFormaPagto(formaPagto.getCod_forma_pagto());
        mFormaPagamento.setDescrFormaPagto(formaPagto.getDescr_forma_pagto());
        mFormaPagamento.setIdFormaPagto(formaPagto.getId_forma_pagto());
        mFormaPagamento.setValPerc(formaPagto.getVal_perc());
        return mFormaPagamento;
    }

}
