package com.mercapp.recomendacao.negocio;

import android.content.Context;

import com.mercapp.recomendacao.dominio.RecomendacaoProduto;
import com.mercapp.recomendacao.persistencia.RecomendacaoProdutoPersistencia;

import java.util.List;

public class RecomendacaoProdutoNegocio {

    private Context context;

    public RecomendacaoProdutoNegocio(Context contexto) {
        this.context = contexto;
    }

    public final List<RecomendacaoProduto> listaProdutosClassificados(Integer idUsuario){
        RecomendacaoProdutoPersistencia consulta = new RecomendacaoProdutoPersistencia(context);
        return consulta.listaProdutosClassificados(idUsuario);
    }

}
