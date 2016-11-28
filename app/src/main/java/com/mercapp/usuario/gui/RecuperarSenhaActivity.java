package com.mercapp.usuario.gui;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.mercapp.R;
import com.mercapp.infra.Session;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.UsuarioNegocio;



public class RecuperarSenhaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private Button recuperarSenha;
    private UsuarioNegocio usuarioNegocio;
    private Usuario usuario;

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText reciep, sub, msg;
    String rec, subject, textMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        context = this;
        recuperarSenha = (Button) findViewById(R.id.btnRecuperarSenha);
        email = (EditText) findViewById(R.id.edtEmailRecuperarSenha);

        recuperarSenha.setOnClickListener(this);

//        recuperarSenha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                usuarioNegocio =  new UsuarioNegocio(context);
//                usuario =  usuarioNegocio.buscaUsuario(email.getText().toString());
//                if(usuarioNegocio == null){
//                    Toast.makeText(context,"Email não existe!", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    recuperarSenha(usuario);
//                }
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {

    }


//    public void recuperarSenha(Usuario usuario){
//
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Sua nova Senha");
//        intent.putExtra(Intent.EXTRA_TEXT,usuario.getSenha().toString());
//        try {
//            startActivity(intent.createChooser(intent, "Enviando o email..."));
//        }catch(Exception e){
//            //
//        }
//    }



}
