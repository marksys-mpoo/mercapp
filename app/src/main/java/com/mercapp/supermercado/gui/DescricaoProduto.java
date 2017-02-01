package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Carrinho;
import com.mercapp.supermercado.dominio.Produto;

import java.util.Map;

import static java.lang.String.valueOf;

public class DescricaoProduto extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private Produto produto = session.getProdutoSelecionado();
    private Button menos;
    private Button mais;
    private TextView quantidadeProduto;
    private Map map = new ArrayMap();

    public final Session getSession() {
        return session;
    }

    public final TextView getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public final void setQuantidadeProduto(TextView quantidadeProdutox) {
        this.quantidadeProduto = quantidadeProdutox;
    }

    public final Button getMais() {
        return mais;
    }

    public final void setMais(Button maisx) {
        this.mais = maisx;
    }

    public final Button getMenos() {
        return menos;
    }

    public final void setMenos(Button menosx) {
        this.menos = menosx;
    }

    public final Produto getProduto() {
        return produto;
    }

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        TextView nomeProduto = (TextView) findViewById(R.id.nomeProduto);
        nomeProduto.setText(getProduto().getNome());
        TextView precoProduto = (TextView) findViewById(R.id.precoProduto);
        precoProduto.setText(getProduto().getPreco().toString());
        TextView descricaoProduto = (TextView) findViewById(R.id.descricaoProduto);
        descricaoProduto.setText(getProduto().getDescricao());
        ImageView imgProduto = (ImageView) findViewById(R.id.imgProduto);
        imgProduto.setImageResource(getProduto().getImageProduto());

        setQuantidadeProduto((TextView)findViewById(R.id.quantProduto));
        setMais((Button) findViewById(R.id.btnMais));
        setMenos((Button) findViewById(R.id.btnMenos));

        getMais().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentValueString = getQuantidadeProduto().getText().toString();
                int presentValueInt = Integer.parseInt(presentValueString);
                presentValueInt++;

                getQuantidadeProduto().setText(valueOf(presentValueInt));
            }
        });

        getMenos().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String presentValueString = getQuantidadeProduto().getText().toString();
                int presentValueInt = Integer.parseInt(presentValueString);
                if (presentValueInt > 0){
                    presentValueInt--;
                }

                getQuantidadeProduto().setText(valueOf(presentValueInt));
            }
        });

    }

    public final void cadastroCarrinho(View view){
        final String zero = String.valueOf(0);
        if (!(getQuantidadeProduto().getText().toString().equals(zero))){
            String quantidade = getQuantidadeProduto().getText().toString();
            map.put(Integer.parseInt(quantidade), produto);
            session.getCarrinho().addProdutos(map);
            Toast.makeText(this, "O produto foi adicionado ao carrinho.", Toast.LENGTH_SHORT).show();
            this.onBackPressed();
        }else{
            Toast.makeText(this, "Clique no '+' para adicionar ao carrinho.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public final void onBackPressed() {
        Intent voltarTela = new Intent(getApplication(), ListaProdutosDoSupermercado.class);
        startActivity(voltarTela);
        finish();
    }
}
