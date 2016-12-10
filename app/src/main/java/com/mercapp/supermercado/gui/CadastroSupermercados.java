package com.mercapp.supermercado.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class CadastroSupermercados extends AppCompatActivity {

    private static final String stringVazia = "";
    private static final String stringInicial ="0.0";
    private static final String textBotaoFuncaoEditar ="atualizar";
    private static final String textBotaoFuncaoCadastrar ="salvar";
    private String textBotaoFuncao;
    private String nomeSupermercado, telefoneSupermercado;

    private Context _context = CadastroSupermercados.this;
    private Session session = Session.getInstanciaSessao();
    private SupermercadoNegocio supermercadoNegocio;
    private Supermercado supermercado, supermercadoEditado;
    private EditText etSupermercadoNome, etSupermercadoTelefone, etLogintude, etLatitude;
    private Button btnSalvarEditar;
    private String funcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_supermercado);

        etSupermercadoNome = (EditText) findViewById(R.id.etSupNome);
        etSupermercadoTelefone = (EditText) findViewById(R.id.etSupTelefone);
        etLatitude = (EditText) findViewById(R.id.etSupLat);
        etLogintude = (EditText) findViewById(R.id.etSupLong);
        btnSalvarEditar = (Button) findViewById(R.id.btnCadastrarSupermercado);

        funcao = session.getFuncaoCrudSupermercado().toString();

        if (funcao == "alterar") {
            nomeSupermercado = session.getSupermercadoSelecionado().getNome().toString();
            telefoneSupermercado = session.getSupermercadoSelecionado().getTelefone().toString();
            Double coordLat = session.getSupermercadoSelecionado().getCoordenadas().latitude;
            Double coordLong = session.getSupermercadoSelecionado().getCoordenadas().longitude;
            String coordLatString = coordLat.toString();
            String coordLongString = coordLong.toString();
            etSupermercadoNome.setText(nomeSupermercado);
            etSupermercadoTelefone.setText(telefoneSupermercado);
            etLatitude.setText(coordLatString);
            etLogintude.setText(coordLongString);
            textBotaoFuncao = textBotaoFuncaoEditar;
        }

        if (funcao == "cadastrar") {
            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey("CoordLat")) {
                Double coordLat = bundle.getDouble("CoordLat");
                String coordLatString = coordLat.toString();
                etLatitude.setText(coordLatString);
            }
            if (bundle.containsKey("CoordLong")) {
                Double coordLong = bundle.getDouble("CoordLong");
                String coordLongString = coordLong.toString();
                etLogintude.setText(coordLongString);
            }
            nomeSupermercado = "";
            telefoneSupermercado = "";
            textBotaoFuncao = textBotaoFuncaoCadastrar;
        }

        if (funcao == "editar") {

            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey("CoordLat")) {
                Double coordLat = bundle.getDouble("CoordLat");
                String coordLatString = coordLat.toString();
                etLatitude.setText(coordLatString);
            }

            if (bundle.containsKey("CoordLong")) {
                Double coordLong = bundle.getDouble("CoordLong");
                String coordLongString = coordLong.toString();
                etLogintude.setText(coordLongString);
            }

            nomeSupermercado = session.getSupermercadoSelecionado().getNome().toString();
            telefoneSupermercado = session.getSupermercadoSelecionado().getTelefone().toString();
            etSupermercadoNome.setText(nomeSupermercado);
            etSupermercadoTelefone.setText(telefoneSupermercado);
            textBotaoFuncao = session.getTextButaoFuncaoSupermercado().toString();;
        }

        btnSalvarEditar.setText(textBotaoFuncao);
    }

    public void cadastrarAtualizar(View view) {
        String nome = etSupermercadoNome.getText().toString().trim();
        String telefone = etSupermercadoTelefone.getText().toString().trim();
        Double latitude = Double.parseDouble(etLatitude.getText().toString().trim());
        Double longitude = Double.parseDouble(etLogintude.getText().toString().trim());
        Integer id = 0;

        if (textBotaoFuncao == "salvar") {
            id = 0; // Não vai ser usado pois no cadastro Id no banco é auto incrementado.
            if(validarCamposDouble() && validarCampos()) {
                LatLng coordenadas = new LatLng(latitude, longitude);
                supermercadoNegocio = new SupermercadoNegocio(_context);
                supermercado = supermercadoNegocio.buscaSupermercado(nome);
                if (supermercado == null) {
                    supermercadoNegocio.cadastrarAtualizar(id, textBotaoFuncao, nome, telefone, coordenadas);
                    Toast.makeText(this, "Supermercado " + nome + " cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(this, "Supermercado já existente!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (textBotaoFuncao == "atualizar") {
            id = session.getSupermercadoSelecionado().getId();
            if(validarCamposDouble() && validarCampos()) {
                LatLng coordenadas = new LatLng(latitude, longitude);
                supermercadoNegocio = new SupermercadoNegocio(_context);
                supermercado = supermercadoNegocio.buscaSupermercado(nome);
                supermercadoNegocio.cadastrarAtualizar(id, textBotaoFuncao, nome, telefone, coordenadas);
                Toast.makeText(this, "Supermercado " + nome + " atualizado.", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }
    }

    public void changeScreenCadastroSupermercadoToMapa(View view) {
        nomeSupermercado = etSupermercadoNome.getText().toString().trim();
        telefoneSupermercado = etSupermercadoTelefone.getText().toString().trim();
        Supermercado supermercadoEditado = new Supermercado();
        supermercadoEditado.setNome(nomeSupermercado);
        supermercadoEditado.setTelefone(telefoneSupermercado);
        if (textBotaoFuncao == "salvar") {
            supermercadoEditado.setId(0);
        } else if (textBotaoFuncao == "atualizar") {
            Integer idSupermercadoSessao = session.getSupermercadoSelecionado().getId();
            supermercadoEditado.setId(idSupermercadoSessao);
        }
        SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(_context);
        supermercadoNegocio.iniciarSessaoSupermercado(supermercadoEditado);
        supermercadoNegocio.iniciarSessaoFuncaoCrud("editar");
        supermercadoNegocio.iniciarSessaotextButaoFuncaoCrud(textBotaoFuncao);
        Intent addCoordenadas = new Intent(CadastroSupermercados.this, TelaMenuActivity.class);
        startActivity(addCoordenadas);
        finish();
    }

    private  boolean validarCamposDouble(){
        boolean result;
        if (etLatitude.getText().toString().equals(stringInicial)) {
            etLatitude.requestFocus();
            etLatitude.setError(getString(R.string.latitude_vazio_tela_cadastro_produtos));
            result = false;
        }else if(etLogintude.getText().toString().equals(stringInicial)) {
            etLogintude.requestFocus();
            etLogintude.setError(getString(R.string.longitude_vazio_tela_cadastro_produtos));
            result = false;
        }else{
            result = true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        Intent voltarMenu = new Intent(CadastroSupermercados.this, ListaSupermercados.class);
        startActivity(voltarMenu);
        finish();
    }

    private void limparCampos(){
        etSupermercadoNome.setText(stringVazia);
        etSupermercadoTelefone.setText(stringVazia);
        etLatitude.setText(stringInicial);
        etLogintude.setText(stringInicial);
        etLatitude.requestFocus();
    }

    private boolean validarCampos(){
        String nomeSupermercado = etSupermercadoNome.getText().toString();
        String telefonesupermercado = etSupermercadoTelefone.getText().toString();
        return verificaVazios(nomeSupermercado, telefonesupermercado);
    }

    private boolean verificaVazios(String nomeSupermercado, String telefonesupermercado) {
        boolean result;
        if (TextUtils.isEmpty(nomeSupermercado)) {
            etSupermercadoNome.requestFocus();
            etSupermercadoNome.setError(getString(R.string.nome_vazio_tela_cadastro_supermrecados));
            result = false;
        } else if (TextUtils.isEmpty(telefonesupermercado)) {
            etSupermercadoTelefone.requestFocus();
            etSupermercadoTelefone.setError(getString(R.string.telefone_vazio_tela_cadastro_supermrecados));
            result = false;
        }else {
            result = true;
        }
        return result;
    }
}