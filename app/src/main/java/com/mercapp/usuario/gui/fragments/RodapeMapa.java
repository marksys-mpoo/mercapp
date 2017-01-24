package com.mercapp.usuario.gui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.gui.TelaSupermercado;


public class RodapeMapa extends Fragment {

    private Session session = Session.getInstanciaSessao();
    private Activity context;


    @Override
    public  final Activity getContext() {
        return context;
    }

    public  final void setContext(Activity contexto) {
        this.context = contexto;
    }


    public final  View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setContext(getActivity());
        return inflater.inflate(R.layout.fragment_rodape_mapa, container, false);
    }

    @Override
    public  final void onStart() {
        super.onStart();

        String superSessao = session.getSupermercadoSelecionado().getNome().toString();
        setTextSupermercado(superSessao);

        Button bt3=(Button)getContext().findViewById(R.id.rodape_to_Supermercado);
        bt3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent supermercado = new Intent(getActivity(), TelaSupermercado.class);
                startActivity(supermercado);
                getActivity().finish();

            }
        });
    }
    public final  void setTextSupermercado(String text){
        TextView textView = (TextView) getView().findViewById(R.id.supermercadoSelecionadoWaypoint);
        textView.setText(text);
    }
}
