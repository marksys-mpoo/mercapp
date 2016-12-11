package com.mercapp.usuario.negocio;

import android.content.Context;

import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.persistencia.PessoaPersistencia;

public class PessoaNegocio {

    private Context _context;

    public PessoaNegocio(Context _context) {
        this._context = _context;
    }

    public Pessoa buscar(int usuarioId){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
        Pessoa pessoaLogada = pessoaPersistencia.buscar(usuarioId);
        return pessoaLogada;
    }

//    public Pessoa buscar(String numeroCartao){
//        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
//        Pessoa pessoaLogada = pessoaPersistencia.buscar(numeroCartao);
//        return pessoaLogada;
//    }

      public void cadastro (Pessoa pessoa) {
//    public void cadastro(String nome, String telefone, String numeroCartao){
//        Pessoa pessoaCadastro = new Pessoa();
//
//       pessoaCadastro.setNome(nome);
//        pessoaCadastro.setTelefone(telefone);
//        pessoaCadastro.setNumeroCartao(numeroCartao);
//
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
        pessoaPersistencia.cadastrar(pessoa);
    }

    public void editar(Pessoa pessoa){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(_context);
        pessoaPersistencia.editar(pessoa);
    }
}
