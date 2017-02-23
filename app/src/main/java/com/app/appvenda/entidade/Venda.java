package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.repositorio.RPCliente;
import com.app.appvenda.repositorio.RPPedido;
import com.app.appvenda.repositorio.RPStatusVenda;
import com.app.appvenda.repositorio.RPVendedor;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_venda")
public class Venda extends Entidade<Integer> {

    public final static String ID_CLIENTE = "id_cliente";
    public final static String ID_PEDIDO = "id_pedido";
    public final static String ID_STATUS_VENDA = "id_status_venda";
    public final static String ID_VENDA = "id_venda";
    public final static String ID_VENDEDOR = "id_vendedor";
    public final static String SINCRONIZADO = "sincronizado";

    public Venda(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {
        IExecutorQuery<Pedido> pedidoIExecutorQuery = new RPPedido(context);
        IExecutorQuery<Vendedor> vendedorIExecutorQuery = new RPVendedor(context);
        IExecutorQuery<StatusVenda> statusVendaIExecutorQuery = new RPStatusVenda(context);
        IExecutorQuery<Cliente> clienteIExecutorQuery = new RPCliente(context);

        pedido = pedidoIExecutorQuery.executarUnico(Pedido.getTodasColunas(Pedido.class), Pedido.ID_VENDA + "=?", true, id_venda.toString());
        statusVenda = statusVendaIExecutorQuery.executarUnico(StatusVenda.getTodasColunas(StatusVenda.class), StatusVenda.ID_STATUS_VENDA + "=?", true, id_status_venda.toString());
        vendedor = vendedorIExecutorQuery.executarUnico(Vendedor.getTodasColunas(Vendedor.class), Vendedor.ID_VENDEDOR + "=?", true, id_vendedor.toString());
        cliente = clienteIExecutorQuery.executarUnico(Cliente.getTodasColunas(Cliente.class), Cliente.ID_CLIENTE + "=?", true, id_cliente.toString());
    }

    @ChavePrimaria
    private Long id_venda;
    @ColunaTabela
    private Long id_cliente;
    @ColunaTabela
    private Long id_vendedor;
    @ColunaTabela
    private Long id_pedido;
    @ColunaTabela
    private Long id_status_venda;
    @ColunaTabela
    private boolean sincronizado;

    private Pedido pedido;
    private StatusVenda statusVenda;
    private Vendedor vendedor;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(StatusVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Long getId_venda() {
        return id_venda;
    }

    public void setId_venda(Long id_venda) {
        this.id_venda = id_venda;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Long getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(Long id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Long getId_status_venda() {
        return id_status_venda;
    }

    public void setId_status_venda(Long id_status_venda) {
        this.id_status_venda = id_status_venda;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

}
