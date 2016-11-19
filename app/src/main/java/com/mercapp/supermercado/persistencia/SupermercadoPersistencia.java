package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

        //values.put(bdHelper.COLUNA_ID_SUPERMERCADO, supermercado.getId());
        values.put(bdHelper.COLUNA_NOME_SUPERMERCADO, supermercado.getNome());
        values.put(bdHelper.COLUNA_TELEFONE_SUPERMERCADO, supermercado.getTelefone());


        db.insert(bdHelper.TBL_SUPERMERCADO, null, values);
        db.close();
    }

    public void cadastrarProduto(Produto produto){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_DESCRICAO, produto.getDescricao());
        values.put(bdHelper.COLUNA_PRECO, produto.getPreco());
        values.put(bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO, produto.getIdSupermercado());

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

    public Produto buscarProduto(String descricao){
        SQLiteDatabase db = bdHelper.getReadableDatabase();

        Produto produto = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_DESCRICAO+" LIKE ? ", new String[]{descricao});
        if (cursor.moveToFirst()){
            produto = criarProduto(cursor);
        }
        cursor.close();
        db.close();
        return produto;
    }

    public Cursor carregaDados(){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor;
        String[] campos =  {BDHelper.COLUNA_ID_SUPERMERCADO, BDHelper.COLUNA_NOME_SUPERMERCADO, BDHelper.COLUNA_TELEFONE_SUPERMERCADO};
        cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_SUPERMERCADO, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosProdutos(){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor;
        String[] campos =  {BDHelper.COLUNA_ID_PRODUTO, BDHelper.COLUNA_DESCRICAO, BDHelper.COLUNA_PRECO, BDHelper.COLUNA_ID_SUPERMERCADO_PRODUTO};
        cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_PRODUTO, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosProdutosDoSupermercado(String idSuper){
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        /*Cursor cursor;
        String[] campos =  {BDHelper.COLUNA_ID_PRODUTO, BDHelper.COLUNA_DESCRICAO, BDHelper.COLUNA_PRECO, BDHelper.COLUNA_ID_SUPERMERCADO_PRODUTO};
        cursor = db.rawQuery("SELECT * FROM " + BDHelper.TBL_PRODUTO, null);*/
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO+" LIKE ? ", new String[]{idSuper});
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
        produto.setPreco(cursor.getString(2));
        produto.setIdSupermercado(cursor.getString(3));
        return produto;
    }
}
