package com.app.bdframework.baseEntidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.BDHelper;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.utils.ListaUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe base para repositorios, esta possui métodos básicos de consulta e CRUD, trabalha em conjunto com a base de entidades
 */
public abstract class Repositorio<TEntidade extends Entidade> implements IExecutorQuery {

    private Context context;
    private BDHelper<TEntidade> bdHelper;
    private Class<TEntidade> tEntidadeClass;
    private List<RegraNegocio<TEntidade>> regraNegociosSalvar;
    private List<RegraNegocio<TEntidade>> regraNegociosDeletar;

    public Repositorio(Context context, Class<TEntidade> tEntidadeClass) {
        this.context = context;
        this.tEntidadeClass = tEntidadeClass;
        this.regraNegociosSalvar = new ArrayList<>();
        this.regraNegociosDeletar = new ArrayList<>();
        this.bdHelper = BDHelper.getBDHelper(context, tEntidadeClass);
    }

    @Override
    public synchronized int executarScalar(String whereClause, String[] argumentos) {
        return bdHelper.executarScalar(whereClause, argumentos, this.tEntidadeClass);
    }

    @Override
    public synchronized List<TEntidade> executarQuery(String[] colunas, String whereClause, String[] argumentos) {
        try {
            List<TEntidade> tEntidades = new ArrayList<>();
            Cursor cursor = this.bdHelper.getDatabase().query(this.bdHelper.getNomeTabela(this.tEntidadeClass), colunas, whereClause, argumentos, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    TEntidade entidade = tEntidadeClass.getConstructor(Cursor.class).newInstance(cursor);
                    entidade.complementarEntidade(context);
                    tEntidades.add(entidade);
                } while (cursor.moveToNext());
                return tEntidades;
            }
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
        return null;
    }

    @Override
    public synchronized TEntidade executarUnico(String[] colunas, String whereClause, String[] argumentos) {
        try {
            Cursor cursor = this.bdHelper.getDatabase().query(this.bdHelper.getNomeTabela(this.tEntidadeClass), colunas, whereClause, argumentos, null, null, null);
            if (cursor.moveToFirst()) {
                TEntidade _entidade;
                do {
                    _entidade = tEntidadeClass.getConstructor(Cursor.class).newInstance(cursor);
                    _entidade.complementarEntidade(context);
                } while (cursor.moveToNext());
                return _entidade;
            }
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
        return null;
    }

    public synchronized boolean salvar(final TEntidade entidade, final String[] regrasIgnorar) throws RegraNegocioException, Exception {
        if (entidade != null) {
            if (executarRegraNegocio(regraNegociosSalvar, entidade, regrasIgnorar)) {
                return this.bdHelper.salvarEntidade(entidade);
            }
        }
        return false;
    }

    public synchronized boolean deletar(final TEntidade entidade, final String[] regrasIgnorar) throws RegraNegocioException, Exception {
        if (entidade != null) {
            if (executarRegraNegocio(regraNegociosDeletar, entidade, regrasIgnorar)) {
                return this.bdHelper.deletarEntidade(entidade);
            }
        }
        return false;
    }

    public synchronized void createTransaction() {
        if (!this.bdHelper.getDatabase().isDbLockedByCurrentThread()) {
            this.bdHelper.getDatabase().beginTransaction();
        }
    }

    public synchronized void endTransaction() {
        if (this.bdHelper.getDatabase().inTransaction()) {
            if (!TratamentoExcecao.existeExcecao()) {
                this.bdHelper.getDatabase().setTransactionSuccessful();
            }
            this.bdHelper.getDatabase().endTransaction();
        }

        if (this.bdHelper.getDatabase().isOpen())
            this.bdHelper.getDatabase().close();
    }

    public synchronized void salvarBDLocal() {
        this.bdHelper.salvarBDLocal();
    }

    protected void setRegraNegociosSalvar(RegraNegocio<TEntidade> regraNegocio) {
        this.regraNegociosSalvar.add(regraNegocio);
    }

    protected void setRegraNegociosDeletar(RegraNegocio<TEntidade> regraNegocio) {
        this.regraNegociosDeletar.add(regraNegocio);
    }

    private boolean executarRegraNegocio(List<RegraNegocio<TEntidade>> regraNegocios, TEntidade tEntidade, final String[] regrasIgnorar) throws RegraNegocioException, Exception {
        boolean sucesso = true;
        if (regraNegocios != null) {
            Collections.sort(regraNegocios, new Comparator<RegraNegocio>() {
                @Override
                public int compare(RegraNegocio o1, RegraNegocio o2) {
                    return o1.getOrdem() < o2.getOrdem() ? o1.getOrdem() : o2.getOrdem();
                }
            });

            for (RegraNegocio<TEntidade> regraNegocio : regraNegocios) {
                if (!ListaUtils.contem(regrasIgnorar, regraNegocio.getClass().getSimpleName()))
                    if (!regraNegocio.validarRegra(tEntidade, this)) {
                        sucesso = false;
                    }
            }
        }
        return sucesso;
    }

}