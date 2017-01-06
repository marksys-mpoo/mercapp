package com.mercapp.usuario.negocio;

import android.content.Context;

import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.persistencia.UsuarioPersistencia;

public class UsuarioNegocio {

    private Context context;

    public UsuarioNegocio(Context contexto)
    {
        this.context = contexto;
    }
    
    public final  Usuario buscar(String email, String senha) {
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(context);
        return usuarioPersistencia.buscar(email, senha);
    }

    public final  Usuario buscar(String email) {
        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(context);
        return usuarioPersistencia.buscar(email);
    }


    public final  void cadastro(String emailTela, String senhaTela){

        Usuario usuarioCadastro = new Usuario();

        usuarioCadastro.setEmail(emailTela);
        usuarioCadastro.setSenha(senhaTela);

        UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia(context);
        usuarioPersistencia.cadastrar(usuarioCadastro);
    }
}