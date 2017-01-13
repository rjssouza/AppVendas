package com.app.bdframework.excecoes;

import android.content.Context;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

/**
 * Created by Robson on 12/01/2017.
 */

public interface IRegraNegocio extends EventoVoid<RegraNegocioMensagem> {
    void onPrimeiroAcesso();
}
