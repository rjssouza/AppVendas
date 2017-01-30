package com.app.appvenda.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.app.appvenda.R;
import com.app.appvenda.dao.VendedorDAO;
import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.fragment.base.BaseFragment;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.eventos.EventoVoid;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 17/01/2017.
 */
@EFragment(R.layout.fragment_configurar_vendedor)
public class FragmentSelecionarVendedor extends BaseFragment {

    @ViewById
    AutoCompleteTextView auto_txt_vendedor;

    private EventoVoid<Boolean> posSalvar;
    private VendedorDAO vendedorDAO;
    private ArrayAdapter<MItemSeletor> valoresAutoTxt;

    public void setPosSalvar(EventoVoid<Boolean> posSalvar) {
        this.posSalvar = posSalvar;
    }

    @Override
    protected void afterViews() {
        vendedorDAO = new VendedorDAO(getContext());
        vendedorDAO.setEventoPosExecucao(new EventoVoid<Boolean>() {
            @Override
            public void executarEvento(Boolean item) throws Exception {
                exibirMensagemToast(R.string.vendedor_selecionado);
                vendedorDAO.salvarBD();
                posSalvar.executarEvento(item);
            }
        });
        configurarAutoTxt();
    }

    private void configurarAutoTxt() {
        List<Vendedor> vendedorList = vendedorDAO.getLista(null, null);
        List<MItemSeletor> mItemSeletors = ConversorHelper.converterDePara(vendedorList, new ArrayList<MItemSeletor>().getClass());

        valoresAutoTxt = new ArrayAdapter<MItemSeletor>(getContext(), android.R.layout.simple_dropdown_item_1line, mItemSeletors);
        auto_txt_vendedor.setAdapter(valoresAutoTxt);
        auto_txt_vendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto_txt_vendedor.showDropDown();
            }
        });

        auto_txt_vendedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MItemSeletor mItemSeletor = valoresAutoTxt.getItem(position);
                atualizarVendedor(mItemSeletor);
            }
        });
    }

    @UiThread
    void atualizarVendedor(MItemSeletor mItemSeletor) {
        exibirProgress(R.string.aguarde, false);
        vendedorDAO.atualizarVendedor(mItemSeletor);
    }
}