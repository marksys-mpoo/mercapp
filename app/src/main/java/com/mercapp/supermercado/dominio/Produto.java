package com.mercapp.supermercado.dominio;

public class Produto {
    private int id;
    private String descricao;
    private Double preco;
    private String idSupermercado;
    private String idDepartamento;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getIdSupermercado() {
        return idSupermercado;
    }

    public void setIdSupermercado(String idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }


}
