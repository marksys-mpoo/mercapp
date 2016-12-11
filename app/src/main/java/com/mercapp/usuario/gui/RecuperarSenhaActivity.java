package com.mercapp.usuario.gui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.UsuarioNegocio;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarSenhaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private Button recuperarSenha;
    private UsuarioNegocio usuarioNegocio;
    private Usuario usuario;
    javax.mail.Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String rec, msg, sub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        context = this;
        recuperarSenha = (Button) findViewById(R.id.btnRecuperarSenha);
        email = (EditText) findViewById(R.id.edtEmailRecuperarSenha);

        recuperarSenha.setOnClickListener(this);

        sub = "Recuperação de Senha";

    }

    @Override
    public void onClick(View v) {
        rec = email.getText().toString();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = javax.mail.Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("marksys.mercapp@gmail.com", "mercapp2016");
            }
        });

        pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try{
                usuarioNegocio = new UsuarioNegocio(context);
                /*
                usuario = usuarioNegocio.buscar(rec);
                */
                Usuario usuario = new Usuario();
                usuario.setEmail(rec);
                Usuario usuarioCadastrado = usuarioNegocio.buscar(usuario);
                /*
                msg = usuario.getSenha();
                */
                msg = usuarioCadastrado.getSenha();
                if (msg != null) {
                    Toast.makeText(getApplicationContext(), "Enviando email para "+ usuario.getEmail(), Toast.LENGTH_SHORT).show();

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("marksys.mercapp@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("peticormei@gmail.com"));
                    message.setSubject(sub);
                    message.setContent(msg, "text/html; charset=utf-8");
                    Transport.send(message);
                }
           } catch(MessagingException e) {
               e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}