package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.ProdutosListAdapter;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.negocio.ProdutoNegocio;

import java.util.List;

public class ListaProdutos extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaProdutos.this;
    private ProdutosListAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        listarProdutosGeral();
    }

    private void listarProdutosGeral() {
        ProdutoNegocio buscaProdutos = new ProdutoNegocio(_context);
        List<Produto> produtos = buscaProdutos.listaProdutos();
        lista = (ListView)findViewById(R.id.lista_produtos_geral);
        dataAdapter = new ProdutosListAdapter(this, produtos);
        lista.setAdapter(dataAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(ListaProdutos.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }
    public void adcionarProduto(View view) {
        Intent cadastrarProdutos = new Intent(ListaProdutos.this, CadastroProdutos.class);
        startActivity(cadastrarProdutos);
        finish();
    }
}
