package com.app.appvenda.DAO;

import android.content.Context;

import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.enums.EnumTipoConfiguracao;
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
    protected void efetuarsalvar(MConfiguracao mConfiguracao, String[] regrasIgnorar) {
        Configuracao configuracao = ConversorHelper.converterParaDe(mConfiguracao);
        this.repositorio.salvar(configuracao, regrasIgnorar);
    }

    @Override
    protected void efetuardeletar(MConfiguracao mConfiguracao, String[] regrasIgnorar) {
        Configuracao configuracao = ConversorHelper.converterParaDe(mConfiguracao);
        this.repositorio.deletar(configuracao, null);
    }

    public MConfiguracao obterConfiguracao(EnumTipoConfiguracao tipoConfiguracao) {
        Configuracao configuracao = this.repositorio.executarUnico(Configuracao.getTodasColunas(Configuracao.class), Configuracao.TIPO_CONFIG + " = ?", new String[]{
                tipoConfiguracao.getNumVal().toString()
        });
        return ConversorHelper.converterParaDe(configuracao, true);
    }

    public MConfiguracao obterConfiguracaoAtiva() {
        Configuracao configuracao = this.repositorio.executarUnico(Configuracao.getTodasColunas(Configuracao.class), Configuracao.PRINCIPAL + " = ?", new String[]{
                "1"
        });
        return ConversorHelper.converterParaDe(configuracao, true);
    }

    public MConfiguracao obterConfiguracaoSecundaria() {
        Configuracao configuracao = this.repositorio.executarUnico(Configuracao.getTodasColunas(Configuracao.class), Configuracao.PRINCIPAL + " = ?", new String[]{
                "0"
        });
        return ConversorHelper.converterParaDe(configuracao, true);
    }

    public void transformarPrincipal(EnumTipoConfiguracao tipoConfiguracao) {
        MConfiguracao mConfiguracao = obterConfiguracao(tipoConfiguracao);
        if (mConfiguracao != null) {
            mConfiguracao.setPrincipal(true);
            salvar(mConfiguracao, null);
        }
    }

}
