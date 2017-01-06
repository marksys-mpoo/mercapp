package com.mercapp.supermercado.dominio;

import com.google.android.gms.maps.model.LatLng;
    public class Supermercado {
    private int id;
    private String nome;
    private String telefone;
    private LatLng coordenadas;

    public  final int getId() {
        return id;
    }

    public  final void setId(int ids) {
        this.id = ids;
    }

    public  final String getNome() {
        return nome;
    }

    public final  void setNome(String nomes) {
        this.nome = nomes;
    }

    public final  String getTelefone() {
        return telefone;
    }

    public final  void setTelefone(String telefones) {
        this.telefone = telefones;
    }

    public final  LatLng getCoordenadas() {
        return coordenadas;
    }

    public  final void setCoordenadas(LatLng coordenada) {
        this.coordenadas = coordenada;
    }
}