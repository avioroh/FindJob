package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class InsideJob extends AppCompatActivity {

    String employerID;
    String jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_job);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            employerID = extras.getString("EMPLOYERID");
            jobID = extras.getString("JOBID");
            Toast.makeText(this, employerID +" "+ jobID, Toast.LENGTH_SHORT).show();
        }
        readJobInfo();
    }

    private void readJobInfo() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("employer/"+employerID+"/jobs/"+jobID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String contentAboutTheCompany = snapshot.getValue(JobClass.class).getContentAboutTheCompany();
                String jobID = snapshot.getValue(JobClass.class).getID();
                String jobRequirements = snapshot.getValue(JobClass.class).getJobRequirements();
                String jobTitle = snapshot.getValue(JobClass.class).getJobTitle();
                String jobType = snapshot.getValue(JobClass.class).getJobType();
                String jobLocation = snapshot.getValue(JobClass.class).getLocation();
                String category = snapshot.getValue(JobClass.class).getCategory();


                TextView tvContent = findViewById(R.id.tvContent);
                TextView tvID = findViewById(R.id.tvID);
                TextView tvJobReq = findViewById(R.id.tvJobReq);
                TextView tvJobTitle = findViewById(R.id.tvJobTitle);
                TextView tvJobType = findViewById(R.id.tvJobType);
                TextView tvLocation = findViewById(R.id.tvLocation);


                tvContent.setText(contentAboutTheCompany);
                tvID.setText(jobID);
                tvJobReq.setText(jobRequirements);
                tvJobTitle.setText(jobTitle);
                tvJobType.setText(jobType);
                tvLocation.setText(jobLocation);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

   /* public void apply(View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("candidate");

        HashMap apply = new HashMap();
        apply.put("id",)

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

    }*/


}