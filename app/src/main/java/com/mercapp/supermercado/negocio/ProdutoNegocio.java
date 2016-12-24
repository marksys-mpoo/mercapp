package com.mercapp.supermercado.negocio;

import android.content.Context;

import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.persistencia.ProdutoPersistencia;

import java.util.List;

public class ProdutoNegocio {

    private Context _context;
    private Session session = Session.getInstanciaSessao();

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

    public List<Produto> listar(String inputText){
        ProdutoPersistencia listagem = new ProdutoPersistencia(_context);
        List<Produto> produtos = listagem.listar(inputText);
        return produtos;
    }

    public void cadastrar(Produto produtoCadastro){
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        produtoPersistencia.cadastrar(produtoCadastro);
    }

    public void editar(Produto produto){
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(_context);
        produtoPersistencia.editar(produto);
    }
    public void deletar(Produto produto){
        ProdutoPersistencia produtoPersistencia= new ProdutoPersistencia(_context);
        produtoPersistencia.deletar(produto);
    }
    public List<Produto> listar(){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listar();
        return produtos ;
    }

    public List<Produto> listaProdutosDoSupermercado(String idSuper){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listaDadosProdutosDoSupermercado(idSuper);
        return produtos;
    }

    public List<Produto> listar(String idSupermercado, int numDepartamento){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listar(idSupermercado, numDepartamento);
        return produtos;
    }
}
