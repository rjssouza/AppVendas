package com.app.bdframework.negocio;

import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.excecoes.RegraNegocioException;

/**
 * Interface para validacao de regra de negocio customizada, caso aguma noa seja atendida ele dispara uma regra de negodio exception
 */
public interface RegraNegocio<TEntidade extends Entidade> {

    int getOrdem();

    void validarRegra(TEntidade entidade, IExecutorQuery<TEntidade> queryHelper) throws RegraNegocioException, Exception;

}
