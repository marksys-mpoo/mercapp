package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.negocio.ProdutoNegocio;

import java.util.List;

public class ListaProdutosDoSupermercado extends AppCompatActivity {

    private ListView lista;
    private Context context = ListaProdutosDoSupermercado.this;
    private Session session = Session.getInstanciaSessao();
    private String departamentoSelecionado;

    @Override
    protected  final  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos_do_supermercado);
        Integer idSupermercado = session.getSupermercadoSelecionado().getId();
        String nomeSM = session.getSupermercadoSelecionado().getNome().toString();
        String numeroDepartamento = session.getDepartamentoSelecionado();

        String idSupermercadoString = idSupermercado.toString();

        TextView etNomeSupermercado = (TextView) findViewById(R.id.tituloSupermercado);
        etNomeSupermercado.setText(nomeSM);
        TextView etDepartamentoSelecionado = (TextView) findViewById(R.id.tituloDepartamento);
        if (!(numeroDepartamento.equals("Todos"))) {
            departamentoSelecionado(numeroDepartamento, idSupermercadoString, etDepartamentoSelecionado);
        } else {
            this.buscarTodosProdutosDoSupermercado();
            final String todosProdutos = "Todos os Produtos";
            etDepartamentoSelecionado.setText(todosProdutos);
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

    private void departamentoSelecionado(String numeroDepartamento, String idSupermercadoString, TextView etDepartamentoSelecionado) {
        final String primeiro = "1";
        if (numeroDepartamento.equals(primeiro)) {
            final String padaria = "Padaria";
            this.setDepartamentoSelecionado(padaria);
        } else {
            final String segundo = "2";
            if (numeroDepartamento.equals(segundo)) {
                final String frios = "Frios";
                this.setDepartamentoSelecionado(frios);
            } else {
                final String terceiro = "3";
                if (numeroDepartamento .equals(terceiro)) {
                    final String acougue = "Açougue";
                    this.setDepartamentoSelecionado(acougue);
                } else {
                    final String quarto = "4";
                    if (numeroDepartamento.equals(quarto)) {
                        final String frutas = "Frutas";
                        this.setDepartamentoSelecionado(frutas);
                    } else {
                        final String quinto = "5";
                        if (numeroDepartamento .equals(quinto)) {
                            final String bebidas = "Bebidas";
                            this.setDepartamentoSelecionado(bebidas);
                        } else {
                            final String sexto = "6";
                            if (numeroDepartamento.equals(sexto)) {
                                final String mercearia = "Mercearia";
                                this.setDepartamentoSelecionado(mercearia);
                            } else {
                                final String setimo = "7";
                                if (numeroDepartamento.equals(setimo)) {
                                    final String higiene = "Higiene";
                                    this.setDepartamentoSelecionado(higiene);
                                } else {
                                    final String oitavo = "8";
                                    if (numeroDepartamento.equals(oitavo)) {
                                        final String limpeza = "Limpeza";
                                        this.setDepartamentoSelecionado(limpeza);
                                    } else {
                                        final String nono = "9";
                                        if (numeroDepartamento.equals(nono)) {
                                            final String bazar = "Bazar";
                                            this.setDepartamentoSelecionado(bazar);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        etDepartamentoSelecionado.setText(this.getDepartamentoSelecionado());
        this.buscaProdutosPorDepartamento(idSupermercadoString, numeroDepartamento);
    }

    public final void buscarTodosProdutosDoSupermercado() {
        ProdutoNegocio buscaProdutos = new ProdutoNegocio(context);
        Integer idSupermercado = session.getSupermercadoSelecionado().getId();
        List<Produto> produtos = buscaProdutos.listaProdutosDoSupermercado(idSupermercado.toString());
        ProdutoListAdapter adaptador = new ProdutoListAdapter(this, produtos);
        lista = (ListView)findViewById(R.id.lista_produtos_do_supermercado);
        lista.setAdapter(adaptador);
    }

    public final  void buscaProdutosPorDepartamento(String idSupermercado, String departamento) {
        ProdutoNegocio buscaProdutos = new ProdutoNegocio(context);
        int intdepartamento = Integer.parseInt(session.getDepartamentoSelecionado()) - 1;
        List<Produto> produtos = buscaProdutos.listar(idSupermercado, intdepartamento);
        ProdutoListAdapter adaptador = new ProdutoListAdapter(this, produtos);
        lista = (ListView)findViewById(R.id.lista_produtos_do_supermercado);
        lista.setAdapter(adaptador);
    }

    public final void  listaProdutosCarrinho(View view){
        Intent irListaCarrinho = new Intent(this, ListarItensCarrinho.class);
        startActivity(irListaCarrinho);
        finish();
    }

    @Override
    public final  void onBackPressed() {
        Intent voltarTelaSupermercado = new Intent(ListaProdutosDoSupermercado.this, TelaSupermercado.class);
        startActivity(voltarTelaSupermercado);
        finish();
    }
    public final  String getDepartamentoSelecionado() {
        return departamentoSelecionado;
    }

    public  final void setDepartamentoSelecionado(String departamentoSelecionados) {
        this.departamentoSelecionado = departamentoSelecionados;
    }

}
