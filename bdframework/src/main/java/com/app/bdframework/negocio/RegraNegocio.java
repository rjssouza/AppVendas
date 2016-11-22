package com.app.bdframework.negocio;

import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.excecoes.RegraNegocioException;

/**
 * Created by Robson on 15/11/2016.
 */

public interface RegraNegocio<TEntidade extends Entidade> {

    int getOrdem();

    void validarRegra(TEntidade entidade, IExecutorQuery queryHelper) throws RegraNegocioException;

}
