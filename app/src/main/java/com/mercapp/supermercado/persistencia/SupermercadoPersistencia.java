package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.games.internal.api.StatsImpl;
import com.google.android.gms.maps.model.LatLng;
import com.mercapp.infra.BDHelper;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;

public class SupermercadoPersistencia {

    private Context _context;
    private BDHelper bdHelper;

    public SupermercadoPersistencia(Context context)
    {
        _context = context;
        bdHelper = new BDHelper(_context);
    }

    public void cadastrarAtualizar(Integer id, String textBotaoFuncao, Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bdHelper.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelper.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());
        values.put(bdHelper.COLUNA_LATITUDE_SUPERMERCADO, supermercado.getCoordenadas().latitude);
        values.put(bdHelper.COLUNA_LONGITUDE_SUPERMERCADO, supermercado.getCoordenadas().longitude);
        if (textBotaoFuncao == "salvar") {
            db.insert(bdHelper.TBL_SUPERMERCADO, null, values);
        } else if (textBotaoFuncao == "atualizar") {
            String where;
            where = bdHelper.COLUNA_ID_SUPERMERCADO + "=" + id;
            db.update(bdHelper.TBL_SUPERMERCADO,values,where,null);
        }
        db.close();
    }

    public void deletar(int id){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        String where = bdHelper.COLUNA_ID_SUPERMERCADO + "=" + id;
        db.delete(bdHelper.TBL_SUPERMERCADO, where, null);
        db.close();
    }

    public Supermercado buscarSupermercado(String nome){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Supermercado supermercado = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_SUPERMERCADO +
                " WHERE "+ bdHelper.COLUNA_NOME_SUPERMERCADO+" LIKE ? ", new String[]{nome});
        if (cursor.moveToFirst()){
            supermercado = criarSupermercado(cursor);
        }
        cursor.close();
        db.close();
        return supermercado;
    }

    public Cursor listarSupermercadosPorParteDoNome(String inputText) throws SQLException {
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_SUPERMERCADO +
                " WHERE "+ bdHelper.COLUNA_NOME_SUPERMERCADO+" LIKE '%" + inputText + "%'", null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor listaDados(){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_SUPERMERCADO, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor listaDadosProdutosDoSupermercado(String idSupermercado){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO+" LIKE ? ", new String[]{idSupermercado});
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor listaProdutosDoSupermercadoPorDepartamentoPersistencia(String idSupermercado, String idDepartamento){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO +" LIKE ? AND "+ bdHelper.COLUNA_PRODUTO_DEPARTAMENTO +" LIKE ? " , new String[]{idSupermercado,idDepartamento});
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    private Supermercado criarSupermercado(Cursor cursor){
        Supermercado supermercado = new Supermercado();
        supermercado.setId(cursor.getInt(0));
        supermercado.setNome(cursor.getString(1));
        supermercado.setTelefone(cursor.getString(2));
        supermercado.setCoordenadas(new LatLng(cursor.getDouble(3),cursor.getDouble(4)));
        return supermercado;
    }

}
