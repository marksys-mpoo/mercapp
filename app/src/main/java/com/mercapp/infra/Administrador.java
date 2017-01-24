package com.mercapp.infra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mercapp.R;
import com.mercapp.supermercado.gui.CadastroProdutos;
import com.mercapp.supermercado.gui.CadastroSupermercados;
import com.mercapp.supermercado.gui.ListaProdutos;
import com.mercapp.supermercado.gui.ListaSupermercados;
import com.mercapp.usuario.gui.TelaMenu;

public class Administrador extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }

    public final void changeTelaAdministradorToCadastroSupermercado(View view) {
        Intent cadastrar = new Intent(Administrador.this, CadastroSupermercados.class);
        cadastrar.putExtra("CoordLat",0);
        cadastrar.putExtra("CoordLong",0);
        startActivity(cadastrar);
        finish();
    }

    public final void voltarToMenuMapa(View view) {
        Intent voltarMapa = new Intent(Administrador.this, TelaMenu.class);
        startActivity(voltarMapa);
        finish();
    }

    public final void changeTelaAdministradorToCadastroProdutos(View view) {
        Intent voltarMenu = new Intent(Administrador.this, CadastroProdutos.class);
        startActivity(voltarMenu);
        finish();
    }
    public final void changeTelaAdministradorToListaSupermercado(View view) {
        Intent voltarMenu = new Intent(Administrador.this, ListaSupermercados.class);
        startActivity(voltarMenu);
        finish();
    }
    public final void changeTelaAdministradorToListaProdutos(View view) {
        Intent voltarMenu = new Intent(Administrador.this, ListaProdutos.class);
        startActivity(voltarMenu);
        finish();
    }

    @Override
    public final void onBackPressed() {
        Intent voltarMenu = new Intent(Administrador.this, TelaMenu.class);
        startActivity(voltarMenu);
        finish();
    }

}
