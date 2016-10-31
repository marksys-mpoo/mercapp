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
    public static final String COLUNA_ID_ENDERECO = "_id_endereco";
    public static final String COLUNA_ID_USARIO = "_id_usuario";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_USUARIO);
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_PESSOA);
        onCreate(db);
    }


}
