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
    private EditText etSupermercadoNome, etSupermercadoTelefone, etSupermercadoBuscaNome, etSupermercadoBuscaTelefone, etLogintude, etLatitude;
    private TextView etSupermercadoTextoNome, etSupermercadoTextoTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_supermercado);
        etSupermercadoNome = (EditText) findViewById(R.id.etSupNome);
        etSupermercadoTelefone = (EditText) findViewById(R.id.etSupTelefone);
        etSupermercadoBuscaNome = (EditText) findViewById(R.id.supBuscaNome);
        etSupermercadoTextoNome = (TextView) findViewById(R.id.supTextoNome);
        etSupermercadoTextoTelefone = (TextView) findViewById(R.id.supTextoTelefone);

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
        /*
        //LatLng coordenadas = session.getCoordenadas();
        Double latitude = coordenadas.latitude;
        String coordLat = latitude.toString();
        Double longitude = coordenadas.longitude;
        String coordLong = latitude.toString();
        */
    }

    public void cadastroDireto(View view) {
        Supermercado supermercado1 = CadastrarSupermercado(1,"Extrabom - UFRPE","1111");
        efetuarCadastroSupermercado(supermercado1);
        Supermercado supermercado2 = CadastrarSupermercado(1,"Extrabom - Dois Irmãos","2222");
        efetuarCadastroSupermercado(supermercado2);
    }
    private Supermercado CadastrarSupermercado(Integer id, String nome, String telefone) {
        Supermercado supermercado = new Supermercado();
        supermercado.setId(id);
        supermercado.setNome(nome);
        supermercado.setTelefone(telefone);
        return supermercado;
    }

    public void efetuarCadastroSupermercado(Supermercado supermercado) {
        String nome = supermercado.getNome();
        String telefone = supermercado.getTelefone();
        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(nome);
        if (supermercadoCadastrado == null) {
            supermercadoNegocio.cadastroSupermercado(nome, telefone);
            Toast.makeText(this, "Supermercado " + nome + " cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Supermercado já exitente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarSupermercadoManual(View view) {
        String nome = etSupermercadoNome.getText().toString().trim();
        String telefone = etSupermercadoTelefone.getText().toString().trim();
        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(nome);
        if (supermercadoCadastrado == null) {
            supermercadoNegocio.cadastroSupermercado(nome, telefone);
            Toast.makeText(this, "Supermercado " + nome + " cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Supermercado já existente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscaSupermercadoBD(View view) {
        String buscaNome = etSupermercadoBuscaNome.getText().toString().trim();
        supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoCadastrado = supermercadoNegocio.buscaSupermercado(buscaNome);
        if (supermercadoCadastrado != null) {
            etSupermercadoTextoNome.setText(supermercadoCadastrado.getNome());
            etSupermercadoTextoTelefone.setText(supermercadoCadastrado.getTelefone());
        } else {
            Toast.makeText(this, "Supermercado não cadastrado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeTelaCadatroSupermercadosToTelaAdministrador(View view) {
        Intent voltarMenu = new Intent(CadastroSupermercados.this, Administrador.class);
        startActivity(voltarMenu);
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

    private TextView etNomeSupermercado, etFoneSupermercado;
    private Session session = Session.getInstanciaSessao();
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
 */