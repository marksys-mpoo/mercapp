package com.mercapp.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.mercapp.R;

public class SplashActivity extends AppCompatActivity {
    private static int TEMPO_SPLASH = 5000;

    protected boolean mbActive;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar)findViewById(R.id.barraProgresso);
        final Thread timer = new Thread(){
            public void run(){
                mbActive = true;
                try {
                    int tempPassado = 0;
                    while(mbActive && (tempPassado < TEMPO_SPLASH )){
                        // enquanto o tempoPassado for menor que 5s
                        // soma 2 milisegundos
                        sleep(200);
                        if(mbActive) {
                            tempPassado += 500;
                            updateProgress(tempPassado);
                        }
                    }
                } catch (InterruptedException e) {
                  //// ERRO se houver
                }
                finally {
                    //após terminar o try ele chama a tela login. Se não houver erro.
                   Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }

    private void updateProgress(int tempPassado) {
        if (progressBar != null){
            final int progress = progressBar.getMax() * tempPassado /TEMPO_SPLASH;
            progressBar.setProgress(progress);
        }
    }

}
