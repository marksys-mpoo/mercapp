package com.mercapp.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.BDHelper;
import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Pessoa;

public class PessoaPersistencia {

    private Context _context;
    private BDHelper bdHelper;
    private Session session = Session.getInstanciaSessao();

    public PessoaPersistencia(Context context){
        _context = context;
        bdHelper = new BDHelper(_context);
    }

    public void cadastrarPessoa(Pessoa pessoa){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME, pessoa.getNome());
        values.put(bdHelper.COLUNA_TELEFONE, pessoa.getTelefone());
        values.put(bdHelper.COLUNA_NUMEROCARTAO, pessoa.getNumeroCartao());
        values.put(bdHelper.COLUNA_ID_USUARIO_PESSOA, session.getUsuarioLogado().getId());
        db.insert(bdHelper.TBL_PESSOA, null, values);

        db.close();
    }

    public void editarPessoa(Pessoa pessoa){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME, pessoa.getNome());
        values.put(bdHelper.COLUNA_TELEFONE, pessoa.getTelefone());
        values.put(bdHelper.COLUNA_NUMEROCARTAO, pessoa.getNumeroCartao());
        values.put(bdHelper.COLUNA_ID_USUARIO_PESSOA, session.getUsuarioLogado().getId());
        db.update(bdHelper.TBL_PESSOA, values, "_id = ? ", new String[]{""+pessoa.getId()});

        db.close();
    }

    public Pessoa buscarPessoa(String numeroCartao){
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

    public Pessoa buscarPessoa(int usuario){
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

    public Pessoa criarPessoa(Cursor cursor){

        Pessoa pessoa = new Pessoa();
        pessoa.setId(cursor.getInt(0));
        pessoa.setNome(cursor.getString(1));
        pessoa.setTelefone(cursor.getString(2));
        pessoa.setNumeroCartao(cursor.getString(3));

        return pessoa;
    }
}
