package com.mercapp.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    public static final String NOME_BD = "mercapp.sqlite";
    public static final int VERSAO_BD = 1;

    public static final String TBL_USUARIO = "usuarios";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";


    private static final String CREATE_TBL_USUARIOS = "CREATE TABLE " + TBL_USUARIO + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUNA_EMAIL + " TEXT,"
            + COLUNA_SENHA + " TEXT);";

    public BDHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTIS " + TBL_USUARIO);
        onCreate(db);
    }


}
