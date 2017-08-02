package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectsActivity extends AppCompatActivity {

    private Button button_subject_example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        button_subject_example = (Button)findViewById(R.id.button_subject_example);

        button_subject_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_quiz_example = new Intent(SubjectsActivity.this, QuizActivity.class);
                startActivity(int_quiz_example);

            }
        });
    }
}
