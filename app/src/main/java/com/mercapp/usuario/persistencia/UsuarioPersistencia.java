package com.mercapp.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.persistencia.BDHelper;
import com.mercapp.usuario.dominio.Usuario;


public class UsuarioPersistencia {

    private Context _context;

    private BDHelper bdHelper;


    public UsuarioPersistencia(Context context)
    {
        _context = context;
        bdHelper= new BDHelper(_context);
    }

    public void cadastrar(Usuario usuario){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_EMAIL, usuario.getEmail());
        values.put(bdHelper.COLUNA_SENHA, usuario.getSenha());

        db.insert(bdHelper.TBL_USUARIO, null, values);
        db.close();
    }

    public Usuario buscar(String email, String senha){
        SQLiteDatabase db = bdHelper.getReadableDatabase();

        Usuario usuario = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+bdHelper.TBL_USUARIO +
                " WHERE "+bdHelper.COLUNA_EMAIL+" LIKE ?  AND " +
                bdHelper.COLUNA_SENHA + " LIKE ? ", new String[]{email, senha});

        if (cursor.moveToFirst()){
            usuario = criarUsuario(cursor);
        }
        cursor.close();
        db.close();
        return usuario;
    }

    public Usuario buscar(String email){
        SQLiteDatabase db = bdHelper.getReadableDatabase();

        Usuario usuario = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+bdHelper.TBL_USUARIO +
                " WHERE "+bdHelper.COLUNA_EMAIL+" LIKE ? ", new String[]{email});

        if (cursor.moveToFirst()){
            usuario = criarUsuario(cursor);
        }
        cursor.close();
        db.close();
        return usuario;
    }

    private Usuario criarUsuario(Cursor cursor){
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setEmail(cursor.getString(1));
        usuario.setSenha(cursor.getString(2));
        return usuario;
    }
}
