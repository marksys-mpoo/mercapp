package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mercapp.R;

public class OfertasActivity extends AppCompatActivity {

    @Override
    protected  final  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);

    }

    @Override
    public  final  void onBackPressed(){
        Intent voltar = new Intent(OfertasActivity.this, TelaSupermercadoActivity.class);
        startActivity(voltar);
        finish();
    }

    public  final  void voltar(View view){
        Intent back = new Intent(OfertasActivity.this, TelaSupermercadoActivity.class);
        startActivity(back);
        finish();
    }
}
