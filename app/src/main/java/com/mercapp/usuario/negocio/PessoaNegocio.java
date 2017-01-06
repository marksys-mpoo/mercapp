package com.mercapp.usuario.negocio;

import android.content.Context;

import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.persistencia.PessoaPersistencia;

public class PessoaNegocio {

    private Context context;

    public PessoaNegocio(Context contexto) {
        this.context = contexto;
    }

    public  final Pessoa buscar(int usuarioId){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(context);
        return pessoaPersistencia.buscar(usuarioId);
    }

    public final  Pessoa buscar(String numeroCartao){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(context);
        return pessoaPersistencia.buscar(numeroCartao);
    }

    public final  void cadastro(String nome, String telefone, String numeroCartao){
        Pessoa pessoaCadastro = new Pessoa();

        pessoaCadastro.setNome(nome);
        pessoaCadastro.setTelefone(telefone);
        pessoaCadastro.setNumeroCartao(numeroCartao);

        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(context);
        pessoaPersistencia.cadastrar(pessoaCadastro);
    }

    public final void editar(Pessoa pessoa){
        PessoaPersistencia pessoaPersistencia = new PessoaPersistencia(context);
        pessoaPersistencia.editar(pessoa);
    }
}
