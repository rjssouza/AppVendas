package com.app.bdframework.eventos;

/**
 * Evento disparado com retorno em um objeto generico
 */
public interface EventoRetorno<TEntrada, TSaida> {
    TSaida executarEvento(TEntrada tEntrada);
}
