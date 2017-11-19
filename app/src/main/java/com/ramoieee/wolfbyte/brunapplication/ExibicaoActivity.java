package com.ramoieee.wolfbyte.brunapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Sergio on 15/11/2017.
 */


public class ExibicaoActivity extends AppCompatActivity {


    TextView resultPorcentagem;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibicao);

        ProgressBar resultadoProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        resultPorcentagem=(TextView) findViewById(R.id.porcentagem);
       // resultadoProgressBar.setMax(100);
        //resultadoProgressBar.setProgress(35);

        int progressValue = resultadoProgressBar.getProgress();
        resultPorcentagem.setText("80%");



    }



}
