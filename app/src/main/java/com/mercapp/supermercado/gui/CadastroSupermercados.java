package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.TelaMenuActivity;
import com.mercapp.usuario.negocio.Validacao;

public class CadastroSupermercados extends AppCompatActivity {

    private static final String stringVazia = "";


    private Context _context = CadastroSupermercados.this;
    private Session session = Session.getInstanciaSessao();
    private SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(_context);
    private EditText etSupermercadoNome, etSupermercadoTelefone, etLogintude, etLatitude;
    private Button btnSalvarEditar;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_supermercado);

        etSupermercadoNome = (EditText) findViewById(R.id.etSupNome);
        etSupermercadoTelefone = (EditText) findViewById(R.id.etSupTelefone);
        etLatitude = (EditText) findViewById(R.id.etSupLat);
        etLogintude = (EditText) findViewById(R.id.etSupLong);
        btnSalvarEditar = (Button) findViewById(R.id.btnCadastrarSupermercado);

        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("CoordLat")) {
            Double coordLat = bundle.getDouble("CoordLat");
            latitude = coordLat.toString();
            etLatitude.setText(latitude);
        }
        if (bundle.containsKey("CoordLong")) {
            Double coordLong = bundle.getDouble("CoordLong");
            longitude = coordLong.toString();
            etLogintude.setText(longitude);
        }

        if (session.getSupermercadoSelecionado() != null) {
            carregaDados();
        }
    }

    public void cadastroSupermercado(View view) {

        if (validarCampos()) {

            String nome = etSupermercadoNome.getText().toString();
            String telefone = etSupermercadoTelefone.getText().toString();
            Double latitudeD = Double.parseDouble(etLatitude.getText().toString());
            Double longitudeD = Double.parseDouble(etLogintude.getText().toString());
            LatLng latLng = new LatLng(latitudeD, longitudeD);


            if (session.getSupermercadoSelecionado() != null) {
                session.getSupermercadoSelecionado().setNome(nome);
                session.getSupermercadoSelecionado().setTelefone(telefone);
                session.getSupermercadoSelecionado().setCoordenadas(latLng);
                supermercadoNegocio.editar(session.getSupermercadoSelecionado());
            } else {
                supermercadoNegocio.cadastrar(nome, telefone, latLng);
            }
            Intent changeToListaSupermercado = new Intent(CadastroSupermercados.this, ListaSupermercados.class);
            CadastroSupermercados.this.startActivity(changeToListaSupermercado);
            finish();
        }
    }

    private void carregaDados() {
        Double coordLat = session.getSupermercadoSelecionado().getCoordenadas().latitude;
        Double coordLong = session.getSupermercadoSelecionado().getCoordenadas().longitude;
        String coordLatString = coordLat.toString();
        String coordLongString = coordLong.toString();
        etSupermercadoNome.setText(session.getSupermercadoSelecionado().getNome());
        etSupermercadoTelefone.setText(session.getSupermercadoSelecionado().getTelefone());
        etLatitude.setText(coordLatString);
        etLogintude.setText(coordLongString);
    }

    public void changeScreenCadastroSupermercadoToMapa(View view) {
        Intent addCoordenadas = new Intent(CadastroSupermercados.this, TelaMenuActivity.class);
        startActivity(addCoordenadas);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(CadastroSupermercados.this, ListaSupermercados.class);
        startActivity(voltarMenu);
        finish();
    }

//    private void limparCampos(){
//        etSupermercadoNome.setText(stringVazia);
//        etSupermercadoTelefone.setText(stringVazia);
//        etLatitude.setText(stringInicial);
//        etLogintude.setText(stringInicial);
//        etLatitude.requestFocus();
//    }

    private boolean validarCampos(){
        String nomeSupermercado = etSupermercadoNome.getText().toString();
        String telefonesupermercado = etSupermercadoTelefone.getText().toString();
//        String latitudeD = etLatitude.getText().toString();
//        String longitudeD = etLogintude.getText().toString();
        return Validacao.verificaVaziosSupermercado(nomeSupermercado,telefonesupermercado,this,
                etSupermercadoNome,etSupermercadoTelefone,etLatitude,etLogintude);
    }


}