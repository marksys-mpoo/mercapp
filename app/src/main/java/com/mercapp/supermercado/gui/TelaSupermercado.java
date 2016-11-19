package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class TelaSupermercado extends AppCompatActivity {

    private Context _context = TelaSupermercado.this;
    private TextView etNomeSupermercado, etFoneSupermercado;
    //private CadastroSupermercados cadastroSupermercadoAuto;
    private String supermercadoEscolhido;
    private Session session = Session.getInstanciaSessao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_supermercado);

        String nomeSM = session.getSupermercadoSelecionado().getNome().toString();
        etNomeSupermercado = (TextView) findViewById(R.id.supermercadoSessao);
        etNomeSupermercado.setText(nomeSM);
        String foneSM = session.getSupermercadoSelecionado().getTelefone().toString();
        etFoneSupermercado = (TextView) findViewById(R.id.telefoneSessao);
        etFoneSupermercado.setText(foneSM);

    }

    public void voltarTelaMenu(View view) {
        Intent voltarMenu = new Intent(TelaSupermercado.this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }

    public void tela_de_produtos(View view) {
        Intent voltarMenu = new Intent(TelaSupermercado.this, ListaProdutosDoSupermercado.class);
        startActivity(voltarMenu);
        finish();
    }

    public String getSupermercadoEscolhido() {
        return supermercadoEscolhido;
    }

    public void setSupermercadoEscolhido(String supermercadoEscolhido) {
        this.supermercadoEscolhido = supermercadoEscolhido;
    }
}
