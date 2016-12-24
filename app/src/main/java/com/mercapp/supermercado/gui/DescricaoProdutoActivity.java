package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;

public class DescricaoProdutoActivity extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private Produto produto = session.getProdutoSelecionado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        TextView nomeProduto = (TextView) findViewById(R.id.nomeProduto);
        nomeProduto.setText(produto.getNome());
        TextView precoProduto = (TextView) findViewById((R.id.precoProduto));
        precoProduto.setText(produto.getPreco().toString());
        TextView descricaoProduto = (TextView) findViewById((R.id.descricaoProduto));
        descricaoProduto.setText(produto.getDescricao());

    }

    @Override
    public void onBackPressed() {
        Intent voltarTela = new Intent(DescricaoProdutoActivity.this, ListaProdutosDoSupermercadoActivity.class);
        startActivity(voltarTela);
        finish();
    }
}