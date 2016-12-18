package com.app.bdframework.servico;

import android.content.Context;

import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HttpContext;

/**
 * Created by Robson on 15/12/2016.
 */

public class ClienteHttpAssincrono extends AsyncHttpClient implements EventoVoid<Boolean> {

    private boolean sucesso = false;
    private static Integer requisicoes = 0;
    private static EventoVoid<Boolean> onPostRequest;

    @Override
    protected RequestHandle sendRequest(DefaultHttpClient client, HttpContext httpContext, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context) {
        if (responseHandler.getClass().getGenericSuperclass().toString().contains("CustomResponseHandler")) {
            requisicoes++;
            ((CustomResponseHandler) responseHandler).setOnPostRequest(this);
        }
        return super.sendRequest(client, httpContext, uriRequest, contentType, responseHandler, context);
    }

    @Override
    public synchronized void executarEvento(Boolean item) {
        requisicoes--;

        if (requisicoes <= 0) {
            if (onPostRequest != null) {
                try {
                    onPostRequest.executarEvento(item);
                } catch (RegraNegocioException e) {
                    TratamentoExcecao.registrarRegraNegocioExcecao(e);
                } catch (Exception e) {
                    TratamentoExcecao.registrarExcecao(e);
                } finally {
                    TratamentoExcecao.invocarEvento();
                }
            }
        }
    }

    public void setOnPostRequest(EventoVoid<Boolean> onPostRequest) {
        this.onPostRequest = onPostRequest;
    }

}
