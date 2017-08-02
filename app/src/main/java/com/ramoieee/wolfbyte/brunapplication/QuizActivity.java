package com.ramoieee.wolfbyte.brunapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.*;

public class QuizActivity extends AppCompatActivity {

    private Button button_altA, button_altB, button_altC, button_altD;
    private TextView text_enunciado;

    String enunciado, altA, altB, altC, altD, gabarito;

    DatabaseReference questionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button_altA = (Button)findViewById(R.id.button_altA);
        button_altB = (Button)findViewById(R.id.button_altB);
        button_altC = (Button)findViewById(R.id.button_altC);
        button_altD = (Button)findViewById(R.id.button_altD);

        text_enunciado = (TextView)findViewById(R.id.text_enunciado);

        questionRef = FirebaseDatabase.getInstance().getReference("questions");

        questionRef.child("Example").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                enunciado = dataSnapshot.child("enunciado").getValue(String.class);
                altA = dataSnapshot.child("altA").getValue(String.class);
                altB = dataSnapshot.child("altB").getValue(String.class);
                altC = dataSnapshot.child("altC").getValue(String.class);
                altD = dataSnapshot.child("altD").getValue(String.class);
                gabarito = dataSnapshot.child("gabarito").getValue(String.class);

                text_enunciado.setText(enunciado);
                button_altA.setText(altA);
                button_altB.setText(altB);
                button_altC.setText(altC);
                button_altD.setText(altD);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
