package com.mercapp.infra;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.supermercado.dominio.Produto;

import java.util.List;

public class ProdutosListAdapter extends ArrayAdapter{

    private Context _context;
    private List<Produto> produtos;

    public ProdutosListAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produtoPosicao = this.produtos.get(position);

        convertView = LayoutInflater.from(this._context).inflate(R.layout.produtos_geral, null);

        TextView nome = (TextView) convertView.findViewById(R.id.colunaProduto1);
        nome.setText(produtoPosicao.getNome());

        TextView descricao = (TextView) convertView.findViewById(R.id.colunaProduto2);
        descricao.setText(produtoPosicao.getDescricao());

        TextView preco = (TextView) convertView.findViewById(R.id.colunaProduto3);
        preco.setText(produtoPosicao.getPreco().toString());

        TextView supermercado = (TextView) convertView.findViewById(R.id.colunaProduto4);
        supermercado.setText(produtoPosicao.getSupermercado().getNome());

        return convertView;
    }
}
