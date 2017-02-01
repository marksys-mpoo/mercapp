package com.mercapp.supermercado.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Carrinho {

    private int id;
    private Double valorTotal;
    private List<Map> produtos = new ArrayList<>();
    private String quantidadeItens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Map> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Map> produtos) {
        this.produtos = produtos;
    }

    public String getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(String quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public void addProdutos(Map map){
        produtos.add(map);
    }
}
