package com.mercapp.infra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mercapp.R;
import com.mercapp.supermercado.gui.CadastroProdutosActivity;
import com.mercapp.supermercado.gui.CadastroSupermercadosActivity;
import com.mercapp.supermercado.gui.ListaProdutosActivity;
import com.mercapp.supermercado.gui.ListaSupermercadosActivity;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class Administrador extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }

    public final void changeTelaAdministradorToCadastroSupermercado(View view) {
        Intent cadastrar = new Intent(Administrador.this, CadastroSupermercadosActivity.class);
        cadastrar.putExtra("CoordLat",0);
        cadastrar.putExtra("CoordLong",0);
        startActivity(cadastrar);
        finish();
    }

    public final void voltarToMenuMapa(View view) {
        Intent voltarMapa = new Intent(Administrador.this, TelaMenuActivity.class);
        startActivity(voltarMapa);
        finish();
    }

    public final void changeTelaAdministradorToCadastroProdutos(View view) {
        Intent voltarMenu = new Intent(Administrador.this, CadastroProdutosActivity.class);
        startActivity(voltarMenu);
        finish();
    }
    public final void changeTelaAdministradorToListaSupermercado(View view) {
        Intent voltarMenu = new Intent(Administrador.this, ListaSupermercadosActivity.class);
        startActivity(voltarMenu);
        finish();
    }
    public final void changeTelaAdministradorToListaProdutos(View view) {
        Intent voltarMenu = new Intent(Administrador.this, ListaProdutosActivity.class);
        startActivity(voltarMenu);
        finish();
    }

    @Override
    public final void onBackPressed() {
        Intent voltarMenu = new Intent(Administrador.this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }

}
