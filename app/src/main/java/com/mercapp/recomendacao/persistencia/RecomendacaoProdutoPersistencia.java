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

    public final List<RecomendacaoProduto> listaProdutosClassificados(Integer idUsuario){
        List<RecomendacaoProduto> recomendacaoProdutos = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT + bdHelper.TBL_RECOMENDACAO_PRODUTO +
                WHERE + bdHelper.COLUNA_RECOMENDACAO_PRODUTO_ID_USUARIO + LIKE, new String[]{""+idUsuario});
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
        RecomendacaoProduto recomendacaoProduto = new RecomendacaoProduto();
        recomendacaoProduto.setId(cursor.getInt(0));
        recomendacaoProduto.setIdProduto(cursor.getInt(columnIndex1));
        recomendacaoProduto.setIdUsuario(cursor.getInt(columnIndex2));
        recomendacaoProduto.setNota(cursor.getDouble(columnIndex3));
        return recomendacaoProduto;
    }

}
