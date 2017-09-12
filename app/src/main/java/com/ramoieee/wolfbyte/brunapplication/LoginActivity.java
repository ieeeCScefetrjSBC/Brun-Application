package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    Button button_signIn, button_forgotPassword;
    private EditText field_email, field_password;

    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener AuthListener;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Sign in widgets
        button_signIn = (Button)findViewById(R.id.button_sign_in);
        button_forgotPassword = (Button)findViewById(R.id.button_forgot_pass);
        field_email = (EditText)findViewById(R.id.field_email);
        field_password = (EditText)findViewById(R.id.field_password);

        // Authentication Instance
        Auth = FirebaseAuth.getInstance();
        // Listens if user signs in or out
        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...;

            }
        };

        // Sign in button action
        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(field_email.getText().toString(), field_password.getText().toString());
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                checkUserData(user);

            }
        });

        button_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: ADD RETRIEVE PASSWORD FUNCTIONALITY
                finish();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Auth.addAuthStateListener(AuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AuthListener != null) {
            Auth.removeAuthStateListener(AuthListener);
        }
    }

    private void signInUser(String email, String password) {
        Log.d(TAG, "signInUser:" + email);

        Auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if(task.isSuccessful()){
                            Log.w(TAG, "signInWithEmail:success", task.getException());
                            Toast.makeText(LoginActivity.this, "Acesso permitido",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = Auth.getCurrentUser();
                            checkUserData(user);
                        }else{
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Falha na tentativa de acesso",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    private void checkUserData(FirebaseUser user){
        if (user != null) {
            String name = user.getDisplayName();
            Uri photoUrl = user.getPhotoUrl();
            if(name == null && photoUrl == null){
                Intent int_UserSettings = new Intent(LoginActivity.this, UserSettingsActivity.class);
                startActivity(int_UserSettings);
                finish();
            }else{
                finish();
            }
        }
    }

}
//admin@example.com
//123456