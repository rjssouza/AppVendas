package com.app.bdframework.utils;

import com.app.bdframework.excecoes.RegraNegocioMensagem;

/**
 * Created by Robson on 21/12/2016.
 */

public class AppLog {

    public static void criarLog(RegraNegocioMensagem regraNegocioMensagem) {
        if (regraNegocioMensagem.getRegraNegocioException() != null) {
            criarLog(regraNegocioMensagem.getRegraNegocioException());
        } else {
            criarLog(regraNegocioMensagem.getException());
        }
    }

    public static void criarLog(Throwable e) {
        String diretorio = ArquivosUtils.getPastaExternaAplicacao() + "/Logs";
        String mensagem = TradutorMensagemException.obterMensagem(e, true);
        ArquivosUtils.criarArquivoTexto(mensagem, diretorio, "ErroGeral");
    }
}
