package com.app.bdframework.eventos;

/**
 * Created by Robson on 16/11/2016.
 */

public interface EventoRetorno<TEntrada, TSaida> {
    TSaida executarEvento(TEntrada tEntrada);
}
