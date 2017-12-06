package com.ramoieee.wolfbyte.brunapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

/**
 * Created by Sergio on 15/11/2017.
 */


public class ExibicaoActivity extends AppCompatActivity {


    TextView resultPorcentagem;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibicao);

        ProgressBar resultadoProgressBar=(ProgressBar)findViewById(R.id.progressBar);
       // resultPorcentagem=(TextView) findViewById(R.id.porcentagem);
       // resultadoProgressBar.setMax(100);
        resultadoProgressBar.setProgress(80);
        //String Turma = getIntent().getExtras().getString("turma");
       // String aluno = getIntent().getExtras().getString("aluno");

       int progressValue = resultadoProgressBar.getProgress();
        resultPorcentagem.setText("80"+"%");

       /*ptsRef = FirebaseDatabase.getInstance().getReference("pontos");

        ptsRef.child(Turma).child(aluno).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
        progressoAtual = dataSnapshot.getValue("indeterminado".class);
        resultadoProgressBar.setProgress(progressoAtual);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/











    }



}
