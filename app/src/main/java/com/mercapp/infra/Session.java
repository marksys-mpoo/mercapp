package com.mercapp.infra;

import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.dominio.Usuario;

public class Session {

        private static Session instanciaSessao = new Session();
        private Session(){}
        private Pessoa pessoaLogada;
        private Usuario usuarioLogado;


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

    public void Logout() {
            this.pessoaLogada = null;
        }

}
