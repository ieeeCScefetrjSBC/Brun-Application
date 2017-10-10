package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity { //Activity para mostrar feedback do quiz que o usuário fez
    Button btnBack;
    TextView rightQuestions;
    TextView wrongQuestions;
    TextView points;
    String intentValores []; // 1ª posição do array recebe qtd de repostas certas e 2ª posição recebe qtd total de perguntas
    int erros, acertos, pontos, p1 = 1, p2 = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        rightQuestions = (TextView) findViewById(R.id.certas);
        wrongQuestions = (TextView) findViewById(R.id.erradas);
        points = (TextView) findViewById(R.id.points);
        btnBack = (Button) findViewById(R.id.button);

        //Parte dos acertos e erros
        intentValores = getIntent().getExtras().getString("Value").split("#"); //Pega a string e divide ela em duas, tirando o #
        acertos = Integer.parseInt(intentValores[0]);
        rightQuestions.setText(rightQuestions.getText() + " " + acertos); //Os acertos vão diretamente para o textView
        erros = Integer.parseInt(intentValores[1]) - Integer.parseInt(intentValores[0]); //Os erros são calculados
        wrongQuestions.setText(wrongQuestions.getText() + " " + erros); //Coloca os erros no textView

        //Parte da pontuação
        pontos = acertos*p1 + erros*p2;
        points.setText(points.getText() + " " + pontos);

        btnBack.setOnClickListener(new View.OnClickListener() { //se o usuário clicar no botão, voltara para a activity de matérias
            @Override
            public void onClick(View v) {
                Intent int_quiz= new Intent(FeedbackActivity.this, SubjectsActivity.class);
                startActivity(int_quiz);
                finish();
            }
        });

    }

}
