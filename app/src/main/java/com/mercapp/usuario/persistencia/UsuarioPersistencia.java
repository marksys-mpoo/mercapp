package com.mercapp.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.persistencia.BDHelper;
import com.mercapp.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;


public class UsuarioPersistencia {


    private BDHelper bdHelper;


    public UsuarioPersistencia(Context context) {
        bdHelper= new BDHelper(context);
    }

    public  final void cadastrar(Usuario usuario){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_EMAIL, usuario.getEmail());
        values.put(bdHelper.COLUNA_SENHA, usuario.getSenha());

        db.insert(bdHelper.TBL_USUARIO, null, values);
        db.close();
    }

    public final  Usuario buscar(String email, String senha){
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

    public  final Usuario buscar(String email){
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

    public  final Usuario buscar(Integer id){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Usuario usuario = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+bdHelper.TBL_USUARIO +
                " WHERE "+bdHelper.COLUNA_ID+" LIKE ? ", new String[]{""+id});
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

    public  final List<Usuario> listarTodosUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.query(BDHelper.TBL_USUARIO, null, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            usuarios.add(criarUsuario(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return usuarios;
    }
}
