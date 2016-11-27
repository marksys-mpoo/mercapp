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
import com.mercapp.usuario.gui.TelaMenuActivity;

public class Administrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }

    public void changeTelaAdministradorToCadastroSupermercado(View view) {
        Intent voltarMenu = new Intent(Administrador.this, CadastroSupermercados.class);
        startActivity(voltarMenu);
        finish();
    }
    public void voltarToMenuMapa(View view) {
        Intent voltarMapa = new Intent(Administrador.this, TelaMenuActivity.class);
        startActivity(voltarMapa);
        finish();
    }

    public void changeTelaAdministradorToCadastroProdutos(View view) {
        Intent voltarMenu = new Intent(Administrador.this, CadastroProdutos.class);
        startActivity(voltarMenu);
        finish();
    }
    public void changeTelaAdministradorToListaSupermercado(View view) {
        Intent voltarMenu = new Intent(Administrador.this, ListaSupermercados.class);
        startActivity(voltarMenu);
        finish();
    }
    public void changeTelaAdministradorToListaProdutos(View view) {
        Intent voltarMenu = new Intent(Administrador.this, ListaProdutos.class);
        startActivity(voltarMenu);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(Administrador.this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }

}
