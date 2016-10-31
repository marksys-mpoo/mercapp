package com.mercapp.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mercapp.usuario.dominio.Endereco;

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
    public static final String COLUNA_ID_ENDERECO_PESSOA = "_id_endereco";
    public static final String COLUNA_ID_USARIO = "_id_usuario";

    //Tabela de Endereço
    public static final String TBL_ENDERECO = "endereco";
    public static final String COLUNA_ID_ENDERECO= "_id_endereco";
    public static final String COLUNA_NUMERO = "numero";
    public static final String COLUNA_BAIRRO = "bairro";
    public static final String COLUNA_CIDADE = "cidade";
    public static final String COLUNA_UF = "uf";
    public static final String COLUNA_CEP = "cep";
    public static final String COLUNA_ENDERECO = "endereco";

    //Tabela Supermercado
    public static final String TBL_SUPERMERCADO = "supermercado";
    public static final String COLUNA_ID_SUPERMERCADO = "_id_supermercado";
    public static final String COLUNA_NOME_SUPERMERCADO = "nome";
    public static final String COLUNA_TELEFONE_SUPERMERCADO = "telefone";
    public static final String COLUNA_ID_PRODUTOS_SUPERMERCADO = "_id_produtos";

    //Tabela Produtos
    public static final String TBL_PRODUTO = "produto";
    public static final String COLUNA_ID_PRODUTO = "_id_produtos";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_PRECO = "preco";

    //Tabela Carrinho
    public static final String TBL_CARRINHO = "carrinho";
    public static final String COLUNA_ID_CARRINHO = "_id_carrinho";
    public static final String COLUNA_VALOR_TOTAL = "valor_total";
    public static final String COLUNA_DESCONTOS = "descontos";
    public static final String COLUNA_PRODUTOS = "_id_produtos";

//    private static final String CREATE_TBL_USUARIOS = "CREATE TABLE " + TBL_USUARIO + "("
//            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + COLUNA_EMAIL + " TEXT,"
//            + COLUNA_SENHA + " TEXT);";

    public BDHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_TBL_USUARIOS);
        db.execSQL(SQLScript.getTabelaUsuario());
        db.execSQL(SQLScript.getTabelaPessoa());
        db.execSQL(SQLScript.getTabelaEndereco());
        db.execSQL(SQLScript.getTabelaCarrinho());
        db.execSQL(SQLScript.getTabelaProduto());
        db.execSQL(SQLScript.getTabelaSupermercado());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_USUARIO);
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_PESSOA);
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_ENDERECO);
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_CARRINHO);
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_SUPERMERCADO);
        onCreate(db);
    }


}
