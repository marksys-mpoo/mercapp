package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.Mask;
import com.mercapp.usuario.gui.TelaMenuActivity;
import com.mercapp.usuario.negocio.Validacao;

public class CadastroSupermercadosActivity extends AppCompatActivity {

    private static final String stringVazia = "";


    private Context _context = CadastroSupermercadosActivity.this;
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
        justificarTextoAjuda();

        etSupermercadoNome = (EditText) findViewById(R.id.etSupNome);
        etSupermercadoTelefone = (EditText) findViewById(R.id.etSupTelefone);
        etSupermercadoTelefone.addTextChangedListener(Mask.insert(Mask.MaskType.TEL, etSupermercadoTelefone));

        etLatitude = (EditText) findViewById(R.id.etSupLat);
        etLogintude = (EditText) findViewById(R.id.etSupLong);
        btnSalvarEditar = (Button) findViewById(R.id.btnCadastrarSupermercado);
        etSupermercadoNome.requestFocus();

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

    public void justificarTextoAjuda() {
        WebView view = (WebView) findViewById(R.id.ajudaMapa);
        String text;
        text = "<html><body><p align=\"justify\">";
        text+= "Pressionar o local desejado no mapa por mais de" +
                " 1 segundo, para conseguir a " +
                "localização do supermercado que deseja cadastrar.";
        text+= "</p></body></html>";
        view.loadData(text,"text/html;charset=UTF-8",null);
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
            Intent changeToListaSupermercado = new Intent(CadastroSupermercadosActivity.this, ListaSupermercadosActivity.class);
            CadastroSupermercadosActivity.this.startActivity(changeToListaSupermercado);
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
        Intent addCoordenadas = new Intent(CadastroSupermercadosActivity.this, TelaMenuActivity.class);
        startActivity(addCoordenadas);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(CadastroSupermercadosActivity.this, ListaSupermercadosActivity.class);
        startActivity(voltarMenu);
        finish();
    }


    private boolean validarCampos(){
        String nomeSupermercado = etSupermercadoNome.getText().toString();
        String apenasNumerotelefone = Mask.unmask(etSupermercadoTelefone.getText().toString().trim());
        String telefonesupermercado = apenasNumerotelefone;
        return Validacao.verificaVaziosSupermercado(nomeSupermercado,telefonesupermercado,this,
                etSupermercadoNome,etSupermercadoTelefone,etLatitude,etLogintude);
    }


}