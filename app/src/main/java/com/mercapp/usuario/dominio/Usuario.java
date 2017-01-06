package com.mercapp.usuario.dominio;

public class Usuario {

    private int id;
    private String email;
    private String senha;

    public  final String getEmail(){return email;}

    public final  void setEmail(String emails){this.email = emails;}
    public final  String getSenha() {
        return senha;
    }

    public  final void setSenha(String senhas){
        this.senha = senhas;
    }
    public  final int getId() {return id;}

    public  final void setId(int ids) {this.id = ids;}

}