package com.mercapp.usuario.gui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mercapp.R;
import com.mercapp.supermercado.gui.TelaSupermercado;


public class RodapeMapa extends Fragment {

    private FragmentManager fragmentManager;

    final FragmentManager fm = getFragmentManager();

    Activity context;
    private FragmentManager supportFragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        return inflater.inflate(R.layout.fragment_rodape_mapa, container, false);
    }

    public void onStart() {
        super.onStart();

        Button bt3=(Button)context.findViewById(R.id.rodape_to_Supermercado);
        bt3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent supermercado = new Intent(getActivity(), TelaSupermercado.class);
                startActivity(supermercado);
                getActivity().finish();

            }
        });
    }

    private void showTela (Fragment fragment, String name) {

        fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, fragment, name);

        transaction.commit();

    }

    private void showTela2 (Fragment fragment, String name) {

        fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container2, fragment, name);

        transaction.commit();

    }

}
