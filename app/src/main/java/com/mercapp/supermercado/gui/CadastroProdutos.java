package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;

public class CadastroProdutos extends AppCompatActivity {

    private Context _context = CadastroProdutos.this;
    private SupermercadoNegocio supermercadoNegocio;
    private Produto produtoCadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);
    }

    public void cadastroProdutosDireto(View view) {
        Produto produto1 = CadastrarProduto(1,"Produto 1","11.11","1", "1");
        efetuarCadastroProduto(produto1);
        Produto produto2 = CadastrarProduto(2,"Produto 2","22.22","2","1");
        efetuarCadastroProduto(produto2);
        Produto produto3 = CadastrarProduto(3,"Produto 3","33.33","1","2");
        efetuarCadastroProduto(produto3);
        Produto produto4 = CadastrarProduto(4,"Produto 4","44.44","2","2");
        efetuarCadastroProduto(produto4);
    }

    private Produto CadastrarProduto(Integer id, String descricao, String preco, String idSupermercado, String idDepartamento) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setIdSupermercado(idSupermercado);
        produto.setIdDepartamento(idDepartamento);
        return produto;
    }

    public void efetuarCadastroProduto(Produto produto) {
        Integer id = produto.getId();
        String descricao = produto.getDescricao();
        String preco = produto.getPreco();
        String idSupermercado = produto.getIdSupermercado();
        String idDepartamento = produto.getIdDepartamento();
        supermercadoNegocio = new SupermercadoNegocio(_context);
        produtoCadastrado = supermercadoNegocio.buscaProduto(descricao);
        if (produtoCadastrado == null) {
            supermercadoNegocio.cadastroProduto(descricao, preco, idSupermercado, idDepartamento);
            Toast.makeText(this, "Produto " + id + " cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Produto já exitente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeTelaCadatroProdutosToTelaAdministrador(View view) {
        Intent voltarMenu = new Intent(CadastroProdutos.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(CadastroProdutos.this, Administrador.class);
        startActivity(voltarMenu);
        finish();
    }
}
