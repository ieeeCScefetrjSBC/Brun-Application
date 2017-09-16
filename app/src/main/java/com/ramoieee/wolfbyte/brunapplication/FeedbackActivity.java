package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity { //Activity para mostrar feedback do quiz que o usuário fez
    Button btnBack;
    TextView right;
    TextView wrong;
    String intentValores []; // array para colocar na primeira posição quantas acertou e na segunda o número total de questões
    int erros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        right = (TextView) findViewById(R.id.textView2);
        wrong = (TextView) findViewById(R.id.textView3);
        btnBack = (Button) findViewById(R.id.button);
        intentValores = getIntent().getExtras().getString("Value").split("#"); //Pega a string e divide ela em duas, tirando o #
        right.setText(right.getText() + " " + intentValores[0]); //Os acertos vão diretamente para o textView
        erros = Integer.parseInt(intentValores[1]) - Integer.parseInt(intentValores[0]); //Os erros são calculados
        wrong.setText(wrong.getText() + " " + erros); //Coloca os erros no textView

        btnBack.setOnClickListener(new View.OnClickListener() { //se o usuário clicar no botão, voltara para a activity de matérias
            @Override
            public void onClick(View v) {
                Intent int_quiz= new Intent(FeedbackActivity.this, SubjectsActivity.class);
                startActivity(int_quiz);
            }
        });

    }

}
