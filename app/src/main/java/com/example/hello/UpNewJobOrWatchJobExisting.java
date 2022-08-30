package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpNewJobOrWatchJobExisting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_new_job_or_watch_job_existing);
    }



    public void moveToUpNewJobDetails(View view) {
        Intent intent = new Intent(UpNewJobOrWatchJobExisting.this, UpNewJobDetails.class);
        startActivity(intent);
        finish();
    }

    public void moveToExistingJob(View view) {
        Intent intent = new Intent(UpNewJobOrWatchJobExisting.this, ExistingJobs.class);
        startActivity(intent);
        finish();
    }


}