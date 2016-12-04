package com.app.appvenda.DAO;

import android.content.Context;

import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.repositorio.RPConfiguracao;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;

/**
 * Created by Robson on 03/12/2016.
 */

public class ConfiguracaoDAO extends BaseDAO<MConfiguracao, Configuracao> {

    public ConfiguracaoDAO(Context context) {
        super(context);
    }

    @Override
    protected Repositorio<Configuracao> obterRepositorio(Context context) {
        return new RPConfiguracao(context);
    }

    @Override
    public void salvar(MConfiguracao mConfiguracao, String[] regrasIgnorar) {
        Configuracao configuracao = ConversorHelper.converter(mConfiguracao);
        this.repositorio.salvar(configuracao, regrasIgnorar);
    }

    @Override
    public void deletar(MConfiguracao mConfiguracao, String[] regrasIgnorar) {

    }
}
