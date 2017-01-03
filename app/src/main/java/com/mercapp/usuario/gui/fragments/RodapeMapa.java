package com.mercapp.usuario.gui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.gui.TelaSupermercadoActivity;


public class RodapeMapa extends Fragment {

    private FragmentManager fragmentManager;
    private Session session = Session.getInstanciaSessao();

    private final FragmentManager fm = getFragmentManager();

    private Activity context;
    private FragmentManager supportFragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        return inflater.inflate(R.layout.fragment_rodape_mapa, container, false);
    }

    public void onStart() {
        super.onStart();

        String superSessao = session.getSupermercadoSelecionado().getNome().toString();
        setTextSupermercado(superSessao);

        Button bt3=(Button)context.findViewById(R.id.rodape_to_Supermercado);
        bt3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent supermercado = new Intent(getActivity(), TelaSupermercadoActivity.class);
                startActivity(supermercado);
                getActivity().finish();

            }
        });
    }
    public void setTextSupermercado(String text){
        TextView textView = (TextView) getView().findViewById(R.id.supermercadoSelecionadoWaypoint);
        textView.setText(text);
    }
}
