package com.app.appvenda.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.app.appvenda.R;
import com.app.appvenda.base.BaseActivity;
import com.app.appvenda.dao.ClienteDAO;
import com.app.appvenda.dao.FormaPagamentoDAO;
import com.app.appvenda.dao.VendaDAO;
import com.app.appvenda.enums.EnumTipoPedido;
import com.app.appvenda.fragment.base.BaseFragment;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.processos.ProcessoCargaVendas;
import com.app.appvenda.processos.resultado.IRetornoCargaVendas;
import com.app.appvenda.utils.InformacoesVendedor;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by Robson on 30/11/2016.
 */
@EFragment(R.layout.fragment_venda)
public class FragmentVendas extends BaseFragment {

    private static final String TROCA = "1";
    private static final String DEVOLUCAO = "2";
    private static final String VENDA = "0";

    @ViewById
    AutoCompleteTextView auto_txt_cliente;
    @ViewById
    AutoCompleteTextView auto_txt_tipo_pedido;
    @ViewById
    AutoCompleteTextView auto_txt_forma_pagto;
    @ViewById
    Button btnEscolherProduto;

    private MVenda mVenda;
    private ProcessoCargaVendas processoCargaVendas;

    private VendaDAO vendaDAO;
    private ClienteDAO clienteDAO;
    private FormaPagamentoDAO formaPagamentoDAO;

    @Override
    protected void afterViews() {
        this.mVenda = new MVenda(InformacoesVendedor.getmVendedor(getContext()));
        this.vendaDAO = new VendaDAO(getContext());
        this.clienteDAO = new ClienteDAO(getContext());
        this.formaPagamentoDAO = new FormaPagamentoDAO(getContext());

        configurarCargaVendas();
        iniciar();
    }

    @UiThread
    void iniciar() {
        this.processoCargaVendas.efetuarCargaVendas();
    }

    private void configurarCargaVendas() {
        this.processoCargaVendas = new ProcessoCargaVendas((BaseActivity) getActivity());
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
                configurarAutoTxtFormaPagto(item.getListaFormaPagamento());
                configurarAutoTxtTipoPedido(item.getListaTipoPedido());
            }
        });
        this.processoCargaVendas.setErroProcessamento(new EventoVoid<RegraNegocioMensagem>() {
            @Override
            public void executarEvento(RegraNegocioMensagem item) throws Exception {
                ((BaseActivity) getActivity()).executarEvento(item);
            }
        });
    }

    private void configurarAutoTxtCliente(final List<MItemSeletor> mItemSeletorList) {
        configurarAutoTxt(auto_txt_cliente, mItemSeletorList, new EventoVoid<MItemSeletor>() {
            @Override
            public void executarEvento(MItemSeletor item) throws Exception {
                MCliente mCliente = clienteDAO.obterPorID(item.getId(), true);
                mVenda.setmCliente(mCliente);
            }
        });
    }

    private void configurarAutoTxtTipoPedido(List<MItemSeletor> mItemSeletorList) {
        configurarAutoTxt(auto_txt_tipo_pedido, mItemSeletorList, new EventoVoid<MItemSeletor>() {
            @Override
            public void executarEvento(MItemSeletor item) throws Exception {
                switch (item.getId().toString()) {
                    case TROCA:
                        mVenda.getmPedido().setEnumTipoPedido(EnumTipoPedido.TROCA);
                        break;
                    case DEVOLUCAO:
                        mVenda.getmPedido().setEnumTipoPedido(EnumTipoPedido.DEVOLUCAO);
                        break;
                    default:
                        mVenda.getmPedido().setEnumTipoPedido(EnumTipoPedido.VENDA);
                        break;
                }
            }
        });
    }

    private void configurarAutoTxtFormaPagto(List<MItemSeletor> mItemSeletorList) {
        configurarAutoTxt(auto_txt_forma_pagto, mItemSeletorList, new EventoVoid<MItemSeletor>() {
            @Override
            public void executarEvento(MItemSeletor item) throws Exception {
                MFormaPagamento mFormaPagamento = formaPagamentoDAO.obterPorID(item.getId(), false);
                mVenda.getmPedido().setmFormaPagamento(mFormaPagamento);
            }
        });
    }

    private void configurarAutoTxt(final AutoCompleteTextView auto_txt, List<MItemSeletor> mItemSeletorList, final EventoVoid<MItemSeletor> onItemSelected) {
        ArrayAdapter<MItemSeletor> valoresAutoTxt = new ArrayAdapter<MItemSeletor>(getContext(), android.R.layout.simple_dropdown_item_1line, mItemSeletorList);

        auto_txt.setAdapter(valoresAutoTxt);
        auto_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto_txt.showDropDown();
            }
        });

        auto_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MItemSeletor mItemSeletor = (MItemSeletor) parent.getItemAtPosition(position);
                try {
                    onItemSelected.executarEvento(mItemSeletor);
                } catch (Exception e) {
                    TratamentoExcecao.registrarExcecao(e);
                } finally {
                    TratamentoExcecao.invocarEvento();
                }
            }
        });
    }
}
