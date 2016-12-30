package com.app.bdframework.baseEntidade;

import android.content.Context;
import android.os.AsyncTask;

import com.app.bdframework.BDHelper;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.utils.ListaUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe base para repositorios, esta possui métodos básicos de consulta e CRUD, trabalha em conjunto com a base de entidades
 */
public abstract class Repositorio<TEntidade extends Entidade> extends BDHelper<TEntidade> {

    private List<RegraNegocio<TEntidade>> regraNegociosSalvar;
    private List<RegraNegocio<TEntidade>> regraNegociosDeletar;

    protected Repositorio(Context context) {
        super(context);
        this.regraNegociosDeletar = new ArrayList<>();
        this.regraNegociosSalvar = new ArrayList<>();
    }

    private boolean salvarEntidade(TEntidade entidade) {
        boolean sucesso = false;
        ParCampoValor parCampoValor = entidade.getChavePrimaria();
        boolean existe = this.executarScalar(parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor() == null ? "" : parCampoValor.getValor().toString()}) > 0;
        if (existe)
            sucesso = this.getReadableDatabase().update(getNomeTabela(), entidade.getContentValue(),
                    parCampoValor.getNomeCampo() + " = ?",
                    new String[]{parCampoValor.getValor().toString()}) > 0;
        else
            sucesso = this.getWritableDatabase().insert(getNomeTabela(), null, entidade.getContentValue()) > 0;
        return sucesso;
    }

    private boolean deletarEntidade(TEntidade entidade) {
        boolean sucesso = false;
        ParCampoValor<Integer> parCampoValor = entidade.getChavePrimaria();
        sucesso = this.getWritableDatabase().delete(getNomeTabela(),
                "where " + parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor().toString()}) > 0;
        return sucesso;
    }

    public synchronized void salvar(final TEntidade entidade, final String[] regrasIgnorar)  {
        try {
            if (entidade != null) {
                executarRegraNegocio(regraNegociosSalvar, entidade, regrasIgnorar);
                salvarEntidade(entidade);
            } else {
                throw new NullPointerException("entidade");
            }
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    public synchronized void deletar(final TEntidade entidade, final String[] regrasIgnorar) {
        try {
            if (entidade != null) {
                executarRegraNegocio(regraNegociosDeletar, entidade, regrasIgnorar);
                deletarEntidade(entidade);
            } else {
                throw new NullPointerException("entidade");
            }
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    private void setRegraNegociosSalvar(RegraNegocio<TEntidade> regraNegocio) {
        this.regraNegociosSalvar.add(regraNegocio);
    }

    private void setRegraNegociosDeletar(RegraNegocio<TEntidade> regraNegocio) {
        this.regraNegociosDeletar.add(regraNegocio);
    }

    private void executarRegraNegocio(List<RegraNegocio<TEntidade>> regraNegocios, TEntidade tEntidade, final String[] regrasIgnorar) throws RegraNegocioException {
        if (regraNegocios != null) {
            Collections.sort(regraNegocios, new Comparator<RegraNegocio>() {
                @Override
                public int compare(RegraNegocio o1, RegraNegocio o2) {
                    return o1.getOrdem() < o2.getOrdem() ? o1.getOrdem() : o2.getOrdem();
                }
            });

            for (RegraNegocio<TEntidade> regraNegocio : regraNegocios) {
                if (!ListaUtils.contem(regrasIgnorar, regraNegocio.getClass().getSimpleName()))
                    regraNegocio.validarRegra(tEntidade, this);
            }
        }
    }
}