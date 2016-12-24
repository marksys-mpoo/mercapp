package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;

import java.util.List;

public class ListaSupermercadosActivity extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private ListView lista;
    private Context _context = ListaSupermercadosActivity.this;
    private SupermercadoListAdapter dataAdapter;
    private SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(_context);
    private AlertDialog alerta;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_supermercados);

        SupermercadoNegocio consulta = new SupermercadoNegocio(_context);
        List<Supermercado> supermercados = consulta.listar();
        dataAdapter = new SupermercadoListAdapter(this, supermercados);

        lista = (ListView)findViewById(R.id.lista_supermercados);
        lista.setAdapter(dataAdapter);
        lista.setTextFilterEnabled(true);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Supermercado supermercado = (Supermercado) listView.getItemAtPosition(position);
                if (supermercado != null) {
                    session.setSupermercadoSelecionado(supermercado);
                    Intent editarSupermercado = new Intent(ListaSupermercadosActivity.this, CadastroSupermercadosActivity.class);
                    editarSupermercado.putExtra("CoordLat",0);
                    editarSupermercado.putExtra("CoordLong",0);
                    startActivity(editarSupermercado);
                    finish();
                }
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> listView, View view, int position, long id) {
                Supermercado supermercado = (Supermercado) listView.getItemAtPosition(position);
                if (supermercado != null) {
                    alertDeletarItem(supermercado, position);
                }
                return true;
            }
        });

        searchView =(SearchView) findViewById(R.id.searchViewSupermercados);

        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                dataAdapter = new SupermercadoListAdapter(_context, supermercadoNegocio.listar(text));
                lista.setAdapter(dataAdapter);
                return false;
            }
        });
    }

    public void changeTelaListaSupermercadosToTelaAdmimistrador(View view) {
        Intent voltarAdm = new Intent(ListaSupermercadosActivity.this, Administrador.class);
        startActivity(voltarAdm);
        finish();
    }

    public void changeScreenListaToCadastroSupermercados(View view) { // Botao (+)
        Intent addSupermercado = new Intent(ListaSupermercadosActivity.this, CadastroSupermercadosActivity.class);
        session.setSupermercadoSelecionado(null);
        addSupermercado.putExtra("CoordLat",0);
        addSupermercado.putExtra("CoordLong",0);
        startActivity(addSupermercado);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(ListaSupermercadosActivity.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }

    private void alertDeletarItem(final Supermercado supermercado, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(supermercado.getNome());
        builder.setMessage("Deseja deletar o supermercado?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                supermercadoNegocio.deletar(supermercado);
                Toast.makeText(ListaSupermercadosActivity.this, "Supermercado " + supermercado.getNome() + " deletado.", Toast.LENGTH_SHORT).show();
                dataAdapter.remove(dataAdapter.getItem(position));
                dataAdapter.notifyDataSetChanged();
                session.setSupermercadoSelecionado(null);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ListaSupermercadosActivity.this, "Ação cancelada pelo usuário.", Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();
        alerta.show();
    }

}