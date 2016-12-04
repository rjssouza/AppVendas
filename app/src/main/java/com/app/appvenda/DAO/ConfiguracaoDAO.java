package com.app.appvenda.DAO;

import android.content.Context;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;

/**
 * Created by Robson on 03/12/2016.
 */

public class ConfiguracaoDAO extends BaseDAO<MConfiguracao, Configuracao>  {

    public ConfiguracaoDAO(Context context) {
        super(context);
    }

    @Override
    public void salvar(MConfiguracao mConfiguracao, String[] regrasIgnorar) {

    }

    @Override
    public void deletar(MConfiguracao mConfiguracao, String[] regrasIgnorar) {

    }
}
