package com.app.bdframework.baseEntidade;

import android.content.Context;

import com.app.bdframework.BDHelper;
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
public abstract class Repositorio<TEntidade extends Entidade> extends BDHelper<TEntidade> {

    private List<RegraNegocio<TEntidade>> regraNegociosSalvar;
    private List<RegraNegocio<TEntidade>> regraNegociosDeletar;

    protected Repositorio(Context context) {
        super(context);
        this.regraNegociosDeletar = new ArrayList<>();
        this.regraNegociosSalvar = new ArrayList<>();
    }

    private void salvarEntidade(TEntidade entidade) {
        ParCampoValor<Integer> parCampoValor = entidade.getChavePrimaria();
        boolean existe = this.executarScalar(parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor() == null ? "" : parCampoValor.getValor().toString()}) > 0;
        if (existe)
            this.getReadableDatabase().update(getNomeTabela(), entidade.getContentValue(),
                    parCampoValor.getNomeCampo() + " = ?",
                    new String[]{parCampoValor.getValor().toString()});
        else
            this.getWritableDatabase().insert(getNomeTabela(), null, entidade.getContentValue());
    }

    private void deletarEntidade(TEntidade entidade) {
        ParCampoValor<Integer> parCampoValor = entidade.getChavePrimaria();
        this.getReadableDatabase().delete(getNomeTabela(),
                "where " + parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor().toString()});
    }

    public void salvar(TEntidade entidade, final String[] regrasIgnorar) {
        try {
            if (entidade != null) {
                executarRegraNegocio(regraNegociosSalvar, entidade, regrasIgnorar);
                this.salvarEntidade(entidade);
            } else {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    public void deletar(TEntidade entidade, final String[] regrasIgnorar) {
        try {
            executarRegraNegocio(regraNegociosDeletar, entidade, regrasIgnorar);
            this.deletarEntidade(entidade);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    private void registrarRegraNegocioSalvar(RegraNegocio<TEntidade> regraNegocio){
        this.regraNegociosSalvar.add(regraNegocio);
    }

    private void obterRegrasDeletar(RegraNegocio<TEntidade> regraNegocio){
        this.regraNegociosDeletar.add(regraNegocio);
    }

    private void executarRegraNegocio(List<RegraNegocio<TEntidade>> regraNegocios, TEntidade tEntidade, final String[] regrasIgnorar) {
        if (regraNegocios != null) {
            Collections.sort(regraNegocios, new Comparator<RegraNegocio>() {
                @Override
                public int compare(RegraNegocio o1, RegraNegocio o2) {
                    return o1.getOrdem() < o2.getOrdem() ? o1.getOrdem() : o2.getOrdem();
                }
            });

            for (RegraNegocio<TEntidade> regraNegocio : regraNegocios) {
                try {
                    if (!ListaUtils.contem(regrasIgnorar, regraNegocio.getClass().getSimpleName()))
                        regraNegocio.validarRegra(tEntidade, this);
                } catch (RegraNegocioException rn) {
                    TratamentoExcecao.registrarRegraNegocioExcecao(rn);
                    break;
                }
            }
        }
    }

}
