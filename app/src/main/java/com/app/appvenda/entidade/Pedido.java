package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.repositorio.RPFormaPagto;
import com.app.appvenda.repositorio.RPPedidoProduto;
import com.app.appvenda.repositorio.RPTipoPedido;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

import java.util.ArrayList;
import java.util.List;

@NomeTabela(nomeTabela = "tb_pedido")
public class Pedido extends Entidade<Integer> {

    public final static String COORD_X = "coord_x";
    public final static String COORD_Y = "coord_y";
    public final static String ENDERECO = "endereco";
    public final static String ID_FORMA_PAGTO = "id_forma_pagto";
    public final static String ID_PEDIDO = "id_pedido";
    public final static String VALOR_TOTAL = "valor_total";
    public final static String ID_VENDA = "id_venda";

    public Pedido(Cursor cursor) {
        super(cursor);
        this.pedidoProdutos = new ArrayList<>();
    }

    @ChavePrimaria
    private Integer id_pedido;
    @ColunaTabela
    private Double valor_total;
    @ColunaTabela
    private String endereco;
    @ColunaTabela
    private Long coord_x;
    @ColunaTabela
    private Long coord_y;
    @ColunaTabela
    private Integer id_forma_pagto;
    @ColunaTabela
    private Integer id_venda;
    @ColunaTabela
    private Integer id_tipo_pedido;

    private FormaPagto formaPagto;
    private List<PedidoProduto> pedidoProdutos;
    private TipoPedido tipoPedido;

    @Override
    public void complementarEntidade(Context context) {
        IExecutorQuery<PedidoProduto> iExecutorQueryPedidoProduto = new RPPedidoProduto(context);
        IExecutorQuery<FormaPagto> iExecutorQueryFormaPagto = new RPFormaPagto(context);
        IExecutorQuery<TipoPedido> tipoPedidoIExecutorQuery = new RPTipoPedido(context);

        pedidoProdutos = iExecutorQueryPedidoProduto.executarQuery(PedidoProduto.getTodasColunas(PedidoProduto.class), PedidoProduto.ID_PEDIDO + "=?", true, id_pedido.toString());
        formaPagto = iExecutorQueryFormaPagto.executarUnico(FormaPagto.getTodasColunas(FormaPagto.class), FormaPagto.ID_FORMA_PAGTO + "=?", false, id_forma_pagto.toString());
        tipoPedido = tipoPedidoIExecutorQuery.executarUnico(TipoPedido.getTodasColunas(TipoPedido.class), TipoPedido.ID_TIPO_PEDIDO + "=?", false,  id_tipo_pedido.toString());
    }

    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public FormaPagto getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(FormaPagto formaPagto) {
        this.formaPagto = formaPagto;
    }

    public List<PedidoProduto> getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(Long coord_x) {
        this.coord_x = coord_x;
    }

    public Long getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(Long coord_y) {
        this.coord_y = coord_y;
    }

    public Integer getId_forma_pagto() {
        return id_forma_pagto;
    }

    public void setId_forma_pagto(Integer id_forma_pagto) {
        this.id_forma_pagto = id_forma_pagto;
    }

    public Integer getId_tipo_pedido() {
        return id_tipo_pedido;
    }

    public void setId_tipo_pedido(int id_tipo_pedido) {
        this.id_tipo_pedido = id_tipo_pedido;
    }

}
