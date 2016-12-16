package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.ProdutoListAdapter;
import com.mercapp.infra.BDHelper;
import com.mercapp.infra.Session;
import com.mercapp.infra.SupermercadoListAdapter;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.ProdutoNegocio;

import java.util.List;

public class ListaProdutos extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaProdutos.this;
    private ProdutoListAdapter dataAdapter;
    private Session sessao = Session.getInstanciaSessao();
    ProdutoNegocio produtoNegocio = new ProdutoNegocio(_context);
    private AlertDialog alerta;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
//        listarProdutosGeral();
        ProdutoNegocio consulta = new ProdutoNegocio(_context);
        List<Produto> produtos = consulta.listaProdutos();
        dataAdapter = new ProdutoListAdapter(_context, produtos);

        lista = (ListView)findViewById(R.id.lista_produtos);
        lista.setAdapter(dataAdapter);
        lista.setTextFilterEnabled(true);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Editar Produtos.
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Produto produto = (Produto) listView.getItemAtPosition(position);
                if (produto != null) {
                    sessao.setProdutoSelecionado(produto);
                    Intent editarProdudo = new Intent(ListaProdutos.this, CadastroProdutos.class);
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
        searchView =(SearchView) findViewById(R.id.searchViewProdutos);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                dataAdapter = new ProdutoListAdapter(_context, produtoNegocio.listarProdutosPorParteDoNome(text));
                lista.setAdapter(dataAdapter);
                return false;
            }
        });

    }

//    private void listarProdutosGeral() {
//        // Adiconar novos Produtos.
//        ProdutoNegocio buscaProdutos = new ProdutoNegocio(_context);
//        Cursor cursor = buscaProdutos.listaProdutos();
//        String[] colunasBD = new String[] {BDHelper.COLUNA_NOME_PRODUTO,BDHelper.COLUNA_DESCRICAO_PRODUTO, BDHelper.COLUNA_PRECO_PRODUTO, BDHelper.COLUNA_ID_SUPERMERCADO_PRODUTO};
//        int[] idListView = new int[] {R.id.colunaProduto1, R.id.colunaProduto2, R.id.colunaProduto3, R.id.colunaProduto4};
//        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(_context,R.layout.produtos_geral,cursor,colunasBD,idListView, 0);
//        lista = (ListView)findViewById(R.id.lista_produtos_geral);
//        lista.setAdapter(adaptador);
//
//    }

    @Override
    public void onBackPressed() {
        // voltar para a tela adminstrador.
        Intent voltarMenu = new Intent(ListaProdutos.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }
    public void adcionarProduto(View view) {
        // Adiconar novos Produtos.
        Intent cadastrarProdutos = new Intent(ListaProdutos.this, CadastroProdutos.class);
        startActivity(cadastrarProdutos);
        finish();
    }

    private void alertDeletarItem(final Produto produto, final int position) {
        // deletar Produtos.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(produto.getNome());
        builder.setMessage("Deseja deletar o supermercado?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                produtoNegocio.deletar(produto);
                Toast.makeText(getApplication(), "Supermercado " + produto.getNome() + " deletado.", Toast.LENGTH_SHORT).show();
                dataAdapter.remove(dataAdapter.getItem(position));
                dataAdapter.notifyDataSetChanged();
                sessao.setProdutoSelecionado(null);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplication(), "Ação cancelada pelo usuário.", Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();
        alerta.show();
    }

}
