package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class InsideJobCandidate extends AppCompatActivity {
    String candidateID ;
    String employerID;
    String jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_job_candidate);


        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            employerID = extras.getString("EMPLOYERID");
            jobID = extras.getString("JOBID");
            Toast.makeText(this, employerID +" "+ jobID, Toast.LENGTH_SHORT).show();
        }
    }
    public void apply(View view) {


    }

}