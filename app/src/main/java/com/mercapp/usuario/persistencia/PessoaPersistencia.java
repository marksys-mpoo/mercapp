package com.mercapp.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.persistencia.BDHelper;
import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Pessoa;

public class PessoaPersistencia {

    private BDHelper bdHelper;
    private Session session = Session.getInstanciaSessao();

    public PessoaPersistencia(Context contexto){
        bdHelper = new BDHelper(contexto);
    }

    public final  void cadastrar(Pessoa pessoa){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME, pessoa.getNome());
        values.put(bdHelper.COLUNA_TELEFONE, pessoa.getTelefone());
        values.put(bdHelper.COLUNA_NUMEROCARTAO, pessoa.getNumeroCartao());
        values.put(bdHelper.COLUNA_ID_USUARIO_PESSOA, session.getUsuarioLogado().getId());
        db.insert(bdHelper.TBL_PESSOA, null, values);

        db.close();
    }

    public final  void editar(Pessoa pessoa){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME, pessoa.getNome());
        values.put(bdHelper.COLUNA_TELEFONE, pessoa.getTelefone());
        values.put(bdHelper.COLUNA_NUMEROCARTAO, pessoa.getNumeroCartao());
        db.update(bdHelper.TBL_PESSOA, values, "_id_pessoa = ?", new String[]{""+pessoa.getId()});

        db.close();
    }

    public  final Pessoa buscar(String numeroCartao){
        SQLiteDatabase db = bdHelper.getReadableDatabase();

        Pessoa pessoa = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + bdHelper.TBL_PESSOA +" WHERE "
                + bdHelper.COLUNA_NUMEROCARTAO + " LIKE ? ", new String[]{numeroCartao});

        if (cursor.moveToFirst()){
            pessoa = criarPessoa(cursor);
        }
        cursor.close();
        db.close();
        return pessoa;
    }

    public  final Pessoa buscar(int usuario){
        SQLiteDatabase db = bdHelper.getReadableDatabase();

        Pessoa pessoa = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + bdHelper.TBL_PESSOA +
                " WHERE " + bdHelper.COLUNA_ID_USUARIO_PESSOA + " LIKE ? ", new String[]{Integer.toString(usuario)});

        if (cursor.moveToFirst()){
            pessoa = criarPessoa(cursor);
        }
        cursor.close();
        db.close();
        return pessoa;
    }

    private Pessoa criarPessoa(Cursor cursor){
        final int columnIndex0 = 0;
        final int columnIndex1 = 1;
        final int columnIndex2 = 2;
        final int columnIndex3 = 3;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(cursor.getInt(columnIndex0));
        pessoa.setNome(cursor.getString(columnIndex1));
        pessoa.setTelefone(cursor.getString(columnIndex2));
        pessoa.setNumeroCartao(cursor.getString(columnIndex3));

        return pessoa;
    }
}
