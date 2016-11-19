package com.mercapp.supermercado.dominio;

import java.util.ArrayList;

/**
 * Created by wellington on 30/10/16.
 */

public class Supermercado {

    private int id;
    private String nome;
    private String telefone;

    private ArrayList<Produto> idProdutos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}