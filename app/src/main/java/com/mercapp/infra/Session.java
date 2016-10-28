package com.mercapp.infra;

import com.mercapp.usuario.dominio.Usuario;

public class Session {

        private static Session instanciaSessao = new Session();
        private Session(){}
        private Usuario usuarioLogado;

        public static Session getInstanciaSessao() {
            return instanciaSessao;
        }

        public Usuario getUsuarioLogado() {
            return usuarioLogado;
        }

        public void setUsuarioLogado(Usuario usuarioLogado) {
            this.usuarioLogado = usuarioLogado;
        }

        public void usuarioLogout() {
            this.usuarioLogado = null;
        }

}
