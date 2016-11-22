package com.app.appvenda.eventosExcecao;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;

/**
 * Created by Robson on 16/11/2016.
 */

public class EventoRegraNegocio implements EventoVoid<RegraNegocioException> {
    @Override
    public void executarEvento(RegraNegocioException item) {
        String teste = "teste";
    }
}
