package com.ramoieee.wolfbyte.brunapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private Button button_altA, button_altB, button_altC, button_altD;
    private TextView text_enunciado;

    String enunciado, altA, altB, altC, altD, gabarito;
    int answerCount;

    DatabaseReference questionRef;
    ArrayList arrayChildren = new ArrayList(); //Array para colocar todas as perguntas e suas alternativas/gabarito
    Long numberOfChildren; //Váriavel pra colocar quantas questões tem
    int ind = 0; //Váriavel para saber a pergunta de qual indice puxar do array

    public void ExibirPergunta(int index){
        if(index < numberOfChildren) {
            Questions teste = (Questions) arrayChildren.get(index);
            enunciado = teste.enunciado;
            altA = teste.altA;
            altB = teste.altB;
            altC = teste.altC;
            altD = teste.altD;
            gabarito = teste.gabarito;
            text_enunciado.setText(enunciado);
            button_altA.setText(altA);
            button_altB.setText(altB);
            button_altC.setText(altC);
            button_altD.setText(altD);
        }
        else{ //Se o index for igual ao número de perguntas, é porque chegou ao final e passa pra activity das matérias
            Intent int_quiz_example = new Intent(QuizActivity.this, SubjectsActivity.class);
            startActivity(int_quiz_example);
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button_altA = (Button)findViewById(R.id.button_altA);
        button_altB = (Button)findViewById(R.id.button_altB);
        button_altC = (Button)findViewById(R.id.button_altC);
        button_altD = (Button)findViewById(R.id.button_altD);

        text_enunciado = (TextView)findViewById(R.id.text_enunciado);

        questionRef = FirebaseDatabase.getInstance().getReference("questions");

        questionRef.child("bio").child("Clonagem").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                numberOfChildren = dataSnapshot.getChildrenCount(); //Pega o número de questões
                for (DataSnapshot itemSnapshot2 : dataSnapshot.getChildren()) {
                    arrayChildren.add(itemSnapshot2.getValue(Questions.class)); //adiciona cada questão no array
                }
                Collections.shuffle(arrayChildren); //Embaralha array
                ExibirPergunta(ind); //Exibe pela primeira vez a primeira pergunta
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button_altA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { /*se clicar no 1º botão, vê se a resposta está certa, incrementa a variavel answerCount e chama
                a função ExibirPergunta novamente, mas passando o índice incrementado para q exiba a próxima.  */
                Questions t = (Questions) arrayChildren.get(ind);
                if (t.gabarito.equals("AltA")) { //não está entrando no equals, precisa resolver isso
                    answerCount++;
                    System.out.println(answerCount);
                }
                ExibirPergunta(ind++);
            }
        });
        //Utilizando o mesmo raciocinio do primeiro botão, os outros botões também fazem o mesmo
        button_altB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Questions t = (Questions) arrayChildren.get(ind);
                if (t.gabarito.equals("AltB")) {
                    answerCount++;
                    System.out.println(answerCount);
                }
                ExibirPergunta(ind++);
            }
        });
        button_altC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Questions t = (Questions) arrayChildren.get(ind);
                if (t.gabarito.equals("AltC")) {
                    answerCount++;
                    System.out.println(answerCount);
                }
                ExibirPergunta(ind++);
            }
        });
        button_altD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Questions t = (Questions) arrayChildren.get(ind);
                if (t.gabarito.equals("AltD")) {
                    answerCount++;
                    System.out.println(answerCount);
                }
                ExibirPergunta(ind++);
            }
        });

    };;
}