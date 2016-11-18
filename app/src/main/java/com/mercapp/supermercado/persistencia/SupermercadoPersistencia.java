package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.BDHelper;
import com.mercapp.supermercado.dominio.Supermercado;


public class SupermercadoPersistencia {

    private Context _context;

    private BDHelper bdHelperS;


    public SupermercadoPersistencia(Context context)
    {
        _context = context;
        bdHelperS = new BDHelper(_context);
    }

    public void cadastrarSupermercado(Supermercado supermercado){
        SQLiteDatabase db = bdHelperS.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(bdHelperS.COLUNA_ID_SUPERMERCADO, supermercado.getId());
        values.put(bdHelperS.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelperS.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());


        db.insert(bdHelperS.TBL_SUPERMERCADO, null, values);
        db.close();
    }

    public Supermercado buscarSupermercado(String nome){
        SQLiteDatabase db = bdHelperS.getReadableDatabase();

        Supermercado supermercadoS = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelperS.TBL_SUPERMERCADO +
                " WHERE "+ bdHelperS.COLUNA_NOME_SUPERMERCADO+" LIKE ? ", new String[]{nome});

        if (cursor.moveToFirst()){
            supermercadoS = criarSupermercado(cursor);
        }
        cursor.close();
        db.close();
        return supermercadoS;
    }

    public Cursor carregaDados(){
        SQLiteDatabase db = bdHelperS.getReadableDatabase();
        Cursor cursor;
        String[] campos =  {BDHelper.COLUNA_ID_SUPERMERCADO, BDHelper.COLUNA_NOME_SUPERMERCADO, BDHelper.COLUNA_TELEFONE_SUPERMERCADO};
        cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_SUPERMERCADO, null);

        //BDHelper.TBL_SUPERMERCADO, campos, null, null, null, null, null, null);

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
        return supermercado;
    }
}
