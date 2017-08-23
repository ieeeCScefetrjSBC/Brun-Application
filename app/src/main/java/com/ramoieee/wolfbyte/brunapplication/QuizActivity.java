package com.ramoieee.wolfbyte.brunapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        questionRef.child("bio").child("Clonagem").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                long numberOfChildren = dataSnapshot.getChildrenCount();
                ArrayList arrayChildren = new ArrayList();
                for (int i = 1; i <= numberOfChildren; i++) {
                    arrayChildren.add(i);
                }
                Collections.shuffle(arrayChildren);

                for (int i = 0; i < arrayChildren.size(); i++) {
                    enunciado = dataSnapshot.child(arrayChildren.get(i).toString()).child("enunciado").getValue(String.class);
                    altA = dataSnapshot.child(arrayChildren.get(i).toString()).child("altA").getValue(String.class);
                    altB = dataSnapshot.child(arrayChildren.get(i).toString()).child("altB").getValue(String.class);
                    altC = dataSnapshot.child(arrayChildren.get(i).toString()).child("altC").getValue(String.class);
                    altD = dataSnapshot.child(arrayChildren.get(i).toString()).child("altD").getValue(String.class);
                    gabarito = dataSnapshot.child(arrayChildren.get(i).toString()).child("gabarito").getValue(String.class);

                    text_enunciado.setText(enunciado);
                    button_altA.setText(altA);
                    button_altB.setText(altB);
                    button_altC.setText(altC);
                    button_altD.setText(altD);

                    button_altA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (gabarito.equals("AltA")) {
                                answerCount++;
                            }
                        }
                    });
                    button_altB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (gabarito.equals("AltB")) {
                                answerCount++;
                            }
                        }
                    });
                    button_altC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (gabarito.equals("AltC")) {
                                answerCount++;
                            }
                        }
                    });
                    button_altD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (gabarito.equals("AltD")) {
                                answerCount++;
                            }
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}