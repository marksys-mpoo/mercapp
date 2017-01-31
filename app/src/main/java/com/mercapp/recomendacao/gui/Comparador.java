package com.mercapp.recomendacao.gui;

import com.mercapp.supermercado.dominio.Produto;

import java.util.Comparator;
import java.util.Map;

class Comparador implements Comparator<Object> {
    Map<Integer, Double> base;

    public Comparador(Map<Integer, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(Object a, Object b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}