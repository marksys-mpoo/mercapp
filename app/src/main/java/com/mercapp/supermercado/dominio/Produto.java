package com.mercapp.supermercado.dominio;

public class Produto {
    private int id;
    private String descricao;
    private String nome;
    private Double preco;
    private Supermercado supermercado;
    private String idDepartamento;
    private int imageProduto;
    private int posicaoSpinner;
    private int posicaoSpinnerImagem;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getImageProduto() {
        return imageProduto;
    }

    public void setImageProduto(int imageProduto) {
        this.imageProduto = imageProduto;
    }

    public int getPosicaoSpinner() {
        return posicaoSpinner;
    }

    public void setPosicaoSpinner(int posicaoSpinner) {
        this.posicaoSpinner = posicaoSpinner;
    }

    public int getPosicaoSpinnerImagem() {
        return posicaoSpinnerImagem;
    }

    public void setPosicaoSpinnerImagem(int posicaoSpinnerImagem) {
        this.posicaoSpinnerImagem = posicaoSpinnerImagem;
    }

}
