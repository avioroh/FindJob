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

import java.util.HashMap;

public class CV_Employee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_employee);
    }

    public void writeData(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("employer");


        String companyname = ((EditText) findViewById(R.id.etJobTitle)).getText().toString().trim();
        String fullname = ((EditText) findViewById(R.id.etFullname)).getText().toString().trim();
        String employerId = ((EditText) findViewById(R.id.etId)).getText().toString().trim();
        String address = ((EditText) findViewById(R.id.etAddress)).getText().toString().trim();

        if (companyname.isEmpty() || fullname.isEmpty() ||   employerId.length() != 9
                || address.isEmpty() ) {
            Toast.makeText(this, "Miss any param", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CV_Employee.this);
        String email = preferences.getString("EMAIL", null);

        Employer newEmployer = new Employer(companyname, fullname, employerId, address,email);



        HashMap add = new HashMap();
        add.put("details",newEmployer);

        myRef.child(employerId).updateChildren(add)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CV_Employee.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("EMPLOYER_ID",employerId);
                        editor.apply();

                        Intent intent = new Intent(CV_Employee.this, UpNewJobOrWatchJobExisting.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}