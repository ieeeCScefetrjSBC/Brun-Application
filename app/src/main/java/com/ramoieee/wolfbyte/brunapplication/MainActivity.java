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

public class MainActivity extends AppCompatActivity {


    Button button_quiz, button_settings;
    TextView view_welcome_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
            Toast.makeText(MainActivity.this, "Usuário conectado",
                    Toast.LENGTH_SHORT).show();
            view_welcome_text.setText("Olá, " + user.getDisplayName());
        }

        button_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_subject = new Intent(MainActivity.this, SubjectsActivity.class);
                startActivity(int_subject);
                finish();
            }
        });

        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_UserSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
                startActivity(int_UserSettings);
            }
        });
    }
}