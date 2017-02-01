package com.mercapp.supermercado.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.supermercado.dominio.Carrinho;
import com.mercapp.supermercado.dominio.Produto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CarrinhoListAdapter extends ArrayAdapter<Map> {

    private Context context;
    private List<Map> carrinhos;

    public CarrinhoListAdapter(Context contexto, List<Map> carrinho) {
        super(contexto, 0, carrinho);
        this.context = contexto;
        this.carrinhos = carrinho;
    }

    public  final View getView(int position, View converterTela, ViewGroup parent) {
        Map<Integer, Produto> carrinhoPosicao = this.carrinhos.get(position);

        ArrayList<Produto> produtos = new ArrayList<> (carrinhoPosicao.values());
        ArrayList<Integer> quantidade = new ArrayList<>(carrinhoPosicao.keySet());

        converterTela = LayoutInflater.from(this.context).inflate(R.layout.itens_carrinhos_geral, null);

        TextView nome = (TextView) converterTela.findViewById(R.id.colunaProduto1);
        nome.setText(produtos.get(0).getNome().toString());

        TextView preco = (TextView) converterTela.findViewById(R.id.colunaProduto2);
        preco.setText(produtos.get(0).getPreco().toString());
        
        TextView quantidadeItens = (TextView) converterTela.findViewById(R.id.colunaProduto4);
        quantidadeItens.setText(quantidade.get(0).toString());

//        TextView preco = (TextView) converterTela.findViewById(R.id.colunaProduto3);
//        preco.setText(carrinhoPosicao.getPreco().toString().trim());
//
//        TextView supermercado = (TextView) converterTela.findViewById(R.id.colunaProduto4);
//        supermercado.setText(carrinhoPosicao.getSupermercado().getNome());
//
//        ImageView imagem = (ImageView) converterTela.findViewById(R.id.iconProdutoGeral);
//        int idImagem = carrinhoPosicao.getImageProduto();
//        Drawable drawable = ContextCompat.getDrawable(context, idImagem);
//        imagem.setImageDrawable(drawable);

        return converterTela;
    }
}
