package com.mercapp.infra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.mercapp.R;
import com.mercapp.usuario.gui.TelaMenu;

public class Sobre extends AppCompatActivity {

    private String descricaoTelaSobre = "O projeto MercApp  tem como objetivo permitir que " +
            "o cliente realize suas compras em poucos minutos pelo seu Smartphone." +
            " O cliente irá buscar um supermercado de sua preferência que esteja cadastrado no " +
            "aplicativo, selecionar os produtos, a forma de pagamento e receber tudo no conforto de sua casa." +
            " Neste contexto os clientes terão mais tempo para fazer outras coisas no dia-a-dia ao " +
            "invés de perder tempo no supermercado, já o supermercado não terá que contratar mais funcionários.";

    @Override
    protected  final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        justificarTextoSobre();
    }

    @Override
    public final  void onBackPressed() {
        Intent voltarMenu = new Intent(this, TelaMenu.class);
        startActivity(voltarMenu);
        finish();
    }

    public  final void justificarTextoSobre() {
        WebView view = (WebView) findViewById(R.id.sobreDescricao);
        String text;
        text = "<html><body><p align=\"justify\">"+descricaoTelaSobre+"</p></body></html>";
        view.loadData(text,"text/html;charset=UTF-8",null);
    }

}
