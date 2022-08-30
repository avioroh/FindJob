package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CV_Candidate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);


    }

   /* public void readDataCandidate(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("candidate");

        String name = ((EditText)findViewById(R.id.first_input)).getText().toString().trim();
        String lastName = ((EditText)findViewById(R.id.last_input)).getText().toString().trim();
        String age = ((EditText)findViewById(R.id.age)).getText().toString().trim();



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayContent.clear();
                arrayContentNames.clear();
                for (DataSnapshot item: snapshot.getChildren()){
                    if (item.exists()){
                        arrayContent.add(new Person(item.getValue(Person.class).getName(),item.getValue(Person.class).getLastname(),item.getValue(Person.class).getAge()));
                        arrayContentNames.add(item.getValue(Person.class).getName()+" "+ item.getValue(Person.class).getLastname() + " "+ item.getValue(Person.class).getAge());
                        // item.getValue(Person.class).getId().toString()+""+
                        //          item.getValue(Person.class).getLastname().toString()+""+
                        //          item.getValue(Person.class).getName().toString());
                    }
                }
                listViewData.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/


    public void writeData(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("candidate");

        String fullname = ((EditText)findViewById(R.id.etFullname)).getText().toString().trim();
        String id = ((EditText)findViewById(R.id.etId)).getText().toString().trim();
        String address = ((EditText)findViewById(R.id.etAddress)).getText().toString().trim();
        String phone = ((EditText)findViewById(R.id.etPhone)).getText().toString().trim();
        String academic_studies = ((EditText)findViewById(R.id.etAcademic_studies)).getText().toString().trim();
        String exp = ((EditText)findViewById(R.id.etExp)).getText().toString().trim();
        String lenguages = ((EditText)findViewById(R.id.etLenguages)).getText().toString().trim();

        if (fullname.isEmpty() || id.length()!=9 || address.isEmpty() || phone.isEmpty() || academic_studies.isEmpty()
        || exp.isEmpty() || lenguages.isEmpty()){
            Toast.makeText(this, "Miss any param", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CV_Candidate.this);
        String email = preferences.getString("EMAIL", null);

        Candidate newCandidate = new Candidate(fullname,id,address,phone,academic_studies,exp,lenguages,email);

        myRef.child(id)
                .setValue(newCandidate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(CV_Candidate.this, chooseAProfession.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

}