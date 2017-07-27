package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSettingsActivity extends AppCompatActivity {

    private Button button_load_info, button_sign_out;
    private TextView userName, userEmail, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        button_load_info = (Button)findViewById(R.id.button_load_info);
        button_sign_out = (Button)findViewById(R.id.button_sign_out);
        userName = (TextView)findViewById(R.id.userName);
        userEmail = (TextView)findViewById(R.id.userEmail);
        userID = (TextView)findViewById(R.id.userID);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            // The user's ID, unique to the Firebase project.
            String uid = user.getUid();

            userName.setText(name);
            userEmail.setText(email);
            userID.setText(uid);
        }else{
            Intent int_SignIn = new Intent(UserSettingsActivity.this, LoginActivity.class);
            startActivity(int_SignIn);
        }

        button_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                userName.setText("blank");
                userEmail.setText("blank");
                userID.setText("blank");
                finish();
            }
        });
    }
}
