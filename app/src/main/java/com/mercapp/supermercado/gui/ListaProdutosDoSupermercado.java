package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.BDHelper;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;

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
        String idDepartamento = session.getDepartamentoSelecionado();

        String idSupermercado_string = idSupermercado.toString();

        etNomeSupermercado = (TextView) findViewById(R.id.tituloSupermercado);
        etNomeSupermercado.setText(nomeSM);
        etDepartamentoSelecionado = (TextView) findViewById(R.id.tituloDepartamento);
        if (idDepartamento != "Todos") {
            if (idDepartamento == "1") {
                this.setDepartamentoSelecionado("Padaria");
            } else if (idDepartamento == "2") {
                this.setDepartamentoSelecionado("Frios");
            } else if (idDepartamento == "3") {
                this.setDepartamentoSelecionado("Açougue");
            } else if (idDepartamento == "4") {
                this.setDepartamentoSelecionado("Frutas");
            } else if (idDepartamento == "5") {
                this.setDepartamentoSelecionado("Bebidas");
            } else if (idDepartamento == "6") {
                this.setDepartamentoSelecionado("Mercearia");
            } else if (idDepartamento == "7") {
                this.setDepartamentoSelecionado("Higiene");
            } else if (idDepartamento == "8") {
                this.setDepartamentoSelecionado("Limpeza");
            } else if (idDepartamento == "9") {
                this.setDepartamentoSelecionado("Bazar");
            }
            etDepartamentoSelecionado.setText(this.getDepartamentoSelecionado());
            this.buscaProdutosPorDepartamento(idSupermercado_string, idDepartamento);
        } else {
            this.buscarTodosProdutosDoSupermercado();
            etDepartamentoSelecionado.setText("Produtos");
        }
    }

    public void buscarTodosProdutosDoSupermercado() {
        SupermercadoNegocio buscaProdutos = new SupermercadoNegocio(_context);
        Integer idSupermercado = session.getSupermercadoSelecionado().getId();
        String idSupermercado_string = idSupermercado.toString();
        Cursor cursor = buscaProdutos.listaProdutosDoSupermercado(idSupermercado_string);
//        String[] nomeCampos = new String[] {BDHelper.COLUNA_ID_PRODUTO, BDHelper.COLUNA_DESCRICAO, BDHelper.COLUNA_PRECO_PRODUTO, BDHelper.COLUNA_ID_SUPERMERCADO_PRODUTO};
        String[] nomeCampos = new String[] {BDHelper.COLUNA_ID_PRODUTO, BDHelper.COLUNA_DESCRICAO_PRODUTO, BDHelper.COLUNA_PRECO_PRODUTO};
        int[] idViews = new int[] { R.id.colunaProduto2, R.id.colunaProduto3, R.id.colunaProduto4};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(_context,R.layout.produtos,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.lista_produtos_do_supermercado);
        lista.setAdapter(adaptador);
    }

    public void buscaProdutosPorDepartamento(String idSupermercado, String idDepartamento) {
        SupermercadoNegocio buscaProdutos = new SupermercadoNegocio(_context);
        Cursor cursor = buscaProdutos.listaProdutosPorDepartamentoNegocio(idSupermercado, idDepartamento);
        String[] nomeCampos = new String[] {BDHelper.COLUNA_IMAGEM_PRODUTO, BDHelper.COLUNA_DESCRICAO_PRODUTO, BDHelper.COLUNA_PRECO_PRODUTO};
        int[] idViews = new int[] {R.id.iconProduto, R.id.colunaProduto2, R.id.colunaProduto3};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(_context,R.layout.produtos,cursor,nomeCampos,idViews, 0);
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
