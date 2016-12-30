package com.app.appvenda.regrasNegocio.SalvarEntidade.RNConfiguracao;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.repositorio.RPConfiguracao;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.negocio.RegraNegocioResource;

/**
 * Created by Robson on 14/12/2016.
 */

public class ConfiguracaoPrincipal implements RegraNegocio<Configuracao> {

    private RPConfiguracao rpConfiguracao;

    public ConfiguracaoPrincipal(RPConfiguracao rpConfiguracao) {
        this.rpConfiguracao = rpConfiguracao;
    }

    @Override
    public int getOrdem() {
        return 0;
    }

    @Override
    public void validarRegra(Configuracao entidade, IExecutorQuery<Configuracao> queryHelper) throws RegraNegocioException, Exception {
        Configuracao configuracao = queryHelper.executarUnico(Configuracao.getTodasColunas(Configuracao.class), Configuracao.PRINCIPAL + " = ?", new String[]{
                "1"
        });
        if (configuracao != null) {
            configuracao.setPrincipal(false);
            this.rpConfiguracao.salvar(configuracao, new String[]{"ConfiguracaoPrincipal"});
        }
    }
}
