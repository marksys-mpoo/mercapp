package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.persistencia.BDHelper;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;

import java.util.ArrayList;
import java.util.List;

public class ProdutoPersistencia {
    private Context context;
    private BDHelper bdHelper;

    public ProdutoPersistencia(Context context) {
        this.context = context;
        bdHelper = new BDHelper(context);
    }
    public void cadastrar(Produto produto){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bdHelper.COLUNA_DESCRICAO_PRODUTO, produto.getDescricao());
        values.put(bdHelper.COLUNA_PRECO_PRODUTO, produto.getPreco());
        values.put(bdHelper.COLUNA_NOME_PRODUTO, produto.getNome());
        values.put(bdHelper.COLUNA_IMAGEM_PRODUTO, produto.getImageProduto());
        values.put(bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO, produto.getSupermercado().getId());
        values.put(bdHelper.COLUNA_PRODUTO_DEPARTAMENTO, produto.getNumeroDepartamento());
        values.put(bdHelper.COLUNA_POSICAO_SPINNER_SUPERMERCADO, produto.getPosicaoSpinnerSupermercado());
        values.put(bdHelper.COLUNA_POSICAO_SPINNER_IMAGEM_PRODUTO, produto.getPosicaoSpinnerImagem());

        db.insert(bdHelper.TBL_PRODUTO, null, values);
        db.close();
    }

    public void editar(Produto produto){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(bdHelper.COLUNA_DESCRICAO_PRODUTO, produto.getDescricao());
        values.put(bdHelper.COLUNA_PRECO_PRODUTO, produto.getPreco());
        values.put(bdHelper.COLUNA_NOME_PRODUTO, produto.getNome());
        values.put(bdHelper.COLUNA_IMAGEM_PRODUTO, produto.getImageProduto());
        values.put(bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO, produto.getSupermercado().getId());
        values.put(bdHelper.COLUNA_PRODUTO_DEPARTAMENTO, produto.getNumeroDepartamento());
        values.put(bdHelper.COLUNA_POSICAO_SPINNER_SUPERMERCADO, produto.getPosicaoSpinnerSupermercado());
        values.put(bdHelper.COLUNA_POSICAO_SPINNER_IMAGEM_PRODUTO, produto.getPosicaoSpinnerImagem());
        db.update(bdHelper.TBL_PRODUTO, values, "_id = ?", new String[]{""+produto.getId()});
        db.close();
    }

    public void deletar(Produto produto){
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        String where = bdHelper.COLUNA_ID_PRODUTO+ "=" + produto.getId();
        db.delete(bdHelper.TBL_PRODUTO, where, null);
        db.close();
    }

    public Produto buscar(Integer id){
        String idString = id.toString();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Produto produto = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_PRODUTO +" LIKE ? ", new String[]{idString});
        if (cursor.moveToFirst()){
            produto = criarProduto(cursor);
        }
        cursor.close();
        db.close();
        return produto;
    }
    public Produto buscar(String nome){
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
    public List<Produto> listar(){
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.query(BDHelper.TBL_PRODUTO, null, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            produtos.add(criarProduto(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return produtos;
    }

    private Produto criarProduto(Cursor cursor){
        Produto produto = new Produto();
        produto.setId(cursor.getInt(0));
        produto.setNome(cursor.getString(1));
        produto.setImageProduto(cursor.getInt(2));
        produto.setDescricao(cursor.getString(3));
        produto.setPreco(cursor.getDouble(4));
        produto.setNumeroDepartamento(cursor.getInt(6));
        produto.setPosicaoSpinnerSupermercado(cursor.getInt(7));
        produto.setPosicaoSpinnerImagem(cursor.getInt(8));
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(context);
        Supermercado supermercado = supermercadoPersistencia.buscar(cursor.getInt(5));
        produto.setSupermercado(supermercado);
        return produto;
    }

    public List<Produto> listaDadosProdutosDoSupermercado(String idSupermercado){
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO+" LIKE ? ", new String[]{idSupermercado});
            cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            produtos.add(criarProduto(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return produtos;
    }

    public List<Produto> listar(String idSupermercado, int idDepartamento){
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_ID_SUPERMERCADO_PRODUTO +" LIKE ? AND "+ bdHelper.COLUNA_PRODUTO_DEPARTAMENTO +" LIKE ? " , new String[]{idSupermercado, Integer.toString(idDepartamento)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            produtos.add(criarProduto(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return produtos;
    }

    public List<Produto> listar(String inputText) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ bdHelper.TBL_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_NOME_PRODUTO+" LIKE '%" + inputText + "%'", null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            produtos.add(criarProduto(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return produtos;
    }
}
