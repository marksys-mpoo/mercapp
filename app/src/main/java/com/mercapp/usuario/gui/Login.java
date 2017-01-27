package com.mercapp.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.PessoaNegocio;
import com.mercapp.usuario.negocio.UsuarioNegocio;
import com.mercapp.usuario.negocio.Validacao;


public class Login extends AppCompatActivity {

    private Session session = Session.getInstanciaSessao();
    private EditText etEmail;
    private EditText etSenha;
    private Context context = Login.this;
    private CriptografiaSenha criptografiaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etEmail.requestFocus();

        TextView recuperaSenha = (TextView) findViewById(R.id.tvRecuperarSenha);
        recuperaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RecuperarSenha.class);
                Login.this.startActivity(intent);
                finish();
            }
        });

    }

    public void loginUsuario(View view){

        if (validarCampos()) {
            String email = etEmail.getText().toString().trim();
            String senha = etSenha.getText().toString().trim();

            UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context);

            CriptografiaSenha criptografia =  criptografiaSenha.getInstancia(senha);
            String senhaCriptografada = criptografia.getSenhaCriptografada();
            Usuario logarTest = usuarioNegocio.buscar(email,senhaCriptografada);

            if (logarTest != null) {
                session.setUsuarioLogado(logarTest);
                PessoaNegocio pessoaNegocio = new PessoaNegocio(context);
                if (pessoaNegocio.buscar(logarTest.getId()) != null) {
                    session.setPessoaLogada(pessoaNegocio.buscar(logarTest.getId()));
                    Intent changeToTelaPrincipal = new Intent(Login.this, TelaMenu.class);
                    Login.this.startActivity(changeToTelaPrincipal);
                    Toast.makeText(this, "Bem-Vindo - " + session.getPessoaLogada().getNome(), Toast.LENGTH_SHORT).show();
                    finish();
                } else{
                    Intent changeToTelaCadastroPessoa = new Intent(Login.this,CadastroPessoa.class);
                    Login.this.startActivity(changeToTelaCadastroPessoa);
                    finish();
                }
            } else {
                Toast.makeText(this, R.string.email_senha_invalido, Toast.LENGTH_SHORT).show();                }
        }
    }

    

    public void cadastrarUsuario (View view){
        Intent cadastro = new Intent(Login.this, Cadastro.class);
        startActivity(cadastro);
        finish();
    }

    private boolean validarCampos(){
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();
        return (!Validacao.verificaVazios(email, senha,this, etEmail, etSenha)
                && !Validacao.semEspaco(email, this, etEmail)
                && Validacao.tamanhoInválido(email, senha, this, etEmail,etSenha)
                && Validacao.validarEmail(email,this, etEmail));
    }

}