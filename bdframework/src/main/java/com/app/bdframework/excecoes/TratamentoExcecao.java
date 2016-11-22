package com.app.bdframework.excecoes;

import com.app.bdframework.eventos.EventoVoid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 15/11/2016.
 */

public class TratamentoExcecao {

    private static List<RegraNegocioException> regraNegocioExceptions = new ArrayList<>();
    private static List<Exception> exceptions = new ArrayList<>();
    private static List<EventoVoid<?>> eventoException = new ArrayList<>();

    public static void registrarRegraNegocioExcecao(RegraNegocioException rn) {
        regraNegocioExceptions.add(rn);
    }

    public static void registrarExcecao(Exception e) {
        exceptions.add(e);
    }

    public static void invocarEvento() {
        if (regraNegocioExceptions.size() > 0) {
            if (eventoException.size() > 0) {
                for (EventoVoid eventoVoid : eventoException) {
                    RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(exceptions, true);
                    eventoVoid.executarEvento(regraNegocioMensagem);
                }
            }
        }

        if (exceptions.size() > 0) {
            if (eventoException.size() > 0) {
                for (EventoVoid eventoVoid : eventoException) {
                    RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(exceptions, true);
                    eventoVoid.executarEvento(regraNegocioMensagem);
                }
            }
        }
    }

    public static void registrarEvento(EventoVoid<?> _eventoException) {
        eventoException.add(_eventoException);
    }

}
