package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class TelaSupermercado extends AppCompatActivity {

    private TextView etNomeSupermercado, etFoneSupermercado;
    private Session session = Session.getInstanciaSessao();
    private ListaProdutosDoSupermercado listaProdutosDoSupermercado;
    private Context _context = null;

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

    public void tela_de_produtos(View view) {
        this.selecionarDepartamento("Todos");
    }

    public void buscaProdutosPadaria(View view) {
        this.selecionarDepartamento("1");
    }

    public void buscaProdutosFrios(View view) {
        this.selecionarDepartamento("2");
    }

    public void buscaProdutosAcougue(View view) {
        this.selecionarDepartamento("3");
    }

    public void buscaProdutosFrutas(View view) {
        this.selecionarDepartamento("4");
    }

    public void buscaProdutosBebidas(View view) {
        this.selecionarDepartamento("5");
    }

    public void buscaProdutosMercearia(View view) {
        this.selecionarDepartamento("6");
    }

    public void buscaProdutosHigiene(View view) {
        this.selecionarDepartamento("7");
    }

    public void buscaProdutosLimpeza(View view) {
        this.selecionarDepartamento("8");
    }

    public void buscaProdutosBazar(View view) {
        this.selecionarDepartamento("9");
    }

    public void selecionarDepartamento(String departamentoSelecionado) {

        SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoNegocio.iniciarSessaoProduto(departamentoSelecionado);
        Intent voltarMenu = new Intent(TelaSupermercado.this, ListaProdutosDoSupermercado.class);
        startActivity(voltarMenu);
        finish();

    }

    public void voltarTelaMenu(View view) {
        Intent voltarMenu = new Intent(TelaSupermercado.this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarTelaMenuMapa = new Intent(TelaSupermercado.this, TelaMenuActivity.class);
        startActivity(voltarTelaMenuMapa);
        finish();
    }

}
