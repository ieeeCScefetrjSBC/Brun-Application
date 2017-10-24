package com.ramoieee.wolfbyte.brunapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Sergio on 22/10/2017.
 */

public class ResultadoAlunoActivity extends AppCompatActivity{

    Button button_resultTurma,button_resultIndividual ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultaluno);


        button_resultIndividual = (Button)findViewById(R.id.button_resindividual) ;
        button_resultTurma = (Button) findViewById(R.id.button_resturma);

    }
}
