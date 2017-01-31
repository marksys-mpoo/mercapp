package com.mercapp.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

public class BDHelper extends SQLiteOpenHelper {

    public static final String NOME_BD = "mercapp.sqlite";
    public static final int VERSAO_BD = 1;

    //Tabela de Usuario
    public static final String TBL_USUARIO = "usuarios";
    public static final String COLUNA_ID = "_id_usuario";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";

    //Tabela de Pessoa
    public static final String TBL_PESSOA = "pessoas";
    public static final String COLUNA_ID_PESSOA= "_id_pessoa";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_NUMEROCARTAO = "numerocartao";
    public static final String COLUNA_ID_USUARIO_PESSOA = "_id_usuario_pessoa";
    public static final String COLUNA_ID_ENDERECO_PESSOA = "_id_endereco";

    //Tabela de Endereço
    public static final String TBL_ENDERECO = "endereco";
    public static final String COLUNA_ID_ENDERECO= "_id_endereco";
    public static final String COLUNA_RUA = "rua";
    public static final String COLUNA_NUMERO = "numero";
    public static final String COLUNA_BAIRRO = "bairro";
    public static final String COLUNA_CIDADE = "cidade";
    public static final String COLUNA_UF = "uf";
    public static final String COLUNA_CEP = "cep";

    //Tabela Supermercado
    public static final String TBL_SUPERMERCADO = "Supermercado";
    public static final String COLUNA_ID_SUPERMERCADO = "_id";
    public static final String COLUNA_NOME_SUPERMERCADO = "nome";
    public static final String COLUNA_TELEFONE_SUPERMERCADO = "telefone";
    public static final String COLUNA_LATITUDE_SUPERMERCADO = "latitude";
    public static final String COLUNA_LONGITUDE_SUPERMERCADO = "longitude";

    //Tabela Produto
    public static final String TBL_PRODUTO = "produto";
    public static final String COLUNA_ID_PRODUTO = "_id";
    public static final String COLUNA_DESCRICAO_PRODUTO = "descricao";
    public static final String COLUNA_NOME_PRODUTO = "nomeProduto";
    public static final String COLUNA_IMAGEM_PRODUTO = "imagemProduto";
    public static final String COLUNA_PRECO_PRODUTO = "preco";
    public static final String COLUNA_ID_SUPERMERCADO_PRODUTO = "_id_supermercado";
    public static final String COLUNA_PRODUTO_DEPARTAMENTO = "_id_departamento";
    public static final String COLUNA_POSICAO_SPINNER_SUPERMERCADO = "posicaoSpinnerSupermercado";
    public static final String COLUNA_POSICAO_SPINNER_IMAGEM_PRODUTO = "posicaoSpinnerImagemProduto";

    //Tabela Carrinho
    public static final String TBL_CARRINHO = "carrinho";
    public static final String COLUNA_ID_CARRINHO = "_id_carrinho";
    public static final String COLUNA_VALOR_TOTAL = "valor_total";
    public static final String COLUNA_DESCONTOS = "descontos";
    public static final String COLUNA_PRODUTOS = "_id_produtos";

    //Tabela Recomendação Produtos
    public static final String TBL_RECOMENDACAO_PRODUTO = "recomendacao_produto";
    public static final String COLUNA_RECOMENDACAO_PRODUTO_ID = "_id_recomendacao_produto";
    public static final String COLUNA_RECOMENDACAO_PRODUTO_ID_PRODUTO = "recomendacao_produto_id_produto";
    public static final String COLUNA_RECOMENDACAO_PRODUTO_ID_USUARIO = "recomendacao_produto_id_usuario";
    public static final String COLUNA_RECOMENDACAO_PRODUTO_ID_SUPERMERCADO = "recomendacao_produto_id_supermercado";
    public static final String COLUNA_RECOMENDACAO_PRODUTO_NOTA = "recomendacao_produto_nota";

    public BDHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }


    @Override
    public final void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLScript.getTabelaUsuario());
        db.execSQL(SQLScript.getTabelaPessoa());
        db.execSQL(SQLScript.getTabelaEndereco());
        db.execSQL(SQLScript.getTabelaCarrinho());
        db.execSQL(SQLScript.getTabelaProduto());
        db.execSQL(SQLScript.getTabelaSupermercado());
        db.execSQL(SQLScript.getTabelaRecomendacaoProduto());
        CarregamentoDadosBD.carregarUsuariosBD(db);
        CarregamentoDadosBD.carregarSupermercadosBD(db);
        CarregamentoDadosBD.carregarProdutosBD(db);
        CarregamentoDadosBD.carregarRecomendacoesProdutosBD(db);
    }

    @Override
    public final void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        final String testeTabelaExiste = "DROP TABLE IF EXISTIS ";
        db.execSQL(testeTabelaExiste + TBL_USUARIO);
        db.execSQL(testeTabelaExiste + TBL_PESSOA);
        db.execSQL(testeTabelaExiste + TBL_ENDERECO);
        db.execSQL(testeTabelaExiste + TBL_CARRINHO);
        db.execSQL(testeTabelaExiste + TBL_PRODUTO);
        db.execSQL(testeTabelaExiste + TBL_SUPERMERCADO);
        db.execSQL(testeTabelaExiste + TBL_RECOMENDACAO_PRODUTO);
        onCreate(db);
    }
}
