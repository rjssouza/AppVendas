package com.app.appvenda.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.app.appvenda.R;
import com.app.appvenda.base.BaseActivity;
import com.app.appvenda.dao.VendaDAO;
import com.app.appvenda.fragment.base.BaseFragment;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.processos.ProcessoCargaVendas;
import com.app.appvenda.processos.resultado.IRetornoCargaVendas;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by Robson on 30/11/2016.
 */
@EFragment(R.layout.fragment_venda)
public class FragmentVendas extends BaseFragment {

    @ViewById
    AutoCompleteTextView auto_txt_cliente;
    @ViewById
    Button btnEscolherProduto;

    private MVenda mVenda;
    private VendaDAO vendaDAO;
    private ProcessoCargaVendas processoCargaVendas;

    private ArrayAdapter<MItemSeletor> valoresAutoTxtCliente;

    @Override
    protected void afterViews() {
        this.mVenda = new MVenda();
        this.vendaDAO = new VendaDAO(getContext());
        configurarCargaVendas();

        iniciar();
    }

    @UiThread
    void iniciar() {
        this.processoCargaVendas.efetuarCargaVendas();
    }

    private void configurarCargaVendas() {
        this.processoCargaVendas = new ProcessoCargaVendas(getActivity());
        this.processoCargaVendas.setPreCarga(new EventoVoid() {
            @Override
            public void executarEvento(Object item) throws Exception {
                exibirProgress(R.string.aguarde, false);
            }
        });
        this.processoCargaVendas.setPosCarga(new EventoVoid<IRetornoCargaVendas>() {
            @Override
            public void executarEvento(IRetornoCargaVendas item) throws Exception {
                esconderProgress();
                configurarAutoTxtCliente(item.getListaClientes());
            }
        });
        this.processoCargaVendas.setErroProcessamento(new EventoVoid<RegraNegocioMensagem>() {
            @Override
            public void executarEvento(RegraNegocioMensagem item) throws Exception {
                ((BaseActivity) getActivity()).executarEvento(item);
            }
        });
    }

    private void configurarAutoTxtCliente(List<MItemSeletor> mItemSeletorList) {
        valoresAutoTxtCliente = new ArrayAdapter<MItemSeletor>(getContext(), android.R.layout.simple_dropdown_item_1line, mItemSeletorList);

        auto_txt_cliente.setAdapter(valoresAutoTxtCliente);
        auto_txt_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto_txt_cliente.showDropDown();
            }
        });

        auto_txt_cliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}
