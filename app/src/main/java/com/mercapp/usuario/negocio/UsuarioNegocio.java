package com.mercapp.usuario.negocio;

import android.content.Context;

import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.persistencia.UsuarioPersistencia;

public class UsuarioNegocio {

    private Context _context;

// Metodo novo
    public Usuario buscar(Usuario usuario){
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
        Usuario usuarioLogado = usuarioPersistencia.buscar(usuario);
        return usuarioLogado;
    }
// Fim metodo novo

    public Usuario buscar(String email, String senha) {
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
        Usuario usuarioLogado = usuarioPersistencia.buscar(email, senha);
        return usuarioLogado;
    }

    public Usuario buscar(String email) {
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
        Usuario usuarioLogado = usuarioPersistencia.buscar(email);
        return usuarioLogado;
    }

    public UsuarioNegocio(Context context)
    {
        _context = context;
    }

    public void cadastro(Usuario usuario) {
//    public void cadastro(String emailTela, String senhaTela){

//        Usuario usuarioCadastro = new Usuario();

//        usuarioCadastro.setEmail(emailTela);
//        usuarioCadastro.setSenha(senhaTela);

        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(_context);
//        usuarioPersistencia.cadastrar(usuarioCadastro);
        usuarioPersistencia.cadastrar(usuario);
    }
}