package com.ramoieee.wolfbyte.brunapplication;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {

    private Button button_altA, button_altB, button_altC, button_altD, button_altE;
    private TextView text_enunciado;

    String enunciado, altA, altB, altC, altD, altE, gabarito;
    public int answerCount = 0; //Variavel que contabiliza os acertos

    DatabaseReference questionRef;
    ArrayList<Questions> arrayChildren = new ArrayList<Questions>(); //ArrayList para colocar todas as perguntas e suas alternativas/gabarito
    Long numberOfChildren; //Váriavel pra colocar quantas questões tem
    public int ind; //Váriavel para saber a pergunta de qual indice puxar do array
    String intentValores = "";
    String subject;

    public void setInd(int ind) {
        this.ind = ind; //método para mudar o valor de ind em qualquer lugar da classe
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount; //método para mudar o valor de answerCount em qualquer lugar da classe
    }

    public void setNumberOfChildren(long numberOfChildren) {
        this.numberOfChildren = numberOfChildren; //método para mudar o valor do setNumberOfChildren em qualquer lugar da classe
    }

    public void ExibirPergunta(int index) { //Método para exibir as perguntas
        System.out.println(arrayChildren);
        if (index < numberOfChildren) {
            System.out.println("index:" + index);
            System.out.println(numberOfChildren);
            Questions ques = (Questions) arrayChildren.get(index);
            enunciado = ques.enunciado;
            altA = ques.altA;
            altB = ques.altB;
            altC = ques.altC;
            altD = ques.altD;
            altE = ques.altE;
            gabarito = ques.gabarito;
            text_enunciado.setText(enunciado);
            button_altA.setText(altA);
            button_altB.setText(altB);
            button_altC.setText(altC);
            button_altD.setText(altD);
            button_altE.setText(altE); //Acrescentei a alternativa E, que não tinha antes
        } else { //Se o index for igual ao número de perguntas menos 1, é porque chegou ao final e passa pra activity das matérias (provisório)
            setInd(0);
            salvarIndice();
            Intent int_feedback = new Intent(QuizActivity.this, FeedbackActivity.class);
            intentValores = answerCount + "#" + numberOfChildren; //A quantidade de acertos vai para uma string junto com o numero de perguntas.
            //Exemplo: 2#8 <- isso indica que a pessoa acertou 2 e tem 8 questões, na outra activity isso é separado.
            System.out.println(intentValores);
            int_feedback.putExtra("Value", intentValores); //String é passada para a outra activity
            startActivity(int_feedback);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button_altA = (Button) findViewById(R.id.button_altA);
        button_altB = (Button) findViewById(R.id.button_altB);
        button_altC = (Button) findViewById(R.id.button_altC);
        button_altD = (Button) findViewById(R.id.button_altD);
        button_altE = (Button) findViewById(R.id.button_altE);

        text_enunciado = (TextView) findViewById(R.id.text_enunciado);

        String discipline = getIntent().getExtras().getString("discipline");
         subject = getIntent().getExtras().getString("subject");

        questionRef = FirebaseDatabase.getInstance().getReference("questions");

        questionRef.child(discipline).child(subject).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                setNumberOfChildren(dataSnapshot.getChildrenCount());//Pega o número de questões
                //Leitura de indice para saber se é preciso recuperar arraylist
                setInd(lerIndice());
                if (ind == 0) {
                    for (DataSnapshot itemSnapshot2 : dataSnapshot.getChildren()) {
                        arrayChildren.add(itemSnapshot2.getValue(Questions.class)); //adiciona cada questão no arrays
                    }
                    Collections.shuffle(arrayChildren);
                    ExibirPergunta(ind);
               } else{
                    //Se o indice não for zero, é preciso recuperar arraylist
                    arrayChildren = lerArrayList();
                    ExibirPergunta(ind);
                }
            }
            public void onCancelled(DatabaseError databaseError) {

            }

            });

        button_altA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { /*se clicar no 1º botão, vê se a resposta está certa, incrementa a variavel answerCount e chama
                a função ExibirPergunta novamente, mas passando o índice incrementado para q exiba a próxima.  */
                System.out.println("tam array "+ arrayChildren.size());
                System.out.println("indice: " + ind);
                Questions t = arrayChildren.get(ind);
                String gab = t.gabarito; //Coloca o gabarito na variável gab, não tava certo fazendo direto então coloquei na variável
                if (gab.equals("altA")) {
                    setAnswerCount(answerCount + 1);
                    System.out.println(answerCount); //debug
                }
                setInd(ind + 1); //aumenta o índice
                ExibirPergunta(ind);
            }
        });

        //Utilizando o mesmo raciocinio do primeiro botão, os outros botões também fazem o mesmo
        button_altB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("indice: " + ind);
                Questions t = arrayChildren.get(ind);
                String gab = t.gabarito;
                if (gab.equals("altB")) {
                    setAnswerCount(answerCount + 1);
                    System.out.println(answerCount);
                }
                setInd(ind + 1);
                ExibirPergunta(ind);
            }
        });
        button_altC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("indice: " + ind);
                Questions t = arrayChildren.get(ind);
                String gab = t.gabarito;
                if (gab.equals("altC")) {
                    setAnswerCount(answerCount + 1);
                    System.out.println(answerCount);
                }
                setInd(ind + 1);
                ExibirPergunta(ind);
            }
        });
        button_altD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("indice: " + ind);
                Questions t = arrayChildren.get(ind);
                String gab = t.gabarito;
                if (gab.equals("altD")) {
                    setAnswerCount(answerCount + 1);
                    System.out.println(answerCount);
                }
                setInd(ind + 1);
                ExibirPergunta(ind);
            }
        });
        button_altE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("indice: " + ind);
                Questions t = arrayChildren.get(ind);
                String gab = t.gabarito;
                if (gab.equals("altE")) {
                    setAnswerCount(answerCount + 1);
                    System.out.println(answerCount);
                }
                setInd(ind + 1);
                ExibirPergunta(ind);
            }
        });
    }
    //Salvar arrayList
    public void salvarArrayList(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Type arraych = new TypeToken<ArrayList<Questions>>() {}.getType();
        String tst = new Gson().toJson(arrayChildren, arraych);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ARRAY_CHILDREN", tst).apply();
        //Toast.makeText(this,"Array saved" , Toast.LENGTH_LONG).show();
    }
    //Recuperar arrayList
    public ArrayList<Questions> lerArrayList (){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Type arraych = new TypeToken<ArrayList<Questions>>() {}.getType();
        ArrayList<Questions> SavedArrayChildren = new Gson().fromJson(prefs.getString("ARRAY_CHILDREN", ""), arraych);
        //Toast.makeText(this,"Array retrieved" , Toast.LENGTH_LONG).show();
        return SavedArrayChildren;
    }
    //Salvar indice
    public void salvarIndice() {
        Integer indiceSalvar = ind-1;
        if (ind == 0){
            indiceSalvar = 0;
        }
        SharedPreferences prefs = getSharedPreferences(subject, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("indx", ind);
        if (indiceSalvar != 0) {
            Toast.makeText(this,"Parou na pergunta: "+ ind , Toast.LENGTH_LONG).show();
        }

        editor.commit();
    }
    //Recuperar indice
    public int lerIndice(){
        SharedPreferences sharedPref = getSharedPreferences(subject,Context.MODE_PRIVATE);
        Integer indicerecuperado = sharedPref.getInt("indx", 0);
        if (indicerecuperado != 0) {
            Toast.makeText(this, "Parou na pergunta: " + indicerecuperado.toString(), Toast.LENGTH_LONG).show();
        }
        return indicerecuperado;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Salvar arrayList e indice
        if (ind != 0){
            salvarArrayList();
            salvarIndice();
        }
    }
}