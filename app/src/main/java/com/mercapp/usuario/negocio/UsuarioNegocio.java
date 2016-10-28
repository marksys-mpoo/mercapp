package com.mercapp.usuario.negocio;

import android.content.Context;

import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.persistencia.UsuarioPersistencia;

public class UsuarioNegocio {

    private Context _context;
    private Session sessao = Session.getInstanciaSessao();

    public Usuario buscaUsuario(String email, String senha) {
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
        Usuario usuarioLogado = usuarioPersistencia.buscarUsuario(email, senha);
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

    public void iniciarSessao(Usuario usuario){
        sessao.setUsuarioLogado(usuario);
    }
}