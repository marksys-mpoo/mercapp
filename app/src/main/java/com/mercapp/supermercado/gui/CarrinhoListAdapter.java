package com.mercapp.supermercado.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.supermercado.dominio.Carrinho;

import java.util.List;

/**
 * Created by WELLINGTON on 28/01/2017.
 */

public class CarrinhoListAdapter extends ArrayAdapter<Carrinho> {

    private Context context;
    private List<Carrinho> carrinhos;

    public CarrinhoListAdapter(Context contexto, List<Carrinho> carrinho) {
        super(contexto, 0, carrinho);
        this.context = contexto;
        this.carrinhos = carrinho;
    }

    public  final View getView(int position, View converterTela, ViewGroup parent) {
        Carrinho carrinhoPosicao = this.carrinhos.get(position);

        converterTela = LayoutInflater.from(this.context).inflate(R.layout.itens_carrinhos_geral, null);

        TextView nome = (TextView) converterTela.findViewById(R.id.colunaProduto1);
        nome.setText(carrinhoPosicao.getProduto().getNome().toString());

        TextView preco = (TextView) converterTela.findViewById(R.id.colunaProduto2);
        preco.setText(carrinhoPosicao.getProduto().getPreco().toString());
        
        TextView quantidadeItens = (TextView) converterTela.findViewById(R.id.colunaProduto4);
        quantidadeItens.setText(carrinhoPosicao.getQuantidadeItens());

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
