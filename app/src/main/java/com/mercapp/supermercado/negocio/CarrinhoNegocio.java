package com.mercapp.supermercado.negocio;

import android.content.Context;

import com.mercapp.supermercado.dominio.Carrinho;
import com.mercapp.supermercado.persistencia.CarrinhoPersistencia;
import com.mercapp.supermercado.persistencia.ProdutoPersistencia;

import java.util.List;

/**
 * Created by WELLINGTON on 27/01/2017.
 */

public class CarrinhoNegocio {
    private Context context;

    public CarrinhoNegocio(Context context) {
        this.context = context;
    }


    public  final void cadastrar(Carrinho carrinhoCadastro){
        CarrinhoPersistencia carrinhoPersistencia = new CarrinhoPersistencia(context);
        carrinhoPersistencia.cadastrar(carrinhoCadastro);
    }

    public final List<Carrinho> listar(){
        CarrinhoPersistencia consulta = new CarrinhoPersistencia(context);
        return consulta.listar();
    }
}
