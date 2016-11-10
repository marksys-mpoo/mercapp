package com.mercapp.supermercado.negocio;

import android.content.Context;

import com.mercapp.infra.Session;
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

    public void cadastroSupermercado(String nome, String telefone){

        Supermercado supermercadoCadastro = new Supermercado();

        //supermercadoCadastro.setId();
        supermercadoCadastro.setNome(nome);
        supermercadoCadastro.setTelefone(telefone);

        SupermercadoPersistencia supermercadoPersistencia = new SupermercadoPersistencia(_context);
        supermercadoPersistencia.cadastrarSupermercado(supermercadoCadastro);
    }

    public void iniciarSessao(Supermercado supermercado){
        sessao.setSupermercadoSelecionado(supermercado);
    }
}