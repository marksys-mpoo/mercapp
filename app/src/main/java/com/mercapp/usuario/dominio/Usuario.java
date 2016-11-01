package com.mercapp.usuario.dominio;

public class Usuario {

    private int id;
    private String email;
    private String senha;
    private Pessoa pessoa;

    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email;}
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}