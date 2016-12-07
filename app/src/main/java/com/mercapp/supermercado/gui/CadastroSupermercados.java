package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.R;
import com.mercapp.infra.Administrador;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class CadastroSupermercados extends AppCompatActivity {

    private Context _context = CadastroSupermercados.this;
    private Session session = Session.getInstanciaSessao();
    private SupermercadoNegocio supermercadoNegocio;
    private Supermercado supermercadoCadastrado;
    private EditText etSupermercadoNome, etSupermercadoTelefone, etLogintude, etLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_supermercado);
        etSupermercadoNome = (EditText) findViewById(R.id.etSupNome);
        etSupermercadoTelefone = (EditText) findViewById(R.id.etSupTelefone);
        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("CoordLat") ){
            Double coordLat = bundle.getDouble("CoordLat");
            String coordLatString = coordLat.toString();
            etLatitude = (EditText) findViewById(R.id.etSupLat);
            etLatitude.setText(coordLatString);
        }

        if (bundle.containsKey("CoordLong") ){
            Double coordLong = bundle.getDouble("CoordLong");
            String coordLongString = coordLong.toString();
            etLogintude = (EditText) findViewById(R.id.etSupLong);
            etLogintude.setText(coordLongString);
        }
    }

    public void cadastroDireto(View view) {
        LatLng ufrpeExtrabom = new LatLng(-8.017877, -34.944440);
        Supermercado supermercado1 = CadastrarSupermercado("Extrabom - UFRPE","1111", ufrpeExtrabom);
        efetuarCadastroSupermercado(supermercado1);
        LatLng doisIrmaosExtrabom = new LatLng(-8.021448, -34.933130);
        Supermercado supermercado2 = CadastrarSupermercado("Extrabom - Dois Irmãos","2222", doisIrmaosExtrabom);
        efetuarCadastroSupermercado(supermercado2);
        LatLng parnamirimExtrabom = new LatLng(-8.027122, -34.9170917);
        Supermercado supermercado3 = CadastrarSupermercado("Extrabom - Parnamirim","3333", parnamirimExtrabom);
        efetuarCadastroSupermercado(supermercado3);

    }
    private Supermercado CadastrarSupermercado(String nome, String telefone, LatLng coordenadas) {
        Supermercado supermercado = new Supermercado();
        supermercado.setNome(nome);
        supermercado.setTelefone(telefone);
        supermercado.setCoordenadas(coordenadas);
        return supermercado;
    }

    public void efetuarCadastroSupermercado(Supermercado supermercado) {
        String nome = supermercado.getNome();
        String telefone = supermercado.getTelefone();
        LatLng coordenadas = supermercado.getCoordenadas();
        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(nome);
        if (supermercadoCadastrado == null) {
            supermercadoNegocio.cadastroSupermercado(nome, telefone, coordenadas);
            Toast.makeText(this, "Supermercado " + nome + " cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Supermercado já exitente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarSupermercadoManual(View view) {
        String nome = etSupermercadoNome.getText().toString().trim();
        String telefone = etSupermercadoTelefone.getText().toString().trim();
        Double latitude = Double.parseDouble(etLatitude.getText().toString().trim());
        Double longitude = Double.parseDouble(etLogintude.getText().toString().trim());
        LatLng coordenadas = new LatLng(latitude, longitude);
        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(nome);
        if (supermercadoCadastrado == null) {
            supermercadoNegocio.cadastroSupermercado(nome, telefone, coordenadas);
            Toast.makeText(this, "Supermercado " + nome + " cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Supermercado já existente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeScreenCadastroSupermercadoToMapa(View view) {
        Intent addCoordenadas = new Intent(CadastroSupermercados.this, TelaMenuActivity.class);
        startActivity(addCoordenadas);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(CadastroSupermercados.this, TelaMenuActivity.class);
        startActivity(voltarMenu);
        finish();
    }
}

/*
        //Posições
        LatLng boaViagemExtrabom = new LatLng(-8.1201256, -34.9031476);
        LatLng pinaExtrabom = new LatLng(-8.09494, -34.8877137);
        LatLng piedadeExtrabom = new LatLng(-8.183888, -34.9211767);
        LatLng mustardinhaExtrabom = new LatLng(-8.069109, -34.9198987);
        LatLng olindaExtrabom = new LatLng(-7.9995286, -34.8469477);
        LatLng parnamirimExtrabom = new LatLng(-8.027122, -34.9170917);
        LatLng encruzilhadaExtrabom = new LatLng(-8.0371238, -34.899941);
        LatLng torreExtrabom = new LatLng(-8.0462157, -34.9116174);
        LatLng ufrpeExtrabom = new LatLng(-8.017877, -34.944440);
        LatLng doisIrmaosExtrabom = new LatLng(-8.021448, -34.933130);

        //MARKER's
        customAddMaker(boaViagemExtrabom, "Extrabom - BoaViagem");
        customAddMaker(piedadeExtrabom, "Extrabom - Piedade");
        customAddMaker(mustardinhaExtrabom, "Extrabom - Mustardinha");
        customAddMaker(olindaExtrabom, "Extrabom - Olinda");
        customAddMaker(parnamirimExtrabom, "Extrabom - Parnamirim"); // add
        customAddMaker(pinaExtrabom, "Extrabom - Pina");
        customAddMaker(encruzilhadaExtrabom, "Extrabom - Encruzilhada");
        customAddMaker(torreExtrabom, "Extrabom - Torre");
        customAddMaker(ufrpeExtrabom, "Extrabom - UFRPE"); // add
        customAddMaker(doisIrmaosExtrabom, "Extrabom - Dois Irmãos"); // add

        */