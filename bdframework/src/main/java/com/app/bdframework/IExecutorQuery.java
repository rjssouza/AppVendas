package com.app.bdframework;

import com.app.bdframework.baseEntidade.Entidade;

import java.util.List;

/**
 * Interface para execução de query utilizada pelo bd helper
 */
public interface IExecutorQuery<TEntidade extends Entidade> {

    int executarScalar(String whereClause, String... argumentos);

    List<TEntidade> executarQuery(String[] colunas, String whereClause, boolean complementaEntidade, String... argumentos);

    TEntidade executarUnico(String[] colunas, String whereClause, boolean complementaEntidade, String... argumentos);

}
