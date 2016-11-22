package com.app.bdframework.baseEntidade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.bdframework.BDHelper;
import com.app.bdframework.auxiliar.ParCampoValor;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Robson on 12/11/2016.
 */

public abstract class Repositorio<TEntidade extends Entidade> extends BDHelper<TEntidade> {

    public Repositorio(Context context) {
        super(context);
    }

    private void salvarEntidade(TEntidade entidade) {
        ParCampoValor<Integer> parCampoValor = entidade.getChavePrimaria();
        boolean existe = this.executarScalar(parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor().toString()}) > 0;
        if (existe)
            instancia.getReadableDatabase().update(entidade.getNomeTabela(), entidade.getContentValue(),
                    parCampoValor.getNomeCampo() + " = ?",
                    new String[]{parCampoValor.getValor().toString()});
        else
            instancia.getWritableDatabase().insert(entidade.getNomeTabela(), null, entidade.getContentValue());
    }

    private void deletarEntidade(TEntidade entidade) {
        ParCampoValor<Integer> parCampoValor = entidade.getChavePrimaria();
        instancia.getReadableDatabase().delete(entidade.getNomeTabela(),
                "where " + parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor().toString()});
    }

    public void salvar(TEntidade entidade) {
        try {
            if (obterRegras() != null) {
                List<RegraNegocio> regraNegocios = obterRegras();
                Collections.sort(regraNegocios, new Comparator<RegraNegocio>() {
                    @Override
                    public int compare(RegraNegocio o1, RegraNegocio o2) {
                        return o1.getOrdem() < o2.getOrdem() ? o1.getOrdem() : o2.getOrdem();
                    }
                });

                for (RegraNegocio regraNegocio : regraNegocios) {
                    try {
                        regraNegocio.validarRegra(entidade, this);
                    } catch (RegraNegocioException rn) {
                        TratamentoExcecao.registrarRegraNegocioExcecao(rn);
                    }
                }
            }
            this.salvarEntidade(entidade);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    public void deletar(TEntidade entidade) {
        try {
            if (obterRegras() != null) {
                List<RegraNegocio> regraNegocios = obterRegras();
                Collections.sort(regraNegocios, new Comparator<RegraNegocio>() {
                    @Override
                    public int compare(RegraNegocio o1, RegraNegocio o2) {
                        return o1.getOrdem() < o2.getOrdem() ? o1.getOrdem() : o2.getOrdem();
                    }
                });

                for (RegraNegocio regraNegocio : regraNegocios) {
                    try {
                        regraNegocio.validarRegra(entidade, this);
                    } catch (RegraNegocioException rn) {
                        TratamentoExcecao.registrarRegraNegocioExcecao(rn);
                    }
                }
                this.deletarEntidade(entidade);
            }
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    protected abstract List<RegraNegocio> obterRegras();

}
