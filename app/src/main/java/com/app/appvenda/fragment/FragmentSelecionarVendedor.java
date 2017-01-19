package com.app.appvenda.fragment;

import com.app.appvenda.R;
import com.app.appvenda.exportadorVenda.ExportadorVendas;
import com.app.appvenda.fragment.base.BaseFragment;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.bdframework.eventos.EventoVoid;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Robson on 17/01/2017.
 */
@EFragment(R.layout.fragment_configurar_vendedor)
public class FragmentSelecionarVendedor extends BaseFragment {

    private ExportadorVendas exportadorVendas;
    private EventoVoid<MConfiguracao> posSalvar;

    public void setPosSalvar(EventoVoid<MConfiguracao> posSalvar) {
        this.posSalvar = posSalvar;
    }

    @Override
    protected void afterViews() {
        configurarSincronizacao();
    }


    private void configurarSincronizacao() {
        this.exportadorVendas = new ExportadorVendas(this.getActivity());
        this.exportadorVendas.setEventoPosProcessamento(new EventoVoid<Boolean>() {
            @Override
            public void executarEvento(Boolean item) throws Exception {
                if (item) {
                    esconderProgress();
                }
            }
        });
    }

    private void sincronizar() {
        exibirProgress(R.string.aguarde, false);
        this.exportadorVendas.evetuarSincronizacao();
    }

}
