package com.app.bdframework.utils;

import android.os.Environment;

/**
 * Created by Robson on 15/11/2016.
 */

public class TradutorMensagemException {

    public static String obterMensagem(Exception exception, boolean obterStackTrace) {
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
