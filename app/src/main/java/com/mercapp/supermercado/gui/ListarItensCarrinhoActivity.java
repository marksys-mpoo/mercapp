package com.mercapp.supermercado.gui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Carrinho;
import com.mercapp.supermercado.negocio.CarrinhoNegocio;

import java.util.List;

public class ListarItensCarrinhoActivity extends AppCompatActivity {
    private ListView lista;
    private Context context = ListarItensCarrinhoActivity.this;
    private CarrinhoListAdapter dataAdapter;
    private Session session = Session.getInstanciaSessao();
    private CarrinhoNegocio carrinhoNegocio = new CarrinhoNegocio(context);
    private AlertDialog alerta;
    private SearchView searchView;

    public final ListView getLista() {
        return lista;
    }

    public  final void setLista(ListView listas) {
        this.lista = listas;
    }

    public  final Context getContext() {
        return context;
    }

    public final CarrinhoListAdapter getDataAdapter() {
        return dataAdapter;
    }

    public  final void setDataAdapter(CarrinhoListAdapter dataAdapters) {
        this.dataAdapter = dataAdapters;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_itens_carrinho);

        CarrinhoNegocio consulta = new CarrinhoNegocio(getContext());
        List<Carrinho> carrinhos = consulta.listar();
        setDataAdapter(new CarrinhoListAdapter(getContext(), carrinhos));

        setLista((ListView)findViewById(R.id.listaItensCarrinho));
        getLista().setAdapter(getDataAdapter());
        getLista().setTextFilterEnabled(true);

    }



}
