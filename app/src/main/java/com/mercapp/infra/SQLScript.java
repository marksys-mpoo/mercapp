package com.mercapp.infra;

public class SQLScript {
    public static String getTabelaUsuario() {

        StringBuilder usuarioBuilder = new StringBuilder();
        usuarioBuilder.append("CREATE TABLE  usuarios ( ");
        usuarioBuilder.append("_id_usuario   integer primary key autoincrement,   ");
        usuarioBuilder.append("email  text not null unique,  ");
        usuarioBuilder.append("senha  text not null );" );
        return usuarioBuilder.toString();
    }

    public static String getTabelaPessoa() {

        StringBuilder pessoaBuilder = new StringBuilder();
        pessoaBuilder.append("CREATE TABLE  pessoas  (  ");
        pessoaBuilder.append("_id_pessoa   integer primary key autoincrement,   ");
        pessoaBuilder.append("nome  text not null,  ");
        pessoaBuilder.append("telefone text not null, ");
        pessoaBuilder.append("numerocartao text not null, ");
        pessoaBuilder.append("_id_usuario_pessoa text not null, ");
        pessoaBuilder.append("_id_endereco   integer );");
        return pessoaBuilder.toString();
    }
    public static String getTabelaEndereco() {

        StringBuilder enderecoBuilder = new StringBuilder();
        enderecoBuilder.append("CREATE TABLE  endereco  (  ");
        enderecoBuilder.append("_id_endereco   integer primary key autoincrement,   ");
        enderecoBuilder.append("rua  text not null,  ");
        enderecoBuilder.append("numero  text not null,  ");
        enderecoBuilder.append("bairro  text not null,  ");
        enderecoBuilder.append("cidade  text not null,  ");
        enderecoBuilder.append("uf  text not null,  ");
        enderecoBuilder.append("cep  text not null );");
        return enderecoBuilder.toString();
    }

    public static String getTabelaSupermercado() {

        StringBuilder supermercadoBuilder = new StringBuilder();
        supermercadoBuilder.append("CREATE TABLE  supermercado  (  ");
        supermercadoBuilder.append("_id_supermercado   integer primary key autoincrement,   ");
        supermercadoBuilder.append("nome  text not null,  ");
        supermercadoBuilder.append("telefone  text not null,  ");
        supermercadoBuilder.append("_id_produtos   integer,  ");
        supermercadoBuilder.append("foreign key (_id_produtos ) references  produto ( _id_produtos ) );");
        return supermercadoBuilder.toString();
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

