package com.app.appvenda.fragment;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.app.appvenda.R;
import com.app.appvenda.dao.VendedorDAO;
import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.fragment.base.BaseFragment;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.eventos.EventoVoid;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;
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

    private EventoVoid<MConfiguracao> posSalvar;
    private VendedorDAO vendedorDAO;

    public void setPosSalvar(EventoVoid<MConfiguracao> posSalvar) {
        this.posSalvar = posSalvar;
    }

    @Override
    protected void afterViews() {
        vendedorDAO = new VendedorDAO(getContext());
        configurarAutoTxt();
    }

    private void configurarAutoTxt() {
        List<Vendedor> vendedorList = vendedorDAO.getLista(null, null);
        List<MItemSeletor> mItemSeletors = ConversorHelper.converterDePara(vendedorList, new ArrayList<MItemSeletor>().getClass());

        ArrayAdapter<MItemSeletor> valoresAutoTxt = new ArrayAdapter<MItemSeletor>(getContext(), android.R.layout.simple_dropdown_item_1line, mItemSeletors);
        auto_txt_vendedor.setAdapter(valoresAutoTxt);
    }

    @ItemSelect(R.id.auto_txt_vendedor)
    void itemSelecionado(MItemSeletor mItemSeletor) {

    }

}
