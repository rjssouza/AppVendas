package com.app.bdframework.eventos;

/**
 * Evento disparado sem retorno
 */
public interface EventoVoid<T> {
    void executarEvento(T item);
}
