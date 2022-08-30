package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class CategoryInfo extends AppCompatActivity {

    String category;
    ListView listViewData;
    ArrayAdapter<JobClass> adapter;
    ArrayList arrayContent = new ArrayList<JobClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_info);

        Bundle extras = getIntent().getExtras();
        category = extras.getString("category");


        listViewData = findViewById(R.id.lvCategoryJobsList);
        adapter = new CustomAdapter(this, arrayContent);
        listViewData.setAdapter(adapter);

        readByCategory();
    }

    private void readByCategory() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("employer");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayContent.clear();
                for (DataSnapshot employer : snapshot.getChildren()) {

                    String empId = employer.getKey();

                    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                   DatabaseReference  myRef2 = database2.getReference("employer/" + empId + "/jobs/" );

                   myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot job : snapshot.getChildren()) {
                               // Toast.makeText(CategoryInfo.this, "" + job.getValue(JobClass.class).getCategory(), Toast.LENGTH_SHORT).show();
                                if(job.getValue(JobClass.class).getCategory().equals(category)){
                                    Toast.makeText(CategoryInfo.this, "" + job.getValue(JobClass.class).getCategory(), Toast.LENGTH_SHORT).show();
                                    JobClass temp = new JobClass(job.getValue(JobClass.class).getJobTitle(),
                                            job.getValue(JobClass.class).getID(),
                                            job.getValue(JobClass.class).getLocation(),
                                            job.getValue(JobClass.class).getJobType(),
                                            job.getValue(JobClass.class).getContentAboutTheCompany(),
                                            job.getValue(JobClass.class).getJobRequirements(),
                                            job.getValue(JobClass.class).getCategory(),
                                            job.getValue(JobClass.class).getEmployerID());

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
        employerID = btnTitle.substring(0,idx);
        jobID = btnTitle.substring(idx+1,btnTitle.length());

        Intent intent = new Intent(CategoryInfo.this, InsideJobCandidate.class);
        intent.putExtra("EMPLOYERID",employerID);
        intent.putExtra("JOBID",jobID);

        startActivity(intent);
        finish();

    }
}