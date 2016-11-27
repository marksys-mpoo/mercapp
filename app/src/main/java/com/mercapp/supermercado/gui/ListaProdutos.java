package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.BDHelper;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;

public class ListaProdutos extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaProdutos.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        MostrarListaProdutosGeral();
    }

    private void MostrarListaProdutosGeral() {
        SupermercadoNegocio buscaProdutos = new SupermercadoNegocio(_context);
        Cursor cursor = buscaProdutos.listaProdutos();
        String[] colunasBD = new String[] {BDHelper.COLUNA_ID_PRODUTO, BDHelper.COLUNA_DESCRICAO, BDHelper.COLUNA_PRECO, BDHelper.COLUNA_ID_SUPERMERCADO_PRODUTO};
        int[] idListView = new int[] {R.id.colunaProduto1, R.id.colunaProduto2, R.id.colunaProduto3, R.id.colunaProduto4};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(_context,R.layout.produtos,cursor,colunasBD,idListView, 0);
        lista = (ListView)findViewById(R.id.lista_produtos);
        lista.setAdapter(adaptador);
    }

    public void changeTelaListaProdutosToTelaAdmimistrador(View view) {
        Intent voltarAdm = new Intent(ListaProdutos.this, Administrador.class);
        startActivity(voltarAdm);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(ListaProdutos.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }
}
