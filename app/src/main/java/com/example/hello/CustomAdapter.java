package com.example.hello;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<JobClass> {

     CustomAdapter(Context context, ArrayList databaseAdapter) {
         super(context,R.layout.custom_row,databaseAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(getContext());
        View customView = li.inflate(R.layout.custom_row,parent,false);

        String jobName = String.valueOf(getItem(position).getJobTitle());
        String jobID = String.valueOf(getItem(position).getID());
        String employerID = String.valueOf(getItem(position).getEmployerID());

        TextView tvJobName = (TextView) customView.findViewById(R.id.TvJobName);
        Button inside_btn = (Button) customView.findViewById(R.id.Inside_btn);

        tvJobName.setText(jobName);

        inside_btn.setText(employerID+"-"+jobID);
        return customView;
    }
}
