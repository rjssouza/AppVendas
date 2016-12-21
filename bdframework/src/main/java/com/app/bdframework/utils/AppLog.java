package com.app.bdframework.utils;

import com.app.bdframework.excecoes.RegraNegocioMensagem;

/**
 * Created by Robson on 21/12/2016.
 */

public class AppLog {
    public static void CriarLog(RegraNegocioMensagem regraNegocioMensagem) {
        String diretorio = GeradorArquivo.getPastaExternaAplicacao() + "/Logs";
        String mensagem = TradutorMensagemException.obterMensagem(regraNegocioMensagem.getException(), true);
        GeradorArquivo.criarArquivoTexto(mensagem, diretorio, "ErroGeral");
    }
}
