package com.mercapp.supermercado.dominio;

public class Produto {
    private int id;
    private String descricao;
    private String nome;
    private Double preco;
    private Supermercado supermercado;
    private int numeroDepartamento;
    private int imageProduto;
    private int posicaoSpinnerSupermercado;
    private int posicaoSpinnerImagem;

    public  final String getNome() {
        return nome;
    }

    public  final void setNome(String nomes) {
        this.nome = nomes;
    }

    public  final String getDescricao() {
        return descricao;
    }

    public  final void setDescricao(String descricaos) {
        this.descricao = descricaos;
    }

    public  final int getId() {
        return id;
    }

    public  final void setId(int ids) {
        this.id = ids;
    }

    public  final Double getPreco() {
        return preco;
    }

    public  final void setPreco(Double precos) {
        this.preco = precos;
    }

    public  final Supermercado getSupermercado() {
        return supermercado;
    }

    public  final void setSupermercado(Supermercado supermercados) {
        this.supermercado = supermercados;
    }

    public  final int getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public  final void setNumeroDepartamento(int departamentos) {
        this.numeroDepartamento = departamentos;
    }

    public  final int getImageProduto() {
        return imageProduto;
    }

    public  final void setImageProduto(int imageProdutos) {
        this.imageProduto = imageProdutos;
    }

    public  final int getPosicaoSpinnerSupermercado() {
        return posicaoSpinnerSupermercado;
    }

    public  final void setPosicaoSpinnerSupermercado(int posicaoSpinnerSupermercados) {
        this.posicaoSpinnerSupermercado = posicaoSpinnerSupermercados;
    }

    public  final int getPosicaoSpinnerImagem() {
        return posicaoSpinnerImagem;
    }

    public final  void setPosicaoSpinnerImagem(int posicaoSpinnerImagems) {
        this.posicaoSpinnerImagem = posicaoSpinnerImagems;
    }

}
