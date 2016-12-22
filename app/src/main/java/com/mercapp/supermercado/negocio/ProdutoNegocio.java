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

    public List<Produto> listarProdutosPorParteDoNome(String inputText){
        ProdutoPersistencia listagem = new ProdutoPersistencia(_context);
        List<Produto> produtos = listagem.listarProdutosPorParteDoNome(inputText);
        return produtos;
    }

    public void cadastrar(String nome, int imagem, String descricao, double preco, Supermercado nomesupermercado, int numeroDepartamento,
                          int posicaoSpinnerSupermercado, int posicaoSpinnerImagemProduto){
        Produto produtoCadastro = new Produto();
        produtoCadastro.setDescricao(descricao);
        produtoCadastro.setPreco(preco);
        produtoCadastro.setNome(nome);
        produtoCadastro.setImageProduto(imagem);
        produtoCadastro.setSupermercado(nomesupermercado);
        produtoCadastro.setNumeroDepartamento(numeroDepartamento);
        produtoCadastro.setPosicaoSpinnerSupermercado(posicaoSpinnerSupermercado);
        produtoCadastro.setPosicaoSpinnerImagem(posicaoSpinnerImagemProduto);

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
    public List<Produto> listaProdutos(){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listaDados();
        return produtos ;
    }

    public List<Produto> listaProdutosDoSupermercado(String idSuper){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listaDadosProdutosDoSupermercado(idSuper);
        return produtos;
    }

    public List<Produto> listaProdutosPorDepartamentoNegocio(String idSupermercado, String numDepartamento){
        ProdutoPersistencia consulta = new ProdutoPersistencia(_context);
        List<Produto> produtos = consulta.listaProdutosDoSupermercadoPorDepartamentoPersistencia(idSupermercado, numDepartamento);
        return produtos;
    }
}
