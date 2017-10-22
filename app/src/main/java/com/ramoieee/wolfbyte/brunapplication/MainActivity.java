package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    Button button_quiz, button_settings;
    TextView view_welcome_text;
    String access;
    UserInfo myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");


        button_quiz = (Button) findViewById(R.id.button_quiz);


        button_settings = (Button) findViewById(R.id.button_user_settings);
        view_welcome_text = (TextView) findViewById(R.id.text_welcome);

        // #########################
        // CHECKS IF USER IS ALREADY SIGNED IN. IF NOT, CALLS LOGIN ACTIVITY
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent int_SignIn = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(int_SignIn);
            finish();
        } else {
            myRef.child(user.getUid().toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        myUser = dataSnapshot.getValue(UserInfo.class);
                        access = myUser.credencial;
                        //showUserInt(access);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            Toast.makeText(MainActivity.this, "Usuário conectado",
                    Toast.LENGTH_SHORT).show();
            view_welcome_text.setText("Olá, " + user.getDisplayName());
        }

        button_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_subject = new Intent(MainActivity.this, SubjectsActivity.class);
                startActivity(int_subject);
            }
        });

        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_UserSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
                startActivity(int_UserSettings);
                finish();
            }
        });
    }

    void showUserInt(String s){
        switch (s){
            case "admin":
                break;

            case "professor":
                button_quiz.setVisibility(View.GONE);
                break;

            case "aluno":
                // botão de acesso ao quiz
                // botão de acesso ao perfil
                break;

        }

    }
}