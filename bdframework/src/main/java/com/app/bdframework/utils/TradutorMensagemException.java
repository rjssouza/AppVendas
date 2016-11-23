package com.app.bdframework.utils;

/**
 * Classe responsavel por converter exceções em mensagem de texto corrida
 */
public class TradutorMensagemException {

    public static String obterMensagem(Exception exception, @SuppressWarnings("SameParameterValue") boolean obterStackTrace) {
        String mensagem = "";
        mensagem += exception.getMessage();
        if (obterStackTrace)
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                mensagem += stackTraceElement.getMethodName();
                mensagem += "\n";
            }

        return mensagem;
    }

    public static String obterMensagem(Throwable exception, @SuppressWarnings("SameParameterValue") boolean obterStackTrace) {
        String mensagem = "";
        mensagem += exception.getMessage();
        if (obterStackTrace)
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                mensagem += stackTraceElement.getMethodName();
                mensagem += "\n";
            }

        return mensagem;
    }
}
