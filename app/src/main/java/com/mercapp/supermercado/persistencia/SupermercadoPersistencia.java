package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.infra.persistencia.BDHelper;
import com.mercapp.supermercado.dominio.Supermercado;

import java.util.ArrayList;
import java.util.List;

public class SupermercadoPersistencia {

    private static final String SELECT = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private BDHelper bdHelper;

    public SupermercadoPersistencia(Context context)
    {
        bdHelper = new BDHelper(context);
    }

    public final  void cadastrar(Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelper.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());
        values.put(bdHelper.COLUNA_LATITUDE_SUPERMERCADO, supermercado.getCoordenadas().latitude);
        values.put(bdHelper.COLUNA_LONGITUDE_SUPERMERCADO, supermercado.getCoordenadas().longitude);

        db.insert(bdHelper.TBL_SUPERMERCADO, null, values);
        db.close();
    }

    public  final void editar(Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelper.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());
        values.put(bdHelper.COLUNA_LATITUDE_SUPERMERCADO, supermercado.getCoordenadas().latitude);
        values.put(bdHelper.COLUNA_LONGITUDE_SUPERMERCADO, supermercado.getCoordenadas().longitude);

        db.update(bdHelper.TBL_SUPERMERCADO, values, "_id = ?", new String[]{""+supermercado.getId()});
        db.close();
    }

    public final  void deletar(Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        String where = bdHelper.COLUNA_ID_SUPERMERCADO + "=" + supermercado.getId();
        db.delete(bdHelper.TBL_SUPERMERCADO, where, null);
        db.close();
    }

    public final  Supermercado buscar(String nome){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Supermercado supermercado = null;
        Cursor cursor = db.rawQuery(SELECT+ bdHelper.TBL_SUPERMERCADO +
                WHERE + bdHelper.COLUNA_NOME_SUPERMERCADO+" LIKE ? ", new String[]{nome});
        if (cursor.moveToFirst()){
            supermercado = criarSupermercado(cursor);
        }
        cursor.close();
        db.close();
        return supermercado;
    }

    public final  Supermercado buscar(int id){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Supermercado supermercado = null;
        Cursor cursor = db.rawQuery(SELECT+ bdHelper.TBL_SUPERMERCADO +
                WHERE + bdHelper.COLUNA_ID_SUPERMERCADO+" LIKE ? ", new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()){
            supermercado = criarSupermercado(cursor);
        }
        cursor.close();
        db.close();
        return supermercado;
    }

    public  final List<Supermercado> listar(String inputText){
        List<Supermercado> supermercados = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT+ bdHelper.TBL_SUPERMERCADO +
                WHERE + bdHelper.COLUNA_NOME_SUPERMERCADO+" LIKE '%" + inputText + "%'", null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            supermercados.add(criarSupermercado(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return supermercados;
    }

    public final  List<Supermercado> listar(){
        List<Supermercado> supermercados = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT + BDHelper.TBL_SUPERMERCADO, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            supermercados.add(criarSupermercado(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return supermercados;
    }
    public  final List<String> listaSupermercado (){
        List<String> supermercados = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT + BDHelper.TBL_SUPERMERCADO, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            supermercados.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return supermercados;
    }

    private Supermercado criarSupermercado(Cursor cursor){
        Supermercado supermercado = new Supermercado();
        final int columnIndex0 = 0;
        final int columnIndex1 = 1;
        final int columnIndex2 = 2;
        final int columnIndex3 = 3;
        final int columnIndex4 = 4;
        supermercado.setId(cursor.getInt(columnIndex0));
        supermercado.setNome(cursor.getString(columnIndex1));
        supermercado.setTelefone(cursor.getString(columnIndex2));
        supermercado.setCoordenadas(new LatLng(cursor.getDouble(columnIndex3),cursor.getDouble(columnIndex4)));
        return supermercado;
    }

}
