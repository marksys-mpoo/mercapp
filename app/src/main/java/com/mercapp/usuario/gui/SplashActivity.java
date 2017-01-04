package com.mercapp.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.mercapp.R;

public class SplashActivity extends AppCompatActivity {
    private static final int TEMPO_SPLASH = 1000;
    private boolean mbActive;
    private ProgressBar progressBar;

    public boolean isMbActive() {
        return mbActive;
    }

    public void setMbActive(boolean mbActive) {
        this.mbActive = mbActive;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setProgressBar((ProgressBar)findViewById(R.id.barraProgresso));
        final Thread timer = new Thread(){
            @Override
            public void run(){
                setMbActive(true);
                try {
                    int tempPassado = 0;
                    while(isMbActive() && (tempPassado < TEMPO_SPLASH )){
                        // enquanto o tempoPassado for menor que 1s
                        // Soma 100 milisegundos
                        sleep(100);
                        if(isMbActive()) {
                            tempPassado += 100;
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
        if (getProgressBar() != null){
            final int progress = getProgressBar().getMax() * tempPassado /TEMPO_SPLASH;
            getProgressBar().setProgress(progress);
        }
    }

}
