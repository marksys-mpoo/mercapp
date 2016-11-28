package com.mercapp.infra;

import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.dominio.Usuario;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

public class Session {
    private static Session instanciaSessao = new Session();
    private Pessoa pessoaLogada;
    private Usuario usuarioLogado;
    private Supermercado supermercadoSelecionado;


    public static Session getInstanciaSessao() {
        return instanciaSessao;
    }

    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa pessoaLogada) {
        this.pessoaLogada = pessoaLogada;
    }

    public Usuario getUsuarioLogado() {
    return usuarioLogado;
}

    public void setUsuarioLogado(Usuario usuarioLogado) {
    this.usuarioLogado = usuarioLogado;
}

    public Supermercado getSupermercadoSelecionado() {
    return supermercadoSelecionado;
}

    public void setSupermercadoSelecionado(Supermercado supermercadoSelecionado) {
        this.supermercadoSelecionado = supermercadoSelecionado;
    }
    public void Logout() {
        this.pessoaLogada = null;
    }


}
