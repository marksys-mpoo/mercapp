package com.mercapp.supermercado.gui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.supermercado.dominio.Produto;


import java.util.List;

public class ProdutoListAdapter extends ArrayAdapter<Produto> {

        private Context context;
        private List<Produto> produtos;

        public ProdutoListAdapter(Context contexto, List<Produto> produto) {
            super(contexto, 0, produto);
            this.context = contexto;
            this.produtos = produto;
        }

        @Override
        public  final View getView(int position, View converterTela, ViewGroup parent) {
            Produto produtoPosicao = this.produtos.get(position);

            converterTela = LayoutInflater.from(this.context).inflate(R.layout.produtos_geral, null);

            TextView nome = (TextView) converterTela.findViewById(R.id.colunaProduto1);
            nome.setText(produtoPosicao.getNome().toString());

            TextView descricao = (TextView) converterTela.findViewById(R.id.colunaProduto2);
            descricao.setText(produtoPosicao.getDescricao().toString());

            TextView preco = (TextView) converterTela.findViewById(R.id.colunaProduto3);
            preco.setText(produtoPosicao.getPreco().toString().trim());

            TextView supermercado = (TextView) converterTela.findViewById(R.id.colunaProduto4);
            supermercado.setText(produtoPosicao.getSupermercado().getNome());

            ImageView imagem = (ImageView) converterTela.findViewById(R.id.iconProdutoGeral);
            int idImagem = produtoPosicao.getImageProduto();
            Drawable drawable = ContextCompat.getDrawable(context, idImagem);
            imagem.setImageDrawable(drawable);

            return converterTela;
        }
}
