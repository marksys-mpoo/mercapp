package com.mercapp.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.UsuarioNegocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {

    private EditText etEmail, etSenha, etConfirmar;
    private Button btnEfetuarCadastro;
    private Context _context = CadastroActivity.this;
    private UsuarioNegocio usuarioNegocio;
    private Usuario usuarioCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etConfirmar = (EditText) findViewById(R.id.etConfirmaSenha);
        btnEfetuarCadastro = (Button) findViewById(R.id.btnEfetuarCadastro);

    }

    public void efetuarCadastro(View view) {
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();

        usuarioNegocio = new UsuarioNegocio(_context);
        usuarioCadastro = usuarioNegocio.buscaUsuario(email,senha);

        if ((usuarioCadastro == null) && (validarCampos())){
            usuarioNegocio.cadastro(email, senha);
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
        return !verificaVazios(email, senha)  && !semEspaco(email) && tamanhoInválido(email, senha)
                && validarEmail(email) && confirmarSenha(senha, confirmarSenha);
    }


    private boolean verificaVazios(String email, String senha) {

        boolean result;

        if (TextUtils.isEmpty(email)) {
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.email_vazio));
            result = true;
        } else if (TextUtils.isEmpty(senha)) {
            etSenha.requestFocus();
            etSenha.setError(getString(R.string.senha_vazio));
            result = true;
        }
        result = false;

        return result;
    }

    private boolean tamanhoInválido(String email, String senha) {

        boolean result;

        if (!(email.length() > 3)) {
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.login_tamanho_invalido));
            result = false;
        } else if (!(senha.length() > 2)){
            etSenha.requestFocus();
            etSenha.setError(getString(R.string.login_senha_tamanho_invalido));
            result = false;
        }
        result = true;

        return result;
    }

    private boolean semEspaco(String email) {
        boolean result;
        int idx = email.indexOf(" ");

        if (idx != -1){
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.email_senha_invalido));
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    public boolean validarEmail(String email)
    {
        boolean result;
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches()) {
            result = true;
        }else {
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.email_invalido));
            result = false;
        }
        return result;
    }

    public boolean confirmarSenha(String senha, String confirmarSenha){

        boolean result;

        if(senha.equals(confirmarSenha)){
            result = true;
        }else{
            etConfirmar.requestFocus();
            etConfirmar.setError(getString(R.string.senha_diferentes));
            result = false;
        }
        return result;
    }


}
