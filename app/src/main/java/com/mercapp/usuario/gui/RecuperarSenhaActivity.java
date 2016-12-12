package com.mercapp.usuario.gui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercapp.R;
import com.mercapp.usuario.dominio.Usuario;
import com.mercapp.usuario.negocio.UsuarioNegocio;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class RecuperarSenhaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private Button recuperarSenha;
    private String correo, contrasenha;
    Session session;
    private UsuarioNegocio usuarioNegocio;
    private Usuario usuario;
    ProgressDialog pdialog = null;
    Context context = this;
    String rec, msg, sub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        email = (EditText) findViewById(R.id.edtEmailRecuperarSenha);
        correo = "marksys.mercapp@gmail.com";
        contrasenha = "mercapp2016";
        sub = "Recuperação de Senha";

//        context = this;
        recuperarSenha = (Button) findViewById(R.id.btnRecuperarSenha);
        recuperarSenha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

        pdialog = ProgressDialog.show(context, "", "Aguarde... ", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try{
                usuarioNegocio = new UsuarioNegocio(context);
                usuario = usuarioNegocio.buscar(rec);
                msg = usuario.getSenha();
                if (msg != null) {
//                   Toast.makeText(getApplicationContext(), "Enviando email para "+ usuario.getEmail(), Toast.LENGTH_SHORT).show();
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("marksys.mercapp@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("wellingtonluiz123456@gmail.com"));
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
            Toast.makeText(getApplicationContext(), "Message enviada com sucesso.", Toast.LENGTH_LONG).show();
        }
    }
}