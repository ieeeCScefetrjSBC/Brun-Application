package com.ramoieee.wolfbyte.brunapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 22/10/2017.
 */

public class ResultadoAlunoActivity extends AppCompatActivity{

    Button button_return,button_select ;
    Spinner spinner_turma, spinner_aluno;


   /* ListData localList = new ListData();

    public List<String> alunoSpinnerItems = new ArrayList<String>();

    ArrayList listFirebase = new ArrayList();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference listurmaRef = database.getReference("points").child("listurmas");*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultaluno);


        spinner_turma = (Spinner)findViewById(R.id.spinner_class);
        spinner_aluno = (Spinner)findViewById(R.id.spinner_student);
        button_return = (Button) findViewById(R.id.button_voltar);
        button_select = (Button) findViewById(R.id.button_selecionar);

        /* spinner_turma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = spinner_turma.getSelectedItem().toString();

                listurmaRef.child(selectedItem).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listFirebase.clear();
                        alunoSpinnerItems.clear();
                        int iterador=0;

                        // Fills the arraylist with the subjects in Firebase Database
                        for (DataSnapshot itemSnapshot2 : dataSnapshot.getChildren()) {
                            listFirebase.add(itemSnapshot2.getValue(ListData.class));
                            localList = (ListData) listFirebase.get(iterador);
                            if(localList.isOn){
                                alunoSpinnerItems.add(localList.name);
                            }
                            iterador++;
                        }

                        AddItemsOnSpinner(alunoSpinnerItems);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }*/


        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_back = new Intent(ResultadoAlunoActivity.this, MainActivity.class);
                startActivity(int_back);
            }
        });

        button_select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent int_quiz = new Intent(ResultadoAlunoActivity.this, ExibicaoActivity.class);
               // int_quiz.putExtra("Turma", spinner_turma.getSelectedItem().toString());
               // int_quiz.putExtra("Aluno", spinner_aluno.getSelectedItem().toString());
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
        spinner_aluno.setAdapter(dataAdapter);
    }
}

