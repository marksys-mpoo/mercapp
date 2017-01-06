package com.mercapp.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mercapp.R;

public class AjudaActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);
    }

    @Override
    public final void onBackPressed() {
        Intent voltarMenu = new Intent(this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }
}
