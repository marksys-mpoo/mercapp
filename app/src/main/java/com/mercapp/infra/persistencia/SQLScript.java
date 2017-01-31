package com.mercapp.infra.persistencia;

public final class SQLScript {

    private SQLScript() {
    }

    static String getTabelaUsuario() {

        StringBuilder usuarioBuilder = new StringBuilder();
        usuarioBuilder.append("CREATE TABLE  usuarios ( ");
        usuarioBuilder.append("_id_usuario   integer primary key autoincrement,   ");
        usuarioBuilder.append("email  text not null unique,  ");
        usuarioBuilder.append("senha  text not null );" );
        return usuarioBuilder.toString();
    }

    static String getTabelaPessoa() {

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
    static String getTabelaEndereco() {

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

    static String getTabelaSupermercado() {

        StringBuilder supermercadoBuilder = new StringBuilder();
        supermercadoBuilder.append("CREATE TABLE  Supermercado  (  ");
        supermercadoBuilder.append("_id integer primary key autoincrement,   ");
        supermercadoBuilder.append("nome  text not null unique,  ");
        supermercadoBuilder.append("telefone  text not null,  ");
        supermercadoBuilder.append("latitude double,  ");
        supermercadoBuilder.append("longitude double );");
        return supermercadoBuilder.toString();
    }

    static String getTabelaCarrinho() {

        StringBuilder carrinhoBuilder = new StringBuilder();
        carrinhoBuilder.append("CREATE TABLE  carrinho  (  ");
        carrinhoBuilder.append("_id_carrinho   integer primary key autoincrement,   ");
        carrinhoBuilder.append("valor_total  text not null,  ");
        carrinhoBuilder.append("descontos  text not null,  ");
        carrinhoBuilder.append("_id_produtos   integer,  ");
        carrinhoBuilder.append("foreign key (_id_produtos ) references  produto ( _id_produtos ) );");
        return carrinhoBuilder.toString();
    }
    static String getTabelaProduto() {

        StringBuilder produtoBuilder = new StringBuilder();
        produtoBuilder.append("CREATE TABLE  produto  (  ");
        produtoBuilder.append("_id integer primary key autoincrement,   ");
        produtoBuilder.append("nomeProduto  text not null,  ");
        produtoBuilder.append("imagemProduto int not null,  ");
        produtoBuilder.append("descricao  text not null,  ");
        produtoBuilder.append("preco  real not null,  ");
        produtoBuilder.append("_id_supermercado integer not null,  ");
        produtoBuilder.append("_id_departamento text not null,  ");
        produtoBuilder.append("posicaoSpinnerSupermercado int not null,  ");
        produtoBuilder.append("posicaoSpinnerImagemProduto int not null );  ");

        return produtoBuilder.toString();
    }

    static String getTabelaRecomendacaoProduto() {

        StringBuilder recomendacaoProdutoBuilder = new StringBuilder();
        recomendacaoProdutoBuilder.append("CREATE TABLE  recomendacao_produto  (  ");
        recomendacaoProdutoBuilder.append("_id_recomendacao_produto integer primary key autoincrement,   ");
        recomendacaoProdutoBuilder.append("recomendacao_produto_id_produto integer not null,  ");
        recomendacaoProdutoBuilder.append("recomendacao_produto_id_usuario integer not null,  ");
        recomendacaoProdutoBuilder.append("recomendacao_produto_id_supermercado integer not null,  ");
        recomendacaoProdutoBuilder.append("recomendacao_produto_nota integer not null );  ");

        return recomendacaoProdutoBuilder.toString();
    }
}

