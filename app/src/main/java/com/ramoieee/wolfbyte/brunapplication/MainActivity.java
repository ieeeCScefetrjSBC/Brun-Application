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

    Button button_settings;
    TextView view_welcome_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_settings = (Button) findViewById(R.id.button_user_settings);
        view_welcome_text = (TextView)findViewById(R.id.text_welcome);
        // #########################
        // CHECKS IF USER IS ALREADY SIGNED IN. IF NOT, CALLS LOGIN ACTIVITY
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent int_SignIn = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(int_SignIn);
        }else {
            Toast.makeText(MainActivity.this, "A user is already logged in",
                    Toast.LENGTH_SHORT).show();
            view_welcome_text.setText("Olá, "+ user.getDisplayName());
        }
        // ##########################

//        // TODO: REMOVE THIS BLOCK AFTER TESTING
//        if(user != null){
//            Toast.makeText(MainActivity.this, "A user is already logged in",
//                    Toast.LENGTH_SHORT).show();
//            Intent int_UserSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
//            startActivity(int_UserSettings);
//        }
//        // ##########################

        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_UserSettings = new Intent(MainActivity.this, UserSettingsActivity.class);
                startActivity(int_UserSettings);
            }
        });

    }

//    @Override
//    public void onStart(){
//        super.onStart();
//
//        // #########################
//        // CHECKS IF USER IS STILL SIGNED IN. IF NOT, CALLS LOGIN ACTIVITY
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user == null){
//            Toast.makeText(MainActivity.this, "You need to be logged in to access our app features",
//                    Toast.LENGTH_SHORT).show();
//            Intent int_SignIn = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(int_SignIn);
//        }
//        // ##########################
//    }
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
        }else{
            view_welcome_text.setText("Olá, "+ user.getDisplayName());
        }
        // ##########################

    }


    @Override
    public void onStop(){
        super.onStop();
    }
}
