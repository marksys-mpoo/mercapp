package com.mercapp.usuario.negocio;

import android.content.Context;

import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.persistencia.PessoaPersistencia;
import com.mercapp.usuario.persistencia.UsuarioPersistencia;

public class UsuarioNegocio {

    private Context _context;
    private Session sessao = Session.getInstanciaSessao();

    public Usuario buscaUsuario(String email, String senha) {
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
        Usuario usuarioLogado = usuarioPersistencia.buscarUsuario(email, senha);
        return usuarioLogado;
    }

    public Usuario buscaUsuario(String email) {
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
        Usuario usuarioLogado = usuarioPersistencia.buscarUsuario(email);
        return usuarioLogado;
    }

    public UsuarioNegocio(Context context)
    {
        _context = context;
    }

    public void cadastro(String emailTela, String senhaTela){

        Usuario usuarioCadastro = new Usuario();

        usuarioCadastro.setEmail(emailTela);
        usuarioCadastro.setSenha(senhaTela);

        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
        usuarioPersistencia.cadastrarUsuario(usuarioCadastro);
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
}