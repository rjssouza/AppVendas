package com.app.appvenda.eventosExcecao;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

public class EventoRegraNegocio implements EventoVoid<RegraNegocioMensagem> {
    @Override
    public void executarEvento(RegraNegocioMensagem item) {
    }
}
