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
import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.UsuarioNegocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private Button btnLogar, btnCadastrar;
    private EditText etEmail, etSenha;
    private Context _context = LoginActivity.this;
    private CriptografiaSenha criptografiaSenha;
    private String senhaCriptografada;
    private CriptografiaSenha criptografia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);

        btnLogar = (Button) findViewById(R.id.btnEntrar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

    }

    public void loginUsuario(View view){

        if (validarCampos()) {
            String email = etEmail.getText().toString().trim();
            String senha = etSenha.getText().toString().trim();

            UsuarioNegocio usuarioNegocio = new UsuarioNegocio(_context);
            criptografia = CriptografiaSenha.getInstancia(senha);
            senhaCriptografada = criptografia.getSenhaCriptografada();
            Usuario logarTest = usuarioNegocio.buscaUsuario(email,senhaCriptografada);

            if (logarTest != null) {
                session.setUsuarioLogado(logarTest);
                if (usuarioNegocio.buscarPessoa(logarTest.getId()) != null) {
                    session.setPessoaLogada(usuarioNegocio.buscarPessoa(logarTest.getId()));
                    Intent changeToTelaPrincipal = new Intent(LoginActivity.this, TelaMenuActivity.class);
                    LoginActivity.this.startActivity(changeToTelaPrincipal);
                    Toast.makeText(this, "Bem-Vindo - " + session.getPessoaLogada().getNome(), Toast.LENGTH_SHORT).show();
                    finish();
                } else{
                    Intent changeToTelaCadastroPessoa = new Intent(LoginActivity.this,CadastroPessoaActivity.class);
                    LoginActivity.this.startActivity(changeToTelaCadastroPessoa);
                    finish();
                }
            } else {
                Toast.makeText(this, "Email ou senha inválido!", Toast.LENGTH_SHORT).show();                }
        }
    }

    

    public void cadastrarUsuario (View view){
        Intent cadastro = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(cadastro);
        finish();
    }

    private boolean validarCampos(){
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();
        return (!verificaVazios(email, senha)  && !semEspaco(email) && tamanhoInválido(email, senha) && validarEmail(email));
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

    public boolean validarEmail(String email) {
        boolean result;
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            result = true;
        } else {
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.email_invalido));
            result = false;
        }
        return result;
    }

}