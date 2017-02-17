package com.app.appvenda.processos.resultado;

import com.app.appvenda.modelos.MItemSeletor;

import java.util.List;

/**
 * Created by Robson on 12/02/2017.
 */

public interface IRetornoCargaVendas {

    List<MItemSeletor> getListaClientes();
    List<MItemSeletor> getListaTipoPedido();
    List<MItemSeletor> getListaFormaPagamento();
}
