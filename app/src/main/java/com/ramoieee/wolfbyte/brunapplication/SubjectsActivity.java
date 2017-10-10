package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    Button button_select;
    Spinner spinner_subject, spinner_discipline;

    ListData localList = new ListData();

    public List<String> subjectSpinnerItems = new ArrayList<String>();

    ArrayList listFirebase = new ArrayList();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference listnameRef = database.getReference("questions").child("listnames");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        button_select = (Button)findViewById(R.id.button_select);
        spinner_subject = (Spinner)findViewById(R.id.spinner_subject);
        spinner_discipline = (Spinner)findViewById(R.id.spinner_discipline);

        spinner_discipline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = spinner_discipline.getSelectedItem().toString();

                listnameRef.child(selectedItem).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listFirebase.clear();
                        subjectSpinnerItems.clear();
                        int iterador=0;

                        // Fills the arraylist with the subjects in Firebase Database
                        for (DataSnapshot itemSnapshot2 : dataSnapshot.getChildren()) {
                            listFirebase.add(itemSnapshot2.getValue(ListData.class));
                            localList = (ListData) listFirebase.get(iterador);
                            if(localList.isOn){
                                subjectSpinnerItems.add(localList.name);
                            }
                            iterador++;
                        }

                        AddItemsOnSpinner(subjectSpinnerItems);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent int_quiz = new Intent(SubjectsActivity.this, QuizActivity.class);
                int_quiz.putExtra("discipline", spinner_discipline.getSelectedItem().toString());
                int_quiz.putExtra("subject", spinner_subject.getSelectedItem().toString());
                startActivity(int_quiz);
                finish();
            }
        });
    }

    void AddItemsOnSpinner(List s){
        // Adds the list of subjects to the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, s);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_subject.setAdapter(dataAdapter);
    }
}
