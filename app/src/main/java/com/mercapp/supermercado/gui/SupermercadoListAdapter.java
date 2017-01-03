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


    public SupermercadoListAdapter(Context context, List<Supermercado> supermercados) {
        super(context, 0, supermercados);
        this.context = context;
        this.supermercados = supermercados;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Supermercado supermercadoPosicao = this.supermercados.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.supermercados, null);

        TextView nome = (TextView) convertView.findViewById(R.id.colunaProduto2);
        nome.setText(supermercadoPosicao.getNome());

        TextView telefone = (TextView) convertView.findViewById(R.id.colunaProduto3);
        telefone.setText(supermercadoPosicao.getTelefone());

        return convertView;
    }
}
