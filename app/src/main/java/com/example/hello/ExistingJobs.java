package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExistingJobs extends AppCompatActivity {

    String employer_id;
    ListView listViewData;
    ArrayAdapter<JobClass> adapter;

    ArrayList arrayContent = new ArrayList<JobClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_jobs);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        employer_id = preferences.getString("EMPLOYER_ID",null);

        listViewData = findViewById(R.id.lvJobsList);
        adapter = new CustomAdapter(this,arrayContent);
        listViewData.setAdapter(adapter);

        readExistingJobList();
    }

    private void readExistingJobList() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("employer/"+employer_id+"/jobs");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayContent.clear();
                for (DataSnapshot singleJob : snapshot.getChildren()){
                    if (singleJob.exists()) {
                        Toast.makeText(ExistingJobs.this, "" + singleJob.getValue(JobClass.class).JobTitle,
                                Toast.LENGTH_SHORT).show();
                        JobClass temp = new JobClass(singleJob.getValue(JobClass.class).getJobTitle(),
                                singleJob.getValue(JobClass.class).getID(),
                                singleJob.getValue(JobClass.class).getLocation(),
                                singleJob.getValue(JobClass.class).getJobType(),
                                singleJob.getValue(JobClass.class).getContentAboutTheCompany(),
                                singleJob.getValue(JobClass.class).getJobRequirements(),
                                singleJob.getValue(JobClass.class).getCategory(),
                                singleJob.getValue(JobClass.class).getEmployerID());


                       arrayContent.add(temp);
                    }
                }
                listViewData.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void inside(View view){
        String employerID;
        String jobID;
        String btnTitle = ((Button) view).getText().toString();
        int idx = btnTitle.indexOf("-");
        //employerID = btnTitle.substring(0,idx);
        jobID = btnTitle.substring(idx+1,btnTitle.length());

        Intent intent = new Intent(ExistingJobs.this, InsideJob.class);
        intent.putExtra("EMPLOYERID",employer_id);
        intent.putExtra("JOBID",jobID);

        startActivity(intent);
        finish();


    }


}