package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.mercapp.R;
import com.mercapp.infra.Session;

public class Ofertas extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);

        btnVoltar = (Button) findViewById(R.id.btnVoltarOfertas);
    }

    @Override
    public void onBackPressed(){
        Intent voltar = new Intent(Ofertas.this, TelaSupermercado.class);
        startActivity(voltar);
        finish();
    }

    public void voltar(){
        Intent voltar = new Intent(Ofertas.this, TelaSupermercado.class);
        startActivity(voltar);
        finish();
    }
}
