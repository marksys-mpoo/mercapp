package com.mercapp.supermercado.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Produto;

public class DescricaoProdutoActivity extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private Produto produto = session.getProdutoSelecionado();
    private Button menos;
    private Button mais;
    private TextView quantidadeProduto;

    public Session getSession() {
        return session;
    }

    public TextView getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(TextView quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Button getMais() {
        return mais;
    }

    public void setMais(Button mais) {
        this.mais = mais;
    }

    public Button getMenos() {
        return menos;
    }

    public void setMenos(Button menos) {
        this.menos = menos;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        TextView nomeProduto = (TextView) findViewById(R.id.nomeProduto);
        nomeProduto.setText(getProduto().getNome());
        TextView precoProduto = (TextView) findViewById(R.id.precoProduto);
        precoProduto.setText(getProduto().getPreco().toString());
        TextView descricaoProduto = (TextView) findViewById(R.id.descricaoProduto);
        descricaoProduto.setText(getProduto().getDescricao());
        ImageView imgProduto = (ImageView) findViewById(R.id.imgProduto);
        imgProduto.setImageResource(getProduto().getImageProduto());

        setQuantidadeProduto((TextView)findViewById(R.id.quantProduto));
        setMais((Button) findViewById(R.id.btnMais));
        setMenos((Button) findViewById(R.id.btnMenos));

        getMais().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentValueString = getQuantidadeProduto().getText().toString();
                int presentValueInt = Integer.parseInt(presentValueString);
                presentValueInt++;

                getQuantidadeProduto().setText(String.valueOf(presentValueInt));
            }
        });

        getMenos().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String presentValueString = getQuantidadeProduto().getText().toString();
                int presentValueInt = Integer.parseInt(presentValueString);
                if (presentValueInt > 0){
                    presentValueInt--;
                }

                getQuantidadeProduto().setText(String.valueOf(presentValueInt));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent voltarTela = new Intent(getApplication(), ListaProdutosDoSupermercadoActivity.class);
        startActivity(voltarTela);
        finish();
    }
}
