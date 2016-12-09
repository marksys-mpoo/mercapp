package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.BDHelper;
import com.mercapp.supermercado.dominio.Produto;

/**
 * Created by WELLINGTON on 07/12/2016.
 */

public class ProdutoPersistencia {
    private Context _context;
    private BDHelper bdHelper;

    public ProdutoPersistencia(Context _context) {
        this._context = _context;
        bdHelper = new BDHelper(_context);
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
    private Produto criarProduto(Cursor cursor){
        Produto produto = new Produto();
        produto.setId(cursor.getInt(0));
        produto.setDescricao(cursor.getString(1));
        produto.setPreco(cursor.getDouble(2));
        produto.setIdSupermercado(cursor.getInt(3));
        return produto;
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

}
