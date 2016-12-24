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

        private Context _context;
        private List<Produto> produtos;

        public ProdutoListAdapter(Context context, List<Produto> produtos) {
            super(context, 0, produtos);
            this._context = context;
            this.produtos = produtos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Produto produtoPosicao = this.produtos.get(position);

            convertView = LayoutInflater.from(this._context).inflate(R.layout.produtos_geral, null);

            TextView nome = (TextView) convertView.findViewById(R.id.colunaProduto1);
            nome.setText(produtoPosicao.getNome().toString());

            TextView descricao = (TextView) convertView.findViewById(R.id.colunaProduto2);
            descricao.setText(produtoPosicao.getDescricao().toString());

            TextView preco = (TextView) convertView.findViewById(R.id.colunaProduto3);
            preco.setText(produtoPosicao.getPreco().toString().trim());

            TextView supermercado = (TextView) convertView.findViewById(R.id.colunaProduto4);
            supermercado.setText(produtoPosicao.getSupermercado().getNome());

            ImageView imagem = (ImageView) convertView.findViewById(R.id.iconProdutoGeral);
            int idImagem = produtoPosicao.getImageProduto();
            Drawable drawable = ContextCompat.getDrawable(_context, idImagem);
            imagem.setImageDrawable(drawable);

            return convertView;
        }
}
