package com.app.bdframework.excecoes;

import android.os.Handler;
import android.os.Looper;

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
                if (eventoRegraNegocioException != null) {
                    if (regraNegocioException != null) {
                        RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(regraNegocioException);
                        try {
                            eventoRegraNegocioException.executarEvento(regraNegocioMensagem);
                            regraNegocioException = null;
                            eventoRegraNegocioException.esconderProgress();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (exception != null) {
                        RegraNegocioMensagem regraNegocioMensagem = new RegraNegocioMensagem(exception);
                        try {
                            eventoRegraNegocioException.executarEvento(regraNegocioMensagem);
                            exception = null;
                            eventoRegraNegocioException.esconderProgress();
                        } catch (Exception e) {
                            e.printStackTrace();
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
