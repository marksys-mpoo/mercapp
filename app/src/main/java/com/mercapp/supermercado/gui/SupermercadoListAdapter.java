package com.mercapp.supermercado.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.supermercado.dominio.Supermercado;

import java.util.List;

public class SupermercadoListAdapter extends ArrayAdapter<Supermercado> {

    private Context context;
    private List<Supermercado> supermercados;


    public SupermercadoListAdapter(Context contexto, List<Supermercado> supermercado) {
        super(contexto, 0, supermercado);
        this.context = contexto;
        this.supermercados = supermercado;
    }

    @Override
    public  final View getView(int position, View converterTela, ViewGroup parent) {
        Supermercado supermercadoPosicao = this.supermercados.get(position);

        converterTela = LayoutInflater.from(this.context).inflate(R.layout.supermercados, null);

        TextView nome = (TextView) converterTela.findViewById(R.id.colunaProduto2);
        nome.setText(supermercadoPosicao.getNome());

        TextView telefone = (TextView) converterTela.findViewById(R.id.colunaProduto3);
        telefone.setText(supermercadoPosicao.getTelefone());

        return converterTela;
    }
}
