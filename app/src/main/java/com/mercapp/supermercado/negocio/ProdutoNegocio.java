package com.mercapp.supermercado.negocio;

import android.content.Context;

import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.persistencia.ProdutoPersistencia;

import java.util.List;

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
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        produtoPersistencia.cadastrar(produto);
    }

    public List<Produto> listaProdutos(){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listaDadosProdutos();
        return produtos;
    }

    public void iniciarSessao(Produto produto){
        sessao.setProdutoSelecao(produto);
    }

}
