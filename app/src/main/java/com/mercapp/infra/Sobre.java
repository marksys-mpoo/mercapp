package com.mercapp.infra;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.supermercado.gui.CadastroProdutos;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class Sobre extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(Sobre.this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }

}
