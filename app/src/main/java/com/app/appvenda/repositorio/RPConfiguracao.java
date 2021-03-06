package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.regrasNegocio.SalvarEntidade.RNConfiguracao.ConfiguracaoPrincipal;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 04/12/2016.
 */

public class RPConfiguracao extends Repositorio<Configuracao> {

    public RPConfiguracao(Context context) {
        super(context, Configuracao.class);
    }

}
