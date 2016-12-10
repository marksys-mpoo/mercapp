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

    public void cadastrar(Produto produto){
//        Produto produtoCadastro = new Produto();
//        produtoCadastro.setDescricao(produto.getDescricao());
//        produtoCadastro.setPreco(produto.getPreco());
//        produtoCadastro.setNome(produto.getNome());
//        produtoCadastro.setSupermercado(produto.getSupermercado());
//        produtoCadastro.setIdDepartamento(produto.getIdDepartamento());
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        produtoPersistencia.cadastrar(produto);
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
