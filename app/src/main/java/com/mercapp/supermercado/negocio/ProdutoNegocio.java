package com.mercapp.supermercado.negocio;

import android.content.Context;

import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.persistencia.ProdutoPersistencia;

import java.util.List;

public class ProdutoNegocio {

    private Context context;

    public ProdutoNegocio(Context contexto) {
        this.context = contexto;
    }

    public final Produto buscar(Integer id) {
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(context);
        return produtoPersistencia.buscar(id);
    }

    public final Produto buscar(String nome) {
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(context);
        return produtoPersistencia.buscar(nome);
    }

    public final List<Produto> listar(String nomeProduto){
        ProdutoPersistencia listagem = new ProdutoPersistencia(context);
        return listagem.listar(nomeProduto);
    }

    public final void cadastrar(Produto produtoCadastro){
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(context);
        produtoPersistencia.cadastrar(produtoCadastro);
    }

    public final void editar(Produto produto){
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(context);
        produtoPersistencia.editar(produto);
    }
    public  final void deletar(Produto produto){
        ProdutoPersistencia produtoPersistencia= new ProdutoPersistencia(context);
        produtoPersistencia.deletar(produto);
    }
    public final  List<Produto> listar(){
        ProdutoPersistencia consulta = new ProdutoPersistencia(context);
        return consulta.listar();
    }

    public final List<Produto> listaProdutosDoSupermercado(String idSuper){
        ProdutoPersistencia consulta = new ProdutoPersistencia(context);
        return consulta.listaDadosProdutosDoSupermercado(idSuper);
    }

    public final  List<Produto> listar(String idSupermercado, int numDepartamento){
        ProdutoPersistencia consulta = new ProdutoPersistencia(context);
        return consulta.listar(idSupermercado, numDepartamento);
    }
}
