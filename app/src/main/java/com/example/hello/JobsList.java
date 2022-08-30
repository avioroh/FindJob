package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class JobsList extends AppCompatActivity {

    String category="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_list);


        Bundle extra = getIntent().getExtras();
            if(extra!=null){
                category = extra.getString("category");//
            }

        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();

    }


}