package com.app.appvenda.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.app.appvenda.R;
import com.app.appvenda.dao.ClienteDAO;
import com.app.appvenda.dao.VendaDAO;
import com.app.appvenda.fragment.base.BaseFragment;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.modelos.MVendedor;
import com.app.appvenda.utils.InformacoesVendedor;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by Robson on 30/11/2016.
 */
@EFragment(R.layout.fragment_venda)
public class FragmentVendas extends BaseFragment {

    @ViewById
    TextView txtVendedor;
    @ViewById
    AutoCompleteTextView auto_txt_cliente;
    @ViewById
    Button btnEscolherProduto;

    private MVenda mVenda;

    private ClienteDAO clienteDAO;
    private VendaDAO vendaDAO;

    private ArrayAdapter<MItemSeletor> valoresAutoTxt;

    @Override
    protected void afterViews() {
        this.mVenda = new MVenda();
        this.clienteDAO = new ClienteDAO(getContext());
        this.vendaDAO = new VendaDAO(getContext());

        configurarVendedor();
        configurarAutoTxtCliente();
    }

    private void configurarVendedor() {
        MVendedor mVendedor = InformacoesVendedor.getmVendedor();
        if (mVendedor != null)
            this.txtVendedor.setText(mVendedor.getNome());
    }

    private void configurarAutoTxtCliente() {
        List<MItemSeletor> mItemSeletorList = clienteDAO.obterTodosClientes();
        valoresAutoTxt = new ArrayAdapter<MItemSeletor>(getContext(), android.R.layout.simple_dropdown_item_1line, mItemSeletorList);
        auto_txt_cliente.setAdapter(valoresAutoTxt);
        auto_txt_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto_txt_cliente.showDropDown();
            }
        });

        auto_txt_cliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // mItemSeletor = valoresAutoTxt.getItem(position);
            }
        });
    }

}
