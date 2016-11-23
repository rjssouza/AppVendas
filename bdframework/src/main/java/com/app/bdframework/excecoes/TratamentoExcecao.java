package com.app.bdframework.excecoes;

import com.app.bdframework.eventos.EventoVoid;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controlado para tratamento de exceção dinamico, caso a exceção que ocorra não invoque este metodo, o mesmo ira para o tratamento de exceção global
 */
public class TratamentoExcecao {

    private static final List<RegraNegocioException> regraNegocioExceptions = new ArrayList<>();
    private static final List<Exception> exceptions = new ArrayList<>();
    private static final List<EventoVoid<RegraNegocioMensagem>> eventoException = new ArrayList<>();

    public static void registrarRegraNegocioExcecao(RegraNegocioException rn) {
        regraNegocioExceptions.add(rn);
    }

    public static void registrarExcecao(Exception e) {
        exceptions.add(e);
    }

    public static void invocarEvento() {
        if (regraNegocioExceptions.size() > 0) {
            if (eventoException.size() > 0) {
                for (EventoVoid<RegraNegocioMensagem> eventoVoid : eventoException) {
                    RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(exceptions, true);
                    eventoVoid.executarEvento(regraNegocioMensagem);
                }
            }
        }

        if (exceptions.size() > 0) {
            if (eventoException.size() > 0) {
                for (EventoVoid<RegraNegocioMensagem> eventoVoid : eventoException) {
                    RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(exceptions, true);
                    eventoVoid.executarEvento(regraNegocioMensagem);
                }
            }
        }
    }

    public static void registrarEvento(EventoVoid<RegraNegocioMensagem> _eventoException) {
        eventoException.add(_eventoException);
    }

}
