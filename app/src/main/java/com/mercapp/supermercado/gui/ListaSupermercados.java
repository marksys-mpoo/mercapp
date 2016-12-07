package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.BDHelper;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.supermercado.persistencia.SupermercadoPersistencia;

public class ListaSupermercados extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaSupermercados.this;
    private SimpleCursorAdapter dataAdapter;
    private SupermercadoPersistencia buscador = new SupermercadoPersistencia(_context);

    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_supermercados);

        SupermercadoNegocio consulta = new SupermercadoNegocio(_context);
        final Cursor cursor = consulta.listaSupermercados();
        String[] nomeCampos = new String[] {BDHelper.COLUNA_ID_SUPERMERCADO, BDHelper.COLUNA_NOME_SUPERMERCADO, BDHelper.COLUNA_TELEFONE_SUPERMERCADO};
        int[] idViews = new int[] {R.id.colunaProduto1, R.id.colunaProduto2, R.id.colunaProduto3};
        dataAdapter = new SimpleCursorAdapter(_context,R.layout.supermercados,cursor,nomeCampos,idViews, 0);

        lista = (ListView)findViewById(R.id.lista_supermercados);
        lista.setAdapter(dataAdapter);

        sv=(SearchView) findViewById(R.id.searchViewSupermercados);

        sv.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                dataAdapter.getFilter().filter(text.toString());
                return false;
            }
        });
        //
        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return buscador.buscarSupermercadoPorNome(constraint.toString());
            }
        });

    }

    private void listarSupermercados() {
        SupermercadoNegocio consulta = new SupermercadoNegocio(_context);
        Cursor cursor = consulta.listaSupermercados();
        String[] nomeCampos = new String[] {BDHelper.COLUNA_ID_SUPERMERCADO, BDHelper.COLUNA_NOME_SUPERMERCADO, BDHelper.COLUNA_TELEFONE_SUPERMERCADO};
        int[] idViews = new int[] {R.id.colunaProduto1, R.id.colunaProduto2, R.id.colunaProduto3};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(_context,R.layout.supermercados,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.lista_supermercados);
        lista.setAdapter(adaptador);
    }

    public void changeTelaListaSupermercadosToTelaAdmimistrador(View view) {
        Intent voltarAdm = new Intent(ListaSupermercados.this, Administrador.class);
        startActivity(voltarAdm);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(ListaSupermercados.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }

}
