package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.BDHelper;
import com.mercapp.infra.ProdutoListAdapter;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.negocio.ProdutoNegocio;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;

import java.util.List;

public class ListaProdutosDoSupermercado extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaProdutosDoSupermercado.this;
    private Session session = Session.getInstanciaSessao();
    private String departamentoSelecionado;
    private TextView etDepartamentoSelecionado, etNomeSupermercado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos_do_supermercado);
        Integer idSupermercado = session.getSupermercadoSelecionado().getId();
        String nomeSM = session.getSupermercadoSelecionado().getNome().toString();
        String numeroDepartamento = session.getDepartamentoSelecionado();

        String idSupermercado_string = idSupermercado.toString();

        etNomeSupermercado = (TextView) findViewById(R.id.tituloSupermercado);
        etNomeSupermercado.setText(nomeSM);
        etDepartamentoSelecionado = (TextView) findViewById(R.id.tituloDepartamento);
        if (numeroDepartamento != "Todos") {
            if (numeroDepartamento == "1") {
                this.setDepartamentoSelecionado("Padaria");
            } else if (numeroDepartamento == "2") {
                this.setDepartamentoSelecionado("Frios");
            } else if (numeroDepartamento == "3") {
                this.setDepartamentoSelecionado("Açougue");
            } else if (numeroDepartamento == "4") {
                this.setDepartamentoSelecionado("Frutas");
            } else if (numeroDepartamento == "5") {
                this.setDepartamentoSelecionado("Bebidas");
            } else if (numeroDepartamento == "6") {
                this.setDepartamentoSelecionado("Mercearia");
            } else if (numeroDepartamento == "7") {
                this.setDepartamentoSelecionado("Higiene");
            } else if (numeroDepartamento == "8") {
                this.setDepartamentoSelecionado("Limpeza");
            } else if (numeroDepartamento == "9") {
                this.setDepartamentoSelecionado("Bazar");
            }
            etDepartamentoSelecionado.setText(this.getDepartamentoSelecionado());
            this.buscaProdutosPorDepartamento(idSupermercado_string, numeroDepartamento);
        } else {
            this.buscarTodosProdutosDoSupermercado();
            etDepartamentoSelecionado.setText("Produtos");
        }
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Produto produto = (Produto) listView.getItemAtPosition(position);
                session.setProdutoSelecionado(produto);
                Intent editarProdudo = new Intent(ListaProdutosDoSupermercado.this, DescricaoProduto.class);
                startActivity(editarProdudo);
                finish();
                }
            });
    }

    public void buscarTodosProdutosDoSupermercado() {
        ProdutoNegocio buscaProdutos = new ProdutoNegocio(_context);
        Integer idSupermercado = session.getSupermercadoSelecionado().getId();
        List<Produto> produtos = buscaProdutos.listaProdutosDoSupermercado(idSupermercado.toString());
        ProdutoListAdapter adaptador = new ProdutoListAdapter(this, produtos);
        lista = (ListView)findViewById(R.id.lista_produtos_do_supermercado);
        lista.setAdapter(adaptador);
    }

    public void buscaProdutosPorDepartamento(String idSupermercado, String departamento) {
        ProdutoNegocio buscaProdutos = new ProdutoNegocio(_context);
        int intdepartamento = Integer.parseInt(session.getDepartamentoSelecionado()) - 1;
        List<Produto> produtos = buscaProdutos.listaProdutosPorDepartamentoNegocio(idSupermercado, intdepartamento);
        ProdutoListAdapter adaptador = new ProdutoListAdapter(this, produtos);
        lista = (ListView)findViewById(R.id.lista_produtos_do_supermercado);
        lista.setAdapter(adaptador);
    }

    @Override
    public void onBackPressed() {
        Intent voltarTelaSupermercado = new Intent(ListaProdutosDoSupermercado.this, TelaSupermercado.class);
        startActivity(voltarTelaSupermercado);
        finish();
    }
    public String getDepartamentoSelecionado() {
        return departamentoSelecionado;
    }

    public void setDepartamentoSelecionado(String departamentoSelecionado) {
        this.departamentoSelecionado = departamentoSelecionado;
    }

}
