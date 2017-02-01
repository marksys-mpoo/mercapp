package com.mercapp.supermercado.dominio;

/**
 * Created by WELLINGTON on 27/01/2017.
 */

public class Carrinho {

    private int id;
    private Double valorUnitario;
    private Produto Produto;
    private String quantidadeItens;

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final double getValorUnitario() {
        return valorUnitario;
    }

    public final void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;

    }

    public com.mercapp.supermercado.dominio.Produto getProduto() {
        return Produto;
    }

    public void setProduto(com.mercapp.supermercado.dominio.Produto produto) {
        Produto = produto;
    }

    public String getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(String quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }
}
