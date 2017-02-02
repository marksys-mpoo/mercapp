package com.mercapp.recomendacao.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mercapp.infra.persistencia.BDHelper;
import com.mercapp.recomendacao.dominio.RecomendacaoProduto;

import java.util.ArrayList;
import java.util.List;

public class RecomendacaoProdutoPersistencia {
    private Context context;
    private BDHelper bdHelper;
    private static final String SELECT = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String LIKE = " LIKE ? ";

    public RecomendacaoProdutoPersistencia(Context contexto) {
        this.context = contexto;
        bdHelper = new BDHelper(context);
    }

    public  final List<RecomendacaoProduto> listaProdutosClassificados(Integer idUsuario, Integer idSupermercado){
        List<RecomendacaoProduto> recomendacaoProdutos = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        final String selecionar = "SELECT * FROM ";
        final String likeAnd =" LIKE ? AND ";
        Cursor cursor = db.rawQuery(selecionar + bdHelper.TBL_RECOMENDACAO_PRODUTO +
                " WHERE "+ bdHelper.COLUNA_RECOMENDACAO_PRODUTO_ID_USUARIO + likeAnd + bdHelper.COLUNA_RECOMENDACAO_PRODUTO_ID_SUPERMERCADO + LIKE, new String[]{""+idUsuario, ""+idSupermercado});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            recomendacaoProdutos.add(criarRecomendacaoProduto(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return recomendacaoProdutos;
    }

    private RecomendacaoProduto criarRecomendacaoProduto(Cursor cursor){
        final int columnIndex1 = 1;
        final int columnIndex2 = 2;
        final int columnIndex3 = 3;
        final int columnIndex4 = 4;
        RecomendacaoProduto recomendacaoProduto = new RecomendacaoProduto();
        recomendacaoProduto.setId(cursor.getInt(0));
        recomendacaoProduto.setIdProduto(cursor.getInt(columnIndex1));
        recomendacaoProduto.setIdUsuario(cursor.getInt(columnIndex2));
        recomendacaoProduto.setIdSupermercado(cursor.getInt(columnIndex3));
        recomendacaoProduto.setNota(cursor.getDouble(columnIndex4));
        return recomendacaoProduto;
    }

}
