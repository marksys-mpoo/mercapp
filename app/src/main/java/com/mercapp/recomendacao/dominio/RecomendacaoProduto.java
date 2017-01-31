package com.mercapp.recomendacao.dominio;

public class RecomendacaoProduto {

    private Integer id;
    private Integer idProduto;
    private Integer idUsuario;
    private Integer idSupermercado;
    private Double nota;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdSupermercado() {
        return idSupermercado;
    }

    public void setIdSupermercado(Integer idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

}
