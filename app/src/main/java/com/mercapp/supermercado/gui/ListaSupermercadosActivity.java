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
    private Context context = ListaSupermercadosActivity.this;
    private SupermercadoListAdapter dataAdapter;
    private SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(context);
    private SearchView searchView;

    public Session getSession() {
        return session;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    public SupermercadoNegocio getSupermercadoNegocio() {
        return supermercadoNegocio;
    }


    public SupermercadoListAdapter getDataAdapter() {
        return dataAdapter;
    }

    public void setDataAdapter(SupermercadoListAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    public Context getContext() {
        return context;
    }

    public ListView getLista() {
        return lista;
    }

    public void setLista(ListView lista) {
        this.lista = lista;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_supermercados);

        SupermercadoNegocio consulta = new SupermercadoNegocio(getContext());
        List<Supermercado> supermercados = consulta.listar();
        setDataAdapter(new SupermercadoListAdapter(this, supermercados));

        setLista((ListView)findViewById(R.id.lista_supermercados));
        getLista().setAdapter(getDataAdapter());
        getLista().setTextFilterEnabled(true);

        getLista().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Supermercado supermercado = (Supermercado) listView.getItemAtPosition(position);
                if (supermercado != null) {
                    getSession().setSupermercadoSelecionado(supermercado);
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

        setSearchView((SearchView) findViewById(R.id.searchViewSupermercados));

        getSearchView().setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                setDataAdapter(new SupermercadoListAdapter(getContext(), getSupermercadoNegocio().listar(text)));
                getLista().setAdapter(getDataAdapter());
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
        getSession().setSupermercadoSelecionado(null);
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
                getSupermercadoNegocio().deletar(supermercado);
                Toast.makeText(ListaSupermercadosActivity.this, "Supermercado " + supermercado.getNome() + " deletado.", Toast.LENGTH_SHORT).show();
                getDataAdapter().remove(getDataAdapter().getItem(position));
                getDataAdapter().notifyDataSetChanged();
                getSession().setSupermercadoSelecionado(null);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getContext(), "Ação cancelada pelo usuário.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alerta = builder.create();
        alerta.show();
    }

}
