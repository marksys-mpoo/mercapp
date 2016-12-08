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

    private Context _context = CadastroSupermercados.this;    private Session session = Session.getInstanciaSessao();
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

    public void cadastroDireto(View view) {
        LatLng ufrpeExtrabom = new LatLng(-8.017877, -34.944440);
        Supermercado supermercado1 = CriarSupermercado("Extrabom - UFRPE","1111", ufrpeExtrabom);
        efetuarCadastroSupermercado(supermercado1);
        LatLng doisIrmaosExtrabom = new LatLng(-8.021448, -34.933130);
        Supermercado supermercado2 = CriarSupermercado("Extrabom - Dois Irmãos","2222", doisIrmaosExtrabom);
        efetuarCadastroSupermercado(supermercado2);
        LatLng parnamirimExtrabom = new LatLng(-8.027122, -34.9170917);
        Supermercado supermercado3 = CriarSupermercado("Extrabom - Parnamirim","3333", parnamirimExtrabom);
        efetuarCadastroSupermercado(supermercado3);

    }
    private Supermercado CriarSupermercado(String nome, String telefone, LatLng coordenadas) {
        Supermercado supermercado = new Supermercado();
        supermercado.setNome(nome);
        supermercado.setTelefone(telefone);
        supermercado.setCoordenadas(coordenadas);
        return supermercado;
    }

    public void efetuarCadastroSupermercado(Supermercado supermercado) {
        Integer id = supermercado.getId();
        String nome = supermercado.getNome();
        String telefone = supermercado.getTelefone();
        LatLng coordenadas = supermercado.getCoordenadas();
        supermercadoNegocio = new SupermercadoNegocio(_context);
        this.supermercado = supermercadoNegocio.buscaSupermercado(nome);
        if (this.supermercado == null) {
            supermercadoNegocio.cadastrarAtualizar(id, textBotaoFuncao, nome, telefone, coordenadas);
            Toast.makeText(this, "Supermercado " + nome + " cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Supermercado já exitente!", Toast.LENGTH_SHORT).show();
        }
    }
    public void teste999(View view) {
        Toast.makeText(this, "Supermercado " + textBotaoFuncao + " Teste!", Toast.LENGTH_SHORT).show();
    }


    public void cadastrarAtualizar(View view) {
        String nome = etSupermercadoNome.getText().toString().trim();
        String telefone = etSupermercadoTelefone.getText().toString().trim();
        Double latitude = Double.parseDouble(etLatitude.getText().toString().trim());
        Double longitude = Double.parseDouble(etLogintude.getText().toString().trim());
        Integer id = 0;

        if (textBotaoFuncao == "salvar") {
            id = 0; // Não vai ser usado pois no cadastro Id no banco é auto incrementado.
            //Toast.makeText(this, "Supermstente! salvar", Toast.LENGTH_SHORT).show();
            if(validarCamposDouble() && validarCampos()) {
                LatLng coordenadas = new LatLng(latitude, longitude);
                supermercadoNegocio = new SupermercadoNegocio(_context);
                supermercado = supermercadoNegocio.buscaSupermercado(nome);
                if (supermercado == null) {
                    supermercadoNegocio.cadastrarAtualizar(id, textBotaoFuncao, nome, telefone, coordenadas);
                    //limparCampos();
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