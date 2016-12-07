package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public void cadastrarSupermercado(Supermercado supermercado){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bdHelper.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelper.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());
        values.put(bdHelper.COLUNA_LATITUDE_SUPERMERCADO, supermercado.getCoordenadas().latitude);
        values.put(bdHelper.COLUNA_LONGITUDE_SUPERMERCADO, supermercado.getCoordenadas().longitude);
        db.insert(bdHelper.TBL_SUPERMERCADO, null, values);
        db.close();
    }

    public void cadastrarProduto(Produto produto){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bdHelper.COLUNA_DESCRICAO, produto.getDescricao());
        values.put(bdHelper.COLUNA_PRECO_PRODUTO, produto.getPreco());
        values.put(bdHelper.COLUNA_NOME_PRODUTO, produto.getNome());
        values.put(bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO, produto.getIdSupermercado());
        values.put(bdHelper.COLUNA_PRODUTO_DEPARTAMENTO, produto.getIdDepartamento());
        db.insert(bdHelper.TBL_PRODUTO, null, values);
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

    public Cursor buscarSupermercadoPorNome(String inputText) throws SQLException {
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

    public Produto buscarProduto(Integer id){
        String id_string = id.toString();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Produto produto = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_PRODUTO+" LIKE ? ", new String[]{id_string});
        if (cursor.moveToFirst()){
            produto = criarProduto(cursor);
        }
        cursor.close();
        db.close();
        return produto;
    }

    public Produto buscarProdutoNome(String nome){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Produto produto = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_NOME_PRODUTO+" LIKE ? ", new String[]{nome});
        if (cursor.moveToFirst()){
            produto = criarProduto(cursor);
        }
        cursor.close();
        db.close();
        return produto;
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

    public Cursor listaDadosProdutos(){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_PRODUTO, null);
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
        return supermercado;
    }

    private Produto criarProduto(Cursor cursor){
        Produto produto = new Produto();
        produto.setId(cursor.getInt(0));
        produto.setDescricao(cursor.getString(1));
        produto.setPreco(cursor.getDouble(2));
        produto.setIdSupermercado(cursor.getString(3));
        return produto;
    }



}
