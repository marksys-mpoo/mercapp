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
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.negocio.ProdutoNegocio;

import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaProdutosActivity.this;
    private ProdutoListAdapter dataAdapter;
    private Session session = Session.getInstanciaSessao();
    private ProdutoNegocio produtoNegocio = new ProdutoNegocio(_context);
    private AlertDialog alerta;
    private SearchView searchView;

    public ListView getLista() {
        return lista;
    }

    public void setLista(ListView lista) {
        this.lista = lista;
    }

    public Context get_context() {
        return _context;
    }

    public ProdutoListAdapter getDataAdapter() {
        return dataAdapter;
    }

    public void setDataAdapter(ProdutoListAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    public Session getSession() {
        return session;
    }

    public ProdutoNegocio getProdutoNegocio() {
        return produtoNegocio;
    }

    public AlertDialog getAlerta() {
        return alerta;
    }

    public void setAlerta(AlertDialog alerta) {
        this.alerta = alerta;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        ProdutoNegocio consulta = new ProdutoNegocio(get_context());
        List<Produto> produtos = consulta.listar();
        setDataAdapter(new ProdutoListAdapter(get_context(), produtos));

        setLista((ListView)findViewById(R.id.lista_produtos));
        getLista().setAdapter(getDataAdapter());
        getLista().setTextFilterEnabled(true);

        getLista().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Editar Produtos.
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Produto produto = (Produto) listView.getItemAtPosition(position);
                if (produto != null) {
                    getSession().setProdutoSelecionado(produto);
                    Intent editarProdudo = new Intent(get_context(), CadastroProdutosActivity.class);
                    startActivity(editarProdudo);
                    finish();
                }
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> listView, View view, int position, long id) {
                Produto produto = (Produto) listView.getItemAtPosition(position);
                if (produto != null) {
                    alertDeletarItem(produto, position);
                }
                return true;
            }
        });
        setSearchView((SearchView) findViewById(R.id.searchViewProdutos));

        getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                setDataAdapter( new ProdutoListAdapter(get_context(), getProdutoNegocio().listar(text)));
                getLista().setAdapter(getDataAdapter());
                return false;
            }
        });

    }


    @Override
    public void onBackPressed() {
        // voltar para a tela adminstrador.
        Intent voltarAdm = new Intent(get_context(), Administrador.class);
        startActivity(voltarAdm);
        finish();
    }
    public void adcionarProduto(View view) {
        // Adiconar novos Produtos.
        getSession().setProdutoSelecionado(null);
        Intent cadastrarProdutos = new Intent(get_context(), CadastroProdutosActivity.class);
        startActivity(cadastrarProdutos);
        finish();
    }

    private void alertDeletarItem(final Produto produto, final int position) {
        // deletar Produtos.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(produto.getNome());
        builder.setMessage("Deseja deletar o produto?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                getProdutoNegocio().deletar(produto);
                Toast.makeText(getApplication(), "Produto " + produto.getNome() + " deletado.", Toast.LENGTH_SHORT).show();
                getDataAdapter().remove(getDataAdapter().getItem(position));
                getDataAdapter().notifyDataSetChanged();
                getSession().setProdutoSelecionado(null);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplication(), "Ação cancelada pelo usuário.", Toast.LENGTH_SHORT).show();
            }
        });
        setAlerta(builder.create());
        getAlerta().show();
    }

}
