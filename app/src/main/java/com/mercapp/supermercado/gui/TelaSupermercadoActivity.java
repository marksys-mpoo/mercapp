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

public class TelaSupermercadoActivity extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private Context context = null;

    @Override
    protected final  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_supermercado);

        String nomeSM = session.getSupermercadoSelecionado().getNome().toString();
        TextView etNomeSupermercado = (TextView) findViewById(R.id.supermercadoSessao);
        etNomeSupermercado.setText(nomeSM);
        String foneSM = session.getSupermercadoSelecionado().getTelefone().toString();
        TextView etFoneSupermercado = (TextView) findViewById(R.id.telefoneSessao);
        etFoneSupermercado.setText(foneSM);

    }

    public  final void telaProdutos(View view) {
        this.selecionarDepartamento("Todos");
    }

    public final  void listaProdutosPadaria(View view) {
        this.selecionarDepartamento("1");
    }

    public  final void listaProdutosFrios(View view) {
        this.selecionarDepartamento("2");
    }

    public  final void listaProdutosAcougue(View view) {
        this.selecionarDepartamento("3");
    }

    public  final void listaProdutosFrutas(View view) {
        this.selecionarDepartamento("4");
    }

    public final  void listaProdutosBebidas(View view) {
        this.selecionarDepartamento("5");
    }

    public  final void listaProdutosMercearia(View view) {
        this.selecionarDepartamento("6");
    }

    public  final void listaProdutosHigiene(View view) {
        this.selecionarDepartamento("7");
    }

    public  final void listaProdutosLimpeza(View view) {
        this.selecionarDepartamento("8");
    }

    public  final void listaProdutosBazar(View view) {
        this.selecionarDepartamento("9");
    }

    public final  void selecionarDepartamento(String departamentoSelecionado) {
        SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(context);
        supermercadoNegocio.iniciarSessaoProduto(departamentoSelecionado);
        Intent voltarMenu = new Intent(TelaSupermercadoActivity.this, ListaProdutosDoSupermercadoActivity.class);
        startActivity(voltarMenu);
        finish();
    }

    public final  void ofertas(View view){
        Intent irOfertas = new Intent(TelaSupermercadoActivity.this, OfertasActivity.class);
        startActivity(irOfertas);
        finish();
    }

    @Override
    public final  void onBackPressed() {
        session.setSupermercadoSelecionado(null);
        Intent voltarTelaMenuMapa = new Intent(TelaSupermercadoActivity.this, TelaMenuActivity.class);
        startActivity(voltarTelaMenuMapa);
        finish();
    }

}
