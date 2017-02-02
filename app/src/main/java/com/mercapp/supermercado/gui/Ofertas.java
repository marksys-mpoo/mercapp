package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.mercapp.recomendacao.dominio.RecomendacaoProduto;
import com.mercapp.recomendacao.gui.SlopeOne;
import com.mercapp.R;
import com.mercapp.supermercado.dominio.Produto;
import com.mercapp.supermercado.negocio.ProdutoNegocio;
import com.mercapp.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.Session;

import static android.R.attr.data;
import static com.mercapp.recomendacao.gui.SlopeOne.print;


public class Ofertas extends AppCompatActivity {

    com.mercapp.infra.Session session;
    @Override
    protected  final  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);

//        Pegar o produto e da pagina de recomendação
//        ProdutoNegocio produtoNegocios  = new ProdutoNegocio(this);
//        RecomendacaoProduto recomendacaoProduto = new RecomendacaoProduto();
//        recomendacaoProduto.getProdutos();
    }



    @Override
    public  final  void onBackPressed(){
        Intent voltar = new Intent(Ofertas.this, TelaSupermercado.class);
        startActivity(voltar);
        finish();
    }


    public  final  void voltar(View view){
        this.onBackPressed();
    }
}
