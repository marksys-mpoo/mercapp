package com.mercapp.infra;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.dominio.Usuario;

public class Session {
    private static Session instanciaSessao = new Session();
    private Pessoa pessoaLogada;
    private Usuario usuarioLogado;
    private Supermercado supermercadoSelecionado;
    private String departamentoSelecionado;
    private LatLng coordenadas;

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


    public String getDepartamentoSelecionado() {
        return departamentoSelecionado;
    }

    public void setDepartamentoSelecionado(String departamentoSelecionado) {
        this.departamentoSelecionado = departamentoSelecionado;
    }
    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
    }


}
