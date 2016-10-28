package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mercapp.R;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class TelaSupermercado extends AppCompatActivity {

    private Context _context = TelaSupermercado.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_supermercado);

    }

    public void voltarTelaMenu(View view) {
        Intent voltarMenu = new Intent(TelaSupermercado.this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();

    }

}
