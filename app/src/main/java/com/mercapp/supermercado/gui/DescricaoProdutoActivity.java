package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;

public class DescricaoProdutoActivity extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private Produto produto = session.getProdutoSelecionado();
    private Button menos;
    private Button mais;
    private TextView quantidadeProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        TextView nomeProduto = (TextView) findViewById(R.id.nomeProduto);
        nomeProduto.setText(produto.getNome());
        TextView precoProduto = (TextView) findViewById(R.id.precoProduto);
        precoProduto.setText(produto.getPreco().toString());
        TextView descricaoProduto = (TextView) findViewById(R.id.descricaoProduto);
        descricaoProduto.setText(produto.getDescricao());
        ImageView imgProduto = (ImageView) findViewById(R.id.imgProduto);
        imgProduto.setImageResource(produto.getImageProduto());

        quantidadeProduto =(TextView)findViewById(R.id.quantProduto);
        mais = (Button) findViewById(R.id.btnMais);
        menos = (Button) findViewById(R.id.btnMenos);

        mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentValueString = quantidadeProduto.getText().toString();
                int presentValueInt = Integer.parseInt(presentValueString);
                presentValueInt++;

                quantidadeProduto.setText(String.valueOf(presentValueInt));
            }
        });

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String presentValueString = quantidadeProduto.getText().toString();
                int presentValueInt = Integer.parseInt(presentValueString);
                if (presentValueInt > 0){
                    presentValueInt--;
                }

                quantidadeProduto.setText(String.valueOf(presentValueInt));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent voltarTela = new Intent(DescricaoProdutoActivity.this, ListaProdutosDoSupermercadoActivity.class);
        startActivity(voltarTela);
        finish();
    }
}
