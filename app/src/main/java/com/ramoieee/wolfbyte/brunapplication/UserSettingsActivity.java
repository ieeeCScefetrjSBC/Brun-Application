package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSettingsActivity extends AppCompatActivity {

    private Button button_edit_user_info, button_sign_out, button_save_user_info, button_cancel_edit;
    private TextView view_userName, view_userEmail, view_userID;
    private EditText edit_userName, edit_userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        
        // BUTTONS
        button_edit_user_info = (Button)findViewById(R.id.button_edit_user_info);
        button_sign_out = (Button)findViewById(R.id.button_sign_out);
        button_save_user_info = (Button)findViewById(R.id.button_save_user_info);
        button_cancel_edit = (Button)findViewById(R.id.button_cancel_edit);
        
        // TEXTVIEWS
        view_userName = (TextView)findViewById(R.id.view_userName);
        view_userEmail = (TextView)findViewById(R.id.view_userEmail);
        view_userID = (TextView)findViewById(R.id.view_userID);

        // EDITTEXTS
        edit_userName = (EditText)findViewById(R.id.edit_userName);
        edit_userEmail = (EditText)findViewById(R.id.edit_userEmail);
        
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            // The user's ID, unique to the Firebase project.
            String uid = user.getUid();

            view_userName.setText(name);
            view_userEmail.setText(email);
            view_userID.setText(uid);
        }else{
            Intent int_SignIn = new Intent(UserSettingsActivity.this, LoginActivity.class);
            startActivity(int_SignIn);
        }

        // Signs user out and cleans the data fields on screen
        button_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                view_userName.setText("blank");
                view_userEmail.setText("blank");
                view_userID.setText("blank");
                finish();
            }
        });
        
        button_edit_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUserInfo();
                button_cancel_edit.setVisibility(View.VISIBLE);
                button_save_user_info.setVisibility(View.VISIBLE);
            }
        });

        button_cancel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_cancel_edit.setVisibility(View.GONE);
                button_save_user_info.setVisibility(View.GONE);
            }
        });

        button_save_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    protected void editUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Name, email address, and profile photo Url
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        // The user's ID, unique to the Firebase project.
        String uid = user.getUid();

        // HIDE TEXT VIEWS
        view_userName.setVisibility(View.GONE);
        view_userEmail.setVisibility(View.GONE);
        view_userID.setVisibility(View.GONE);

        // SHOW EDIT TEXT
        edit_userName.setVisibility(View.VISIBLE);
        edit_userName.setText(name);
        edit_userEmail.setVisibility(View.VISIBLE);
        edit_userEmail.setText(email);

    }
}
