package com.mercapp.usuario.dominio;

public class Pessoa {

    private int id;
    private String nome;
    private String telefone;
    private String numeroCartao;
    private Usuario usuario;
    private Endereco endereco;

    public final  int getId() {
        return id;
    }

    public final  void setId(int ids) {
        this.id = ids;
    }

    public  final String getNome() {
        return nome;
    }

    public final  void setNome(String nomes) {
        this.nome = nomes;
    }

    public  final String getTelefone() {
        return telefone;
    }

    public final  void setTelefone(String telefones) {
        this.telefone = telefones;
    }

    public final  String getNumeroCartao() {
        return numeroCartao;
    }

    public  final void setNumeroCartao(String numeroCartoes) {
        this.numeroCartao = numeroCartoes;
    }

    public final Endereco getEndereco() {
        return endereco;
    }

    public  final void setEndereco(Endereco enderecos) {
        this.endereco = enderecos;
    }

    public  final Usuario getUsuario() {
        return usuario;
    }

    public  final void setUsuario(Usuario usuarios) {
        this.usuario = usuarios;
    }
}
