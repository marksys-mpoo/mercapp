package com.mercapp.usuario.negocio;

import android.content.Context;

import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.persistencia.PessoaPersistencia;

/**
 * Created by WELLINGTON on 07/12/2016.
 */

public class PessoaNegocio {

    private Context _context;

    public PessoaNegocio(Context _context) {
        this._context = _context;
    }

    public Pessoa buscarPessoa(int usuarioId){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
        Pessoa pessoaLogada = pessoaPersistencia.buscarPessoa(usuarioId);
        return pessoaLogada;
    }

    public Pessoa buscarPessoa(String numeroCartao){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
        Pessoa pessoaLogada = pessoaPersistencia.buscarPessoa(numeroCartao);
        return pessoaLogada;
    }

    public void cadastroPessoa(String nome, String telefone, String numeroCartao){
        Pessoa pessoaCadastro = new Pessoa();

        pessoaCadastro.setNome(nome);
        pessoaCadastro.setTelefone(telefone);
        pessoaCadastro.setNumeroCartao(numeroCartao);

        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
        pessoaPersistencia.cadastrarPessoa(pessoaCadastro);
    }

    public void editarPessoa(Pessoa pessoa){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
        pessoaPersistencia.editarPessoa(pessoa);
    }
}
