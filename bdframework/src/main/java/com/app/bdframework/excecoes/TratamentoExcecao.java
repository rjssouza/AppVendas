package com.app.bdframework.excecoes;

import com.app.bdframework.eventos.EventoVoid;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controlado para tratamento de exceção dinamico, caso a exceção que ocorra não invoque este metodo, o mesmo ira para o tratamento de exceção global
 */
public class TratamentoExcecao {

    private static RegraNegocioException regraNegocioException;
    private static Exception exception;
    private static EventoVoid<RegraNegocioMensagem> eventoRegraNegocioException = null;
    private static final List<EventoVoid<RegraNegocioMensagem>> eventoException = new ArrayList<>();

    public static void registrarRegraNegocioExcecao(RegraNegocioException rn) {
        regraNegocioException = rn;
    }

    public static void registrarExcecao(Exception e) {
        exception = e;
    }

    public static void invocarEvento() {
        if (regraNegocioException != null) {
            if (eventoRegraNegocioException != null) {
                RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(regraNegocioException);
                try {
                    eventoRegraNegocioException.executarEvento(regraNegocioMensagem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (exception != null) {
            if (eventoException.size() > 0) {
                for (EventoVoid<RegraNegocioMensagem> eventoVoid : eventoException) {
                    RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(exception, true);
                    try {
                        eventoVoid.executarEvento(regraNegocioMensagem);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void registrarEvento(EventoVoid<RegraNegocioMensagem> _eventoException) {
        eventoException.add(_eventoException);
    }

    public static void registrarEventoRegraNegocio(EventoVoid<RegraNegocioMensagem> _eventoRegraNegocioException) {
        eventoRegraNegocioException = _eventoRegraNegocioException;
    }

}
