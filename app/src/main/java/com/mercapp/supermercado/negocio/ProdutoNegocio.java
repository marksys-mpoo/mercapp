package com.mercapp.supermercado.negocio;

import android.content.Context;
import android.database.Cursor;

import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.persistencia.ProdutoPersistencia;

public class ProdutoNegocio {

    private Context _context;
    private Session sessao = Session.getInstanciaSessao();

    public ProdutoNegocio(Context _context) {
        this._context = _context;
    }

    public Produto buscar(Integer id) {
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        Produto produtoSelecionado = produtoPersistencia.buscar(id);
        return produtoSelecionado;
    }

    public Produto buscar(String nome) {
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        Produto produtoSelecionado = produtoPersistencia.buscar(nome);
        return produtoSelecionado;
    }

    public void cadastrar(String nome, String descricao, Double preco, int idSupermercado, String idDepartamento){
        Produto produtoCadastro = new Produto();
        produtoCadastro.setDescricao(descricao);
        produtoCadastro.setPreco(preco);
        produtoCadastro.setNome(nome);
        produtoCadastro.setIdSupermercado(idSupermercado);
        produtoCadastro.setIdDepartamento(idDepartamento);
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        produtoPersistencia.cadastrar(produtoCadastro);
    }

    public Cursor listaProdutos(){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        Cursor cursor = consulta.listaDadosProdutos();
        return cursor;
    }

    public void iniciarSessao(Produto produto){
        sessao.setProdutoSelecao(produto);
    }

}
