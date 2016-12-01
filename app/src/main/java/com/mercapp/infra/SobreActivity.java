package com.mercapp.infra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mercapp.R;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class SobreActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
    }

    public void onBackPressed() {
        Intent voltarMenu = new Intent(this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }


}
