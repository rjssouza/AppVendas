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

public abstract class CustomResponseHandler<TObjetoSucesso> extends AsyncHttpResponseHandler {

    private EventoVoid<Boolean> onPostRequest;
    private boolean sucesso = false;
    private Class<TObjetoSucesso> instanciaSucesso;

    public CustomResponseHandler(Class<TObjetoSucesso> instanciaSucesso) {
        this.instanciaSucesso = instanciaSucesso;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        sucesso = true;
        String str = new String(responseBody); // for UTF-8 encoding
        TObjetoSucesso objetoSucesso = null;
        if (instanciaSucesso.isPrimitive() || instanciaSucesso.equals(String.class)) {
            objetoSucesso = (TObjetoSucesso) str;
        } else {
            Gson gson = new Gson();
            objetoSucesso = gson.fromJson(str, instanciaSucesso);
        }
        try {
            posSucesso(statusCode, objetoSucesso);
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
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
