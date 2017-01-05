package com.mercapp.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.UsuarioNegocio;
import com.mercapp.usuario.negocio.Validacao;

public class CadastroActivity extends AppCompatActivity {

    private EditText etEmail, etSenha, etConfirmar;
    private Context context = CadastroActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etConfirmar = (EditText) findViewById(R.id.etConfirmaSenha);
        Button btnEfetuarCadastro = (Button) findViewById(R.id.btnEfetuarCadastro);
    }

    public void efetuarCadastro(View view) {
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();

        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context);
        Usuario usuarioCadastro = usuarioNegocio.buscar(email);

        if ((usuarioCadastro == null) && (validarCampos())){
            CriptografiaSenha criptografia = CriptografiaSenha.getInstancia(senha);
            String senhaCriptografada = criptografia.getSenhaCriptografada();

            usuarioNegocio.cadastro(email, senhaCriptografada);
            Toast.makeText(this, "Cadastro efetuado com sucesso\r\n Faça o login - "+ email, Toast.LENGTH_SHORT).show();
            voltarLogin(view);
        } else{
            Toast.makeText(this, "Usuário já cadastrado!", Toast.LENGTH_SHORT).show();
        }

    }

    public void voltarLogin(View view) {
        Intent voltarlogin = new Intent(this, LoginActivity.class);
        startActivity(voltarlogin);
        finish();
    }

    private boolean validarCampos(){

        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString();
        String confirmarSenha = etConfirmar.getText().toString();
        return isaBoolean(email, senha, confirmarSenha);
    }

    private boolean isaBoolean(String email, String senha, String confirmarSenha) {
        return !Validacao.verificaVazios(email, senha, this, etEmail,etSenha)
                && !Validacao.semEspaco(email,this,etEmail)
                && Validacao.tamanhoInválido(email, senha, this,etEmail,etSenha)
                && Validacao.validarEmail(email,this,etEmail)
                && Validacao.confirmarSenha(senha, confirmarSenha, this,etConfirmar);
    }

}
