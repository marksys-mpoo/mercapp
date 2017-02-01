package com.mercapp.supermercado.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.persistencia.BDHelper;
import com.mercapp.supermercado.dominio.Carrinho;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoPersistencia {

    private Context context;
    private BDHelper bdHelper;
    private static final String SELECT = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String LIKE = " LIKE ? ";

    public CarrinhoPersistencia(Context contexto) {
        this.context = contexto;
        bdHelper = new BDHelper(context);
    }
//    public  final void cadastrar(Carrinho carrinho){
//        SQLiteDatabase db = bdHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(bdHelper.COLUNA_VALOR_UNITARIO, carrinho.getValorUnitario());
//        values.put(bdHelper.COLUNA_PRODUTOS, String.valueOf(carrinho.getProduto()));
//        values.put(bdHelper.COLUNA_QUANTIDADE_ITENS, carrinho.getQuantidadeItens());
//        db.insert(bdHelper.TBL_CARRINHO, null, values);
//        db.close();
//    }

//    public  final List<Carrinho> listar(){
//        List<Carrinho> produtos = new ArrayList<>();
//        SQLiteDatabase db = bdHelper.getReadableDatabase();
//        Cursor cursor = db.query(BDHelper.TBL_PRODUTO, null, null, null, null, null, null);
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()){
//            produtos.add( criarCarrinho(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        db.close();
//        return produtos;
//    }

//    private Carrinho criarCarrinho(Cursor cursor){
//        Carrinho carrinho = new Carrinho();
//        carrinho.setId(cursor.getInt(0));
//        final int columnIndex1 = 1;
//        final int columnIndex2 = 2;
//        final int columnIndex3 = 3;
//        carrinho.setId(cursor.getInt(columnIndex1));
//        carrinho.setValorUnitario(cursor.getDouble(columnIndex2));
//        carrinho.setQuantidadeItens(cursor.getString(columnIndex3));
//        return carrinho;
//    }
}
