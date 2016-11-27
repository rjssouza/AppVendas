package com.app.appvenda.eventosExcecao;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import java.util.logging.Logger;


public class TratamentoEventoGeral implements EventoVoid<RegraNegocioMensagem> {

    @Override
    public void executarEvento(RegraNegocioMensagem item) {
        item.getException().printStackTrace();
    }

}
