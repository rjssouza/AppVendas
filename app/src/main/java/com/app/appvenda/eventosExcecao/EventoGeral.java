package com.app.appvenda.eventosExcecao;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

/**
 * Created by Robson on 16/11/2016.
 */

public class EventoGeral implements EventoVoid<RegraNegocioMensagem> {
    @Override
    public void executarEvento(RegraNegocioMensagem item) {
        String teste = "teste";
    }
}
