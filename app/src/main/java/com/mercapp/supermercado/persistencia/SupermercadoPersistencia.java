package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.infra.BDHelper;
import com.mercapp.supermercado.dominio.Supermercado;

import java.util.ArrayList;
import java.util.List;

public class SupermercadoPersistencia {

    private Context _context;
    private BDHelper bdHelper;

    public SupermercadoPersistencia(Context context)
    {
        _context = context;
        bdHelper = new BDHelper(_context);
    }

    public void cadastrar(Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelper.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());
        values.put(bdHelper.COLUNA_LATITUDE_SUPERMERCADO, supermercado.getCoordenadas().latitude);
        values.put(bdHelper.COLUNA_LONGITUDE_SUPERMERCADO, supermercado.getCoordenadas().longitude);

        db.insert(bdHelper.TBL_SUPERMERCADO, null, values);
        db.close();
    }

    public void editar(Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelper.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());
        values.put(bdHelper.COLUNA_LATITUDE_SUPERMERCADO, supermercado.getCoordenadas().latitude);
        values.put(bdHelper.COLUNA_LONGITUDE_SUPERMERCADO, supermercado.getCoordenadas().longitude);

        db.update(bdHelper.TBL_SUPERMERCADO, values, "_id = ?", new String[]{""+supermercado.getId()});
        db.close();
    }

    public void deletar(Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        String where = bdHelper.COLUNA_ID_SUPERMERCADO + "=" + supermercado.getId();
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

    public Supermercado buscarSupermercado(int id){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Supermercado supermercado = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_SUPERMERCADO +
                " WHERE "+ bdHelper.COLUNA_ID_SUPERMERCADO+" LIKE ? ", new String[]{""+id});
        if (cursor.moveToFirst()){
            supermercado = criarSupermercado(cursor);
        }
        cursor.close();
        db.close();
        return supermercado;
    }

    public List<Supermercado> listarSupermercadosPorParteDoNome(String inputText) throws SQLException {
        List<Supermercado> supermercados = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_SUPERMERCADO +
                " WHERE "+ bdHelper.COLUNA_NOME_SUPERMERCADO+" LIKE '%" + inputText + "%'", null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            supermercados.add(criarSupermercado(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return supermercados;
    }

    public List<Supermercado> listaDados(){
        List<Supermercado> supermercados = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_SUPERMERCADO, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            supermercados.add(criarSupermercado(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return supermercados;
    }
    public List<String> listaSupermercado (){
        List<String> supermercados = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_SUPERMERCADO, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            supermercados.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return supermercados;
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
    public Cursor listaProdutosDoSupermercadoPorDepartamentoPersistencia(String idSupermercado, int numDepartamento){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO +" LIKE ? AND "+ bdHelper.COLUNA_PRODUTO_DEPARTAMENTO +" LIKE ? " , new String[]{idSupermercado,""+numDepartamento});
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
