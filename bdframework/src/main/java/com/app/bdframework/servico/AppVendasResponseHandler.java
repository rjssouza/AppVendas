package com.app.bdframework.servico;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.BufferedOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.ResponseHandler;

/**
 * Created by Robson on 15/12/2016.
 */

public abstract class AppVendasResponseHandler<TObjetoSucesso> extends AsyncHttpResponseHandler {

    private EventoVoid<Boolean> onPostRequest;
    private boolean sucesso = false;
    private Class<TObjetoSucesso> instanciaSucesso;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        sucesso = true;
        Gson gson = new Gson();
        TObjetoSucesso objetoSucesso = gson.fromJson(new String(responseBody), instanciaSucesso);
        try {
            posSucesso(statusCode, objetoSucesso);
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        sucesso = false;
        try {
            posErro(statusCode, headers, responseBody, error);
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (onPostRequest != null) {
            try {
                onPostRequest.executarEvento(sucesso);
            } catch (Exception e) {
                TratamentoExcecao.registrarExcecao(e);
            } finally {
                TratamentoExcecao.invocarEvento();
            }
        }
    }

    public void setOnPostRequest(EventoVoid<Boolean> onPostRequest) {
        this.onPostRequest = onPostRequest;
    }

    protected abstract void posSucesso(int statusCode, TObjetoSucesso objetoSucesso) throws Exception;

    protected abstract void posErro(int statusCode, Header[] headers, byte[] responseBody, Throwable error) throws RegraNegocioException;

}
