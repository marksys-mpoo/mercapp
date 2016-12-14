package com.mercapp.supermercado.negocio;

import android.content.Context;
import android.database.Cursor;

import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;
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

    public List<Produto> listarProdutosPorParteDoNome(String inputText){
        ProdutoPersistencia listagem = new ProdutoPersistencia(_context);
        List<Produto> produtos = listagem.listarProdutosPorParteDoNome(inputText);
        return produtos;
    }

    public void cadastrar(String nome, String descricao, double preco, Supermercado nomesupermercado, String idDepartamento){
        Produto produtoCadastro = new Produto();
        produtoCadastro.setDescricao(descricao);
        produtoCadastro.setPreco(preco);
        produtoCadastro.setNome(nome);
        produtoCadastro.setSupermercado(nomesupermercado);
        produtoCadastro.setIdDepartamento(idDepartamento);
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        produtoPersistencia.cadastrar(produtoCadastro);
    }

//    public Cursor listaProdutos(){
//        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
//        Cursor cursor = consulta.listaDadosProdutos();
//        return cursor;
//    }
    public void editar(Produto produto){
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        produtoPersistencia.editar(produto);
    }
    public void deletar(Produto produto){
        ProdutoPersistencia produtoPersistencia= new ProdutoPersistencia(_context);
        produtoPersistencia.deletar(produto);
    }
    public List<Produto> listaProdutos(){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listaDados();
        return produtos ;
    }

//    public void iniciarSessao(Produto produto){
//        sessao.setProdutoSelecao(produto);
//    }



}
