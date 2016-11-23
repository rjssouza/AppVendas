package com.app.appvenda.eventosExcecao;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;

public class EventoRegraNegocio implements EventoVoid<RegraNegocioException> {
    @Override
    public void executarEvento(RegraNegocioException item) {
    }
}
