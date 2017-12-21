package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FeedbackActivity extends AppCompatActivity { //Activity para mostrar feedback do quiz que o usuário fez
    Button btnBack;
    TextView rightQuestions;
    TextView wrongQuestions;
    TextView points;
    String intentValores []; // 1ª posição do array recebe qtd de repostas certas e 2ª posição recebe qtd total de perguntas
    int erros, acertos, pontos, p1 = 1, p2 = 0;

    UserInfo myUser = new UserInfo();

    SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH");
    SimpleDateFormat dateFormat_minuto = new SimpleDateFormat("mm");
    Date data = new Date();
    String hora_atual;

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

        // BONUS TIME
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        hora_atual = dateFormat_hora.format(data_atual);
       // String minuto_atual = dateFormat_minuto.format(data_atual);

        // Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    myUser = dataSnapshot.getValue(UserInfo.class);

                    if (myUser.bonus_time!=null && myUser.bonus_time.equals(hora_atual)){
                        p1=2;
                    }
                    //Parte da pontuação
                    pontos = acertos*p1 + erros*p2;
                    points.setText(points.getText() + " " + pontos);

                    System.out.println("hora atual: " + hora_atual);
                    System.out.println("bonus time: " + myUser.bonus_time);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference refSavePoints = database.getReference("points");
        refSavePoints.child("users").child("Example").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot itemSnapshot2 : dataSnapshot.getChildren()) {
                        pointsUser.add(itemSnapshot2.getValue(Points.class));
                    }
                    System.out.println("Pontos: "+pointsUser);

                }else{
                    System.out.println("teste");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
