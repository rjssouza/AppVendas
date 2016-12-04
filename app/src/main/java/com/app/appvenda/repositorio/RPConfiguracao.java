package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Configuracao;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

/**
 * Created by Robson on 04/12/2016.
 */

public class RPConfiguracao extends Repositorio<Configuracao> {

    public RPConfiguracao(Context context) {
        super(context);
    }

    @Override
    protected List<RegraNegocio<Configuracao>> obterRegrasSalvar() {
        return null;
    }

    @Override
    protected List<RegraNegocio<Configuracao>> obterRegrasDeletar() {
        return null;
    }

    @Override
    protected Configuracao obterEntidade(Cursor cursor) {
        return new Configuracao(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_configuracao";
    }

}