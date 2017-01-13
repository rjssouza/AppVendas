package com.app.bdframework.excecoes;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.app.bdframework.eventos.EventoVoid;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controlado para tratamento de exceção dinamico, caso a exceção que ocorra não invoque este metodo, o mesmo ira para o tratamento de exceção global
 */
public class TratamentoExcecao {

    private static RegraNegocioException regraNegocioException;
    private static Exception exception;
    private static IRegraNegocio eventoRegraNegocioException = null;

    public static boolean existeExcecao() {
        return (exception != null || regraNegocioException != null);
    }

    public static void registrarRegraNegocioExcecao(RegraNegocioException rn) {
        regraNegocioException = rn;
    }

    public static void registrarExcecao(Exception e) {
        exception = e;
    }

    public static void invocarEvento() {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (regraNegocioException != null) {
                    if (eventoRegraNegocioException != null) {
                        RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(regraNegocioException);
                        try {
                            eventoRegraNegocioException.executarEvento(regraNegocioMensagem);
                            regraNegocioException = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (exception != null) {
                        if (eventoRegraNegocioException != null) {
                            RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(regraNegocioException);
                            try {
                                eventoRegraNegocioException.executarEvento(regraNegocioMensagem);
                                regraNegocioException = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

    }

    public static void registrarEventoRegraNegocio(IRegraNegocio _eventoRegraNegocioException) {
        eventoRegraNegocioException = _eventoRegraNegocioException;
    }

    public static void chamarOnPrimeiroAcesso() {
        if (eventoRegraNegocioException != null)
            eventoRegraNegocioException.onPrimeiroAcesso();
    }

}
