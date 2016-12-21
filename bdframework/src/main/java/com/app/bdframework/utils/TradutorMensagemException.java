package com.app.bdframework.utils;

import java.util.Date;

/**
 * Classe responsavel por converterParaDe exceções em mensagem de texto corrida
 */
public class TradutorMensagemException {

    public static String obterMensagem(Exception exception) {
        return obterMensagem(exception, false);
    }

    public static String obterMensagem(Throwable exception, @SuppressWarnings("SameParameterValue") boolean obterStackTrace) {
        String mensagem = "";
        if (exception != null) {
            mensagem += exception.getMessage();
            if (obterStackTrace) {
                for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                    mensagem += stackTraceElement.getMethodName();
                    mensagem += "\n";
                }

                mensagem += new Date().toLocaleString();
                mensagem += "\n";
            }
        }
        return mensagem;
    }
}
