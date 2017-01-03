package com.mercapp.supermercado.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mercapp.R;

import java.lang.reflect.Field;

public class ListaImagens extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagens);

        Field[] idFields = android.R.drawable.class.getFields();
        int[] resArray = new int[idFields.length];
        for (int i = 0; i < idFields.length; i++) {
            try {
                resArray[i] = idFields[i].getInt(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }
}
