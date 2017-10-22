/*
* - testar se já existe um diretório
* - criar um diretorio pro usuário caso não exista
* - possibilitar a edição de algumas informações. Até o momento permitiremos apenas editar: nome, nascimento e área favorita apenas.
* - publicar as infos necessárias na base de dados
*
* */



package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserSettingsActivity extends AppCompatActivity {

    private static final String TAG = "UserSettingsActivity";

    Button button_edit_user_info, button_sign_out, button_back_main;
    private TextView view_userName, view_userEmail, view_userBonustime, view_userInstitution; //view_userID, view_userBirth, view_userYear, view_userStudentId, view_userFavArea;
    private EditText edit_userName, edit_userEmail, edit_userFavArea, edit_userBonustime;

    UserInfo myUser = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        // BUTTONS
        button_edit_user_info = (Button)findViewById(R.id.button_edit_user_info);
        button_sign_out = (Button)findViewById(R.id.button_sign_out);
        button_back_main = (Button)findViewById(R.id.button_back_main);
        
        // TEXT VIEWS
        view_userName = (TextView)findViewById(R.id.view_userName);
        view_userEmail = (TextView)findViewById(R.id.view_userEmail);
//        view_userID = (TextView)findViewById(R.id.view_userID);
//        view_userBirth = (TextView) findViewById(R.id.view_userBirth);
//        view_userYear = (TextView) findViewById(R.id.view_userYear);
//        view_userStudentId = (TextView) findViewById(R.id.view_userStudentId);
//        view_userFavArea = (TextView) findViewById(R.id.view_userFavArea);
        view_userBonustime = (TextView) findViewById(R.id.view_userBonustime);
        view_userInstitution = (TextView) findViewById(R.id.view_userInstitution);

        // EDIT TEXTS
        edit_userName = (EditText)findViewById(R.id.edit_userName);
        edit_userEmail = (EditText)findViewById(R.id.edit_userEmail);
        edit_userFavArea = (EditText)findViewById(R.id.edit_userFavArea);
        edit_userBonustime = (EditText) findViewById(R.id.edit_userBonustime);

        UpdateFields();

        // Signs user out and cleans the data fields on screen
        button_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                view_userName.setText("blank");
                view_userEmail.setText("blank");
                //view_userID.setText("blank");
            }
        });

        // Shows the Edit Text fields to add/change user info
        button_edit_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Main = new Intent(UserSettingsActivity.this, MainActivity.class);
                startActivity(int_Main);
                finish();
            }
        });
    }

    protected void UpdateFields(){
        // Retrieves user information from Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();

            // Updates user information fields
            view_userName.setText(name);
            view_userEmail.setText(email);
            //view_userID.setText(uid);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users");

            myRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        myUser = dataSnapshot.getValue(UserInfo.class);
//                        view_userBirth.setText(myUser.birth);
//                        view_userYear.setText(myUser.yearclass);
//                        view_userFavArea.setText(myUser.fav_area);
//                        view_userStudentId.setText(myUser.student_id);
                        view_userBonustime.setText(myUser.bonus_time);
                        view_userInstitution.setText(myUser.institution);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}