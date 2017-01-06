package com.mercapp.usuario.dominio;

public class Endereco {

    private String id;

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public final String getId() {
        return id;
    }

    public final void setId(String ider) {
        this.id = ider;
    }
    public  final String getRua() {
        return rua;
    }

    public final void setRua(String ruas) {
        this.rua = ruas;
    }

    public final String getUf() {
        return uf;
    }

    public final void setUf(String ufs) {
        this.uf = ufs;
    }

    public final String getNumero() {
        return numero;
    }

    public final  void setNumero(String numeros) {
        this.numero = numeros;
    }

    public final  String getBairro() {
        return bairro;
    }

    public final void setBairro(String bairros) {
        this.bairro = bairros;
    }

    public final  String getCep() {
        return cep;
    }

    public final  void setCep(String ceps) {
        this.cep = ceps;
    }

    public  final String getCidade() {
        return cidade;
    }

    public  final void setCidades(String cidades) {
        this.cidade = cidades;
    }
}
