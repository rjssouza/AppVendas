package com.app.appvenda.eventosExcecao;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;


public class EventoGeral implements EventoVoid<RegraNegocioMensagem> {
    @Override
    public void executarEvento(RegraNegocioMensagem item) {
    }
}
