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

public class ListaSupermercados extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaSupermercados.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_supermercados);

        SupermercadoNegocio consulta = new SupermercadoNegocio(_context);
        Cursor cursor = consulta.listaSupermercados();

        String[] nomeCampos = new String[] {BDHelper.COLUNA_ID_SUPERMERCADO, BDHelper.COLUNA_NOME_SUPERMERCADO, BDHelper.COLUNA_TELEFONE_SUPERMERCADO};
        int[] idViews = new int[] {R.id.coluna1, R.id.coluna2, R.id.coluna3};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(_context,R.layout.supermercados,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.lista_supermercados);
        lista.setAdapter(adaptador);

    }

    public void changeTelaListaSupermercadoToTelaAdmimistrador(View view) {
        Intent voltarAdm = new Intent(ListaSupermercados.this, Administrador.class);
        startActivity(voltarAdm);
        finish();
    }

}
