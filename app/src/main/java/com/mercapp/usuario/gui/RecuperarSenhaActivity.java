package com.mercapp.usuario.gui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarSenhaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private Button recuperarSenha;
    private String correo, contrasenha;
    private Session session;
    private UsuarioNegocio usuarioNegocio;
    private Usuario usuario;
    private ProgressDialog pdialog = null;
    private Context context = this;
    private String rec;
    private String msg;
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Context getContext() {
        return context;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProgressDialog getPdialog() {
        return pdialog;
    }

    public void setPdialog(ProgressDialog pdialog) {
        this.pdialog = pdialog;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        email = (EditText) findViewById(R.id.edtEmailRecuperarSenha);
        correo = "marksys.mercapp@gmail.com";
        contrasenha = "mercapp2016";
        setSub("Recuperação de Senha");

        recuperarSenha = (Button) findViewById(R.id.btnRecuperarSenha);
        recuperarSenha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setRec(email.getText().toString());

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        setSession(javax.mail.Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("marksys.mercapp@gmail.com", "mercapp2016");
            }
        }));

        setPdialog(ProgressDialog.show(getContext(), "", "Aguarde... ", true));

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try{
                usuarioNegocio = new UsuarioNegocio(getContext());
                usuario = usuarioNegocio.buscar(getRec());
                setMsg(usuario.getSenha());
                if (getMsg() != null) {
                    Message message = new MimeMessage(getSession());
                    message.setFrom(new InternetAddress("marksys.mercapp@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("wellingtonluiz123456@gmail.com"));
                    message.setSubject(getSub());
                    message.setContent(getMsg(), "text/html; charset=utf-8");
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
            getPdialog().dismiss();
            Toast.makeText(getApplicationContext(), "Message enviada com sucesso.", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        Intent voltarLogin = new Intent(getApplication(), LoginActivity.class);
        startActivity(voltarLogin);
        finish();
    }


}