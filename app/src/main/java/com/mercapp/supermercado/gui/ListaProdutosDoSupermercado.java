package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.BDHelper;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;

public class ListaProdutosDoSupermercado extends AppCompatActivity {

    private ListView lista;
    private Context _context = ListaProdutosDoSupermercado.this;
    private Session session = Session.getInstanciaSessao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos_do_supermercado);

        SupermercadoNegocio consulta = new SupermercadoNegocio(_context);
        Integer idSM = session.getSupermercadoSelecionado().getId();
        String idSM_string = idSM.toString();
        Cursor cursor = consulta.listaProdutosDoSupermercado(idSM_string);

        String[] nomeCampos = new String[] {BDHelper.COLUNA_ID_PRODUTO, BDHelper.COLUNA_DESCRICAO, BDHelper.COLUNA_PRECO, BDHelper.COLUNA_ID_SUPERMERCADO_PRODUTO};
        int[] idViews = new int[] {R.id.colunaProduto1, R.id.colunaProduto2, R.id.colunaProduto3, R.id.colunaProduto4};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(_context,R.layout.produtos,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.lista_produtos_do_supermercado);
        lista.setAdapter(adaptador);
    }

    public void changeTelaListaProdutosDoSupermercadoToTelaSupermercado(View view) {
        Intent voltarAdm = new Intent(ListaProdutosDoSupermercado.this, TelaSupermercado.class);
        startActivity(voltarAdm);
        finish();
    }
}
