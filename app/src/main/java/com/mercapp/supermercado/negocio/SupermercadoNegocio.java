package com.mercapp.supermercado.negocio;

import android.content.Context;
import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;
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


    public SupermercadoNegocio(Context context)
    {
        _context = context;
    }

    public void cadastroSupermercado(String nome, String telefone, LatLng coordenadas){
        Supermercado supermercadoCadastro = new Supermercado();
        supermercadoCadastro.setNome(nome);
        supermercadoCadastro.setTelefone(telefone);
        supermercadoCadastro.setCoordenadas(coordenadas);
        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        supermercadoPersistencia.cadastrarSupermercado(supermercadoCadastro);
    }

    public Cursor listaSupermercados(){
        SupermercadoPersistencia consulta = new SupermercadoPersistencia(_context);
        Cursor cursor = consulta.listaDados();
        return cursor;
    }

    public Cursor listarSupermercadosPorParteDoNome(String inputText){
        SupermercadoPersistencia listagem = new SupermercadoPersistencia(_context);
        Cursor cursor = listagem.listarSupermercadosPorParteDoNome(inputText);
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