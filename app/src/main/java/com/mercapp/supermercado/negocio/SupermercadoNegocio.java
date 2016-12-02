package com.mercapp.supermercado.negocio;

import android.content.Context;
import android.database.Cursor;

import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.persistencia.SupermercadoPersistencia;


public class SupermercadoNegocio {

    private Context _context;
    private Session sessao = Session.getInstanciaSessao();

    public Supermercado buscaSupermercado(String nome) {
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        Supermercado supermercadoSelecionado = supermercadoPersistencia.buscarSupermercado(nome);
        return supermercadoSelecionado;
    }

    public Produto buscaProduto(Integer id) {
        SupermercadoPersistencia produtoPersistencia = new SupermercadoPersistencia(_context);
        Produto produtoSelecionado = produtoPersistencia.buscarProduto(id);
        return produtoSelecionado;
    }

    public SupermercadoNegocio(Context context)
    {
        _context = context;
    }

    public void cadastroSupermercado(String nome, String telefone){
        Supermercado supermercadoCadastro = new Supermercado();
        supermercadoCadastro.setNome(nome);
        supermercadoCadastro.setTelefone(telefone);
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        supermercadoPersistencia.cadastrarSupermercado(supermercadoCadastro);
    }

    public void cadastroProduto(String descricao, String preco, String idSupermercado, String idDepartamento){
        Produto produtoCadastro = new Produto();
        produtoCadastro.setDescricao(descricao);
        produtoCadastro.setPreco(preco);
        produtoCadastro.setIdSupermercado(idSupermercado);
        produtoCadastro.setIdDepartamento(idDepartamento);
        SupermercadoPersistencia produtoPersistencia = new SupermercadoPersistencia(_context);
        produtoPersistencia.cadastrarProduto(produtoCadastro);
    }

    public Cursor listaSupermercados(){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        Cursor cursor = consulta.listaDados();
        return cursor;
    }

    public Cursor listaProdutos(){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        Cursor cursor = consulta.listaDadosProdutos();
        return cursor;
    }

    public Cursor listaProdutosDoSupermercado(String idSuper){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        Cursor cursor = consulta.listaDadosProdutosDoSupermercado(idSuper);
        return cursor;
    }

    public Cursor listaProdutosPorDepartamentoNegocio(String idSupermercado, String idDepartamento){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        Cursor cursor = consulta.listaProdutosDoSupermercadoPorDepartamentoPersistencia(idSupermercado, idDepartamento);
        return cursor;
    }

    public void iniciarSessao(Supermercado supermercado){
        sessao.setSupermercadoSelecionado(supermercado);
    }

    public void iniciarSessaoProduto(String departamento){
        sessao.setDepartamentoSelecionado(departamento);
    }
}