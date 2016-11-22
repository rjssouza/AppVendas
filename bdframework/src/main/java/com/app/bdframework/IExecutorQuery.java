package com.app.bdframework;

import com.app.bdframework.baseEntidade.Entidade;

import java.util.List;

/**
 * Created by Robson on 15/11/2016.
 */
public interface IExecutorQuery<TEntidade extends Entidade> {

    int executarScalar(String whereClause, String[] argumentos);

    List<TEntidade> executarQuery(String[] colunas, String whereClause, String[] argumentos);

    TEntidade executarUnico(String[] colunas, String whereClause, String[] argumentos);

}
