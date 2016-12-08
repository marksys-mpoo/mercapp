package com.mercapp.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Pessoa;
import com.mercapp.usuario.negocio.PessoaNegocio;
import com.mercapp.usuario.negocio.UsuarioNegocio;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText etNome, etTelefone, etNumeroCartao;
    private Button btnCadastroPessoa;
    private Session session = Session.getInstanciaSessao();
    private Context _context = CadastroPessoaActivity.this;
    private PessoaNegocio pessoaNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        etNome = (EditText) findViewById(R.id.etNome);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etNumeroCartao = (EditText) findViewById(R.id.etNumeroCartao);
        btnCadastroPessoa = (Button) findViewById(R.id.btnCadastroPessoa);

        if (session.getPessoaLogada() != null){
            defineText(session.getPessoaLogada());
        }
    }

    public void defineText(Pessoa pessoa){
        etNome.setText(pessoa.getNome());
        etTelefone.setText(pessoa.getTelefone());
        etNumeroCartao.setText(pessoa.getNumeroCartao());
    }

    public void cadastroPessoa(View view){

        String nome = etNome.getText().toString().trim();
        String telefone = etTelefone.getText().toString().trim();
        String numeroCartao = etNumeroCartao.getText().toString().trim();

        if (validarCamposPessoa()){
            pessoaNegocio = new PessoaNegocio(_context);
            if (session.getPessoaLogada() != null){
                pessoaNegocio.editarPessoa(session.getPessoaLogada());
                Toast.makeText(this, "Alterações Salvas", Toast.LENGTH_SHORT).show();
                finish();
            } else {
            pessoaNegocio.cadastroPessoa(nome, telefone, numeroCartao);
            Pessoa pessoa = pessoaNegocio.buscarPessoa(numeroCartao);
            session.setPessoaLogada(pessoa);
            Intent changeToTelaPrincipal = new Intent(CadastroPessoaActivity.this, TelaMenuActivity.class);
            CadastroPessoaActivity.this.startActivity(changeToTelaPrincipal);
            Toast.makeText(this, "Bem-Vindo - "+ nome, Toast.LENGTH_SHORT).show();
            finish();
            }
        }else {
            Toast.makeText(this, "Existem campos vazios", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCamposPessoa(){
        String nome = etNome.getText().toString().trim();
        String telefone = etTelefone.getText().toString().trim();
        String numeroCartao = etNumeroCartao.getText().toString().trim();
        return (!verificaVaziosPessoa(nome, telefone, numeroCartao));
    }

    private boolean verificaVaziosPessoa(String nome, String telefone, String numeroCartao){
        boolean result;
        if (TextUtils.isEmpty(nome)) {
            etNome.requestFocus();
            etNome.setError(getString(R.string.campo_vazio));
            result = true;
        } else if (TextUtils.isEmpty(telefone)) {
            etTelefone.requestFocus();
            etTelefone.setError(getString(R.string.campo_vazio));
            result = true;
        } else if(TextUtils.isEmpty(numeroCartao)) {
            etNumeroCartao.requestFocus();
            etNumeroCartao.setError(getString(R.string.campo_vazio));
            result = true;
        }
        result = false;
        return result;
    }
}
