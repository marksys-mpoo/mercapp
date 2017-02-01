package com.mercapp.infra;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.supermercado.dominio.Carrinho;
import com.mercapp.supermercado.dominio.Produto;
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
    private String funcaoCrudSupermercado;
    private String textButaoFuncaoSupermercado;
    private Produto produtoSelecionado;
    private Carrinho carrinho;

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public  final Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public static Session getInstanciaSessao() {
        return instanciaSessao;
    }

    public  final Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public  final void setPessoaLogada(Pessoa pessoaLogadas) {
        this.pessoaLogada = pessoaLogadas;
    }

    public final  Usuario getUsuarioLogado() {
    return usuarioLogado;
}

    public  final void setUsuarioLogado(Usuario usuarioLogados) {
    this.usuarioLogado = usuarioLogados;
}

    public final  Supermercado getSupermercadoSelecionado() {
    return supermercadoSelecionado;
}

    public final  void setSupermercadoSelecionado(Supermercado supermercadoSelecionados) {
        this.supermercadoSelecionado = supermercadoSelecionados;
    }
    public final  void setProdutoSelecionado(Produto produtoSelecionados) {
        this.produtoSelecionado = produtoSelecionados;
    }
    public  final void logout() {
        this.pessoaLogada = null;
    }


    public final  String getDepartamentoSelecionado() {
        return departamentoSelecionado;
    }

    public final  void setDepartamentoSelecionado(String departamentoSelecionados) {
        this.departamentoSelecionado = departamentoSelecionados;
    }
    public  final LatLng getCoordenadas() {
        return coordenadas;
    }

    public  final void setCoordenadas(LatLng coordenada) {
        this.coordenadas = coordenada;
    }

    public final  String getFuncaoCrudSupermercado() {
        return funcaoCrudSupermercado;
    }

    public final  void setFuncaoCrudSupermercado(String funcaoCrudSupermercados) {
        this.funcaoCrudSupermercado = funcaoCrudSupermercados;
    }

    public  final String getTextButaoFuncaoSupermercado() {
        return textButaoFuncaoSupermercado;
    }

    public  final void setTextButaoFuncaoSupermercado(String textButaoFuncaoSupermercados) {
        this.textButaoFuncaoSupermercado = textButaoFuncaoSupermercados;
    }


}
