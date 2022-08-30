package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpNewJobDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String employer_id;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_new_job_details);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employer_id = preferences.getString("EMPLOYER_ID",null);

        Spinner dropdown = findViewById(R.id.spCategory);
        String[] items = new String[]{"Choose a profession:","Electric", "Software engineer", "Delivery person", "Marketing"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(this);

        category = "";
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position){
            case 0:
                category="";
                break;
            case 1:
                category = "Electric";
                break;
            case 2:
                category="Software engineer";
                break;
            case 3:
                category="Delivery person";
                break;
            case 4:
                category="Marketing";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void writeData(View view){

        if (category.isEmpty()){
            Toast.makeText(this, "you must choose a category", Toast.LENGTH_SHORT).show();
            return;
        }


        String job_title = ((EditText)findViewById(R.id.etJobTitle)).getText().toString().trim();
        String job_id = ((EditText)findViewById(R.id.etId)).getText().toString().trim();
        String location = ((EditText)findViewById(R.id.etLocation)).getText().toString().trim();
        String jobType = ((EditText)findViewById(R.id.etJobType)).getText().toString().trim();
        String contentAboutTheCompany = ((EditText)findViewById(R.id.etContentAboutTheCompany)).getText().toString().trim();
        String jobRequirements = ((EditText)findViewById(R.id.etJobRequirements)).getText().toString().trim();



        Toast.makeText(this, ""+ job_title+" " +job_id
                +" "+location
                +" "+jobType
                +" "+contentAboutTheCompany
                +" "+ jobRequirements, Toast.LENGTH_SHORT).show();


        if (job_title.isEmpty() || job_id.isEmpty() || location.isEmpty() || jobType.isEmpty() || contentAboutTheCompany.isEmpty() || jobRequirements.isEmpty()){
            Toast.makeText(this, "Missing Variable", Toast.LENGTH_SHORT).show();
            return;
        }
        // save global parameter
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("employer/");

        JobClass newJob = new JobClass(job_title,job_id,location,jobType,contentAboutTheCompany,jobRequirements,category,employer_id);

        HashMap updateJobs = new HashMap();
        updateJobs.put(job_id,newJob);

        myRef.child(employer_id+"/jobs").updateChildren(updateJobs)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent intent = new Intent(UpNewJobDetails.this, UpNewJobOrWatchJobExisting.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

}