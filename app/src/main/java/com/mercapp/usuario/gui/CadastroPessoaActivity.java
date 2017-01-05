package com.mercapp.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.negocio.PessoaNegocio;
import com.mercapp.usuario.negocio.Validacao;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText etNome, etTelefone, etNumeroCartao;
    private Session session = Session.getInstanciaSessao();
    private Context context = CadastroPessoaActivity.this;
    private boolean validaCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        etNome = (EditText) findViewById(R.id.etNome);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etTelefone.addTextChangedListener(Mascara.insert(Mascara.MaskType.CELL, etTelefone));
        etNumeroCartao = (EditText) findViewById(R.id.etNumeroCartao);
        Button btnCadastroPessoa = (Button) findViewById(R.id.btnCadastroPessoa);


        if (session.getPessoaLogada() != null) {
            defineText(session.getPessoaLogada());
        }
    }

    public void defineText(Pessoa pessoa) {
        etNome.setText(pessoa.getNome());
        etTelefone.setText(pessoa.getTelefone());
        etNumeroCartao.setText(pessoa.getNumeroCartao());
    }

    public void cadastroPessoa(View view) {

        String nome = etNome.getText().toString().trim();
        String apenasNumerotelefone = Mascara.unmask(etTelefone.getText().toString().trim());
        String telefone = apenasNumerotelefone;
        String numeroCartao = etNumeroCartao.getText().toString().trim();
        int encontraTipoCartao = Validacao.getCardID(numeroCartao);
        if (encontraTipoCartao != -1) {
            try {
                validaCartao = Validacao.validCC(numeroCartao);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (validarCamposPessoa() && validaCartao) {
            PessoaNegocio pessoaNegocio = new PessoaNegocio(context);
            if (session.getPessoaLogada() != null) {
                session.getPessoaLogada().setNome(nome);
                session.getPessoaLogada().setTelefone(telefone);
                session.getPessoaLogada().setNumeroCartao(numeroCartao);
                pessoaNegocio.editar(session.getPessoaLogada());
                Toast.makeText(this, "Alterações Salvas " + session.getPessoaLogada().getNome(), Toast.LENGTH_SHORT).show();
                Intent changeToTelaPrincipal = new Intent(CadastroPessoaActivity.this, TelaMenuActivity.class);
                CadastroPessoaActivity.this.startActivity(changeToTelaPrincipal);
                finish();
            } else {
                pessoaNegocio.cadastro(nome, telefone, numeroCartao);
                Pessoa pessoa = pessoaNegocio.buscar(numeroCartao);
                session.setPessoaLogada(pessoa);
                Intent changeToTelaPrincipal = new Intent(CadastroPessoaActivity.this, TelaMenuActivity.class);
                CadastroPessoaActivity.this.startActivity(changeToTelaPrincipal);
                Toast.makeText(this, "Bem-Vindo - " + nome + "\nCompre com o seu cartão." + Validacao.getCardName(Validacao.getCardID(numeroCartao)), Toast.LENGTH_SHORT).show();
                finish();


            }
        } else {
            Toast.makeText(this, "Existem campos vazios ou o cartão é inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCamposPessoa() {
        String nome = etNome.getText().toString().trim();
        String telefone = etTelefone.getText().toString().trim();
        String numeroCartao = etNumeroCartao.getText().toString().trim();

        return !Validacao.verificaVaziosPessoa(nome, telefone, numeroCartao,
                this, etNome, etTelefone, etNumeroCartao);
    }

    @Override
    public void onBackPressed() {
        Intent voltarMapa = new Intent(getApplication(), TelaMenuActivity.class);
        startActivity(voltarMapa);
        finish();
    }

}


