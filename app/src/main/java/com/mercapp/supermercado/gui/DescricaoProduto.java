package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;

public class DescricaoProduto extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private String nome;
    private String descricao;
    private String preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        TextView textView1 = (TextView) findViewById(R.id.textView33);
        textView1.setText(session.getProdutoSelecionado().getNome());

    }

    @Override
    public void onBackPressed() {
        Intent voltarTela = new Intent(DescricaoProduto.this, ListaProdutosDoSupermercado.class);
        startActivity(voltarTela);
        finish();
    }
}
