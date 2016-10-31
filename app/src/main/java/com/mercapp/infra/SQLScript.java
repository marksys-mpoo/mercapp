package com.mercapp.infra;

/**
 * Created by wellington on 30/10/16.
 */

public class SQLScript {
    public static String getTabelaUsuario() {

        StringBuilder usuarioBuilder = new StringBuilder();
        usuarioBuilder.append("CREATE TABLE  usuarios ( ");
        usuarioBuilder.append("_id_usuario   integer primary key autoincrement,   ");
        usuarioBuilder.append("email  text not null unique,  ");
        usuarioBuilder.append("senha  text not null );");
        
        return usuarioBuilder.toString();
    }

    public static String getTabelaPessoa() {

        StringBuilder pessoaBuilder = new StringBuilder();
        pessoaBuilder.append("CREATE TABLE  pessoas  (  ");
        pessoaBuilder.append("_id_pessoa   integer primary key autoincrement,   ");
        pessoaBuilder.append("nome  text not null,  ");
        pessoaBuilder.append("_id_usuario   integer,  ");
        pessoaBuilder.append("foreign key (_id_usuario ) references  usuarios ( _id_usuario ) );");
        pessoaBuilder.append("_id_endereco   integer,  ");
        pessoaBuilder.append("foreign key (_id_endereco ) references  endereco ( _id_endereco ) );");
        return pessoaBuilder.toString();
    }
    public static String getTabelaEndereco() {

        StringBuilder enderecoBuilder = new StringBuilder();
        enderecoBuilder.append("CREATE TABLE  endereco  (  ");
        enderecoBuilder.append("_id_endereco   integer primary key autoincrement,   ");
        enderecoBuilder.append("numero  text not null,  ");
        enderecoBuilder.append("bairro  text not null,  ");
        enderecoBuilder.append("cidade  text not null,  ");
        enderecoBuilder.append("uf  text not null,  ");
        enderecoBuilder.append("cep  text not null,  ");
        enderecoBuilder.append("endereco  text not null );");
        return enderecoBuilder.toString();
    }

    public static String getTabelaCarrinho() {

        StringBuilder carrinhoBuilder = new StringBuilder();
        carrinhoBuilder.append("CREATE TABLE  carrinho  (  ");
        carrinhoBuilder.append("_id_carrinho   integer primary key autoincrement,   ");
        carrinhoBuilder.append("valor_total  text not null,  ");
        carrinhoBuilder.append("descontos  text not null,  ");
        carrinhoBuilder.append("_id_produtos   integer,  ");
        carrinhoBuilder.append("foreign key (_id_produtos ) references  produto ( _id_produtos ) );");
        return carrinhoBuilder.toString();
    }
    public static String getTabelaProduto() {

        StringBuilder carrinhoBuilder = new StringBuilder();
        carrinhoBuilder.append("CREATE TABLE  produto  (  ");
        carrinhoBuilder.append("_id_produto   integer primary key autoincrement,   ");
        carrinhoBuilder.append("descricao  text not null,  ");
        carrinhoBuilder.append("preco  text not null );");
        return carrinhoBuilder.toString();
    }
}

