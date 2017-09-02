package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button button_study_activitiestest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_study_activitiestest = (Button) findViewById(R.id.button_study_activities);

        // #########################
        // CHECKS IF USER IS ALREADY SIGNED IN. IF NOT, CALLS LOGIN ACTIVITY
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent int_SignIn = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(int_SignIn);
        }
        // ##########################

        // TODO: REMOVE THIS BLOCK AFTER TESTING
        if(user != null){
            Toast.makeText(MainActivity.this, "A user is already logged in",
                    Toast.LENGTH_SHORT).show();
            Intent int_UserSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
            startActivity(int_UserSettings);
        }
        button_study_activitiestest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Intent quizAct = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(quizAct);
                finish();
            }
        });
    }



    @Override
    public void onStart(){
        super.onStart();

        // #########################
        // CHECKS IF USER IS STILL SIGNED IN. IF NOT, CALLS LOGIN ACTIVITY
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Toast.makeText(MainActivity.this, "You need to be logged in to access our app features",
                    Toast.LENGTH_SHORT).show();
            Intent int_SignIn = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(int_SignIn);
        }
        // ##########################
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();

        // #########################
        // CHECKS IF USER IS STILL SIGNED IN. IF NOT, CALLS LOGIN ACTIVITY
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Toast.makeText(MainActivity.this, "You need to be logged in to access our app features",
                    Toast.LENGTH_SHORT).show();
            Intent int_SignIn = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(int_SignIn);
        }
        // ##########################

    }

    @Override
    public void onStop(){
        super.onStop();
    }
}
