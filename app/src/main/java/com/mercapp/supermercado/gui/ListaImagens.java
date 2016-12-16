package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.mercapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ListaImagens extends AppCompatActivity {

    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagens);

        Field[] ID_Fields = android.R.drawable.class.getFields();
        int[] resArray = new int[ID_Fields.length];
        for (int i = 0; i < ID_Fields.length; i++) {
            try {
                //System.out.println("R.drawable." + f.getName());
                resArray[i] = ID_Fields[i].getInt(null);
                //System.out.println("GetInt = " + ID_Fields[i].getInt(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }
}
