package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Homepage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText etNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentEmail = currentUser.getEmail();

      //  etNewTask = findViewById(R.id.etNewTask);
        TextView tvCurrentEmail = findViewById(R.id.tvCurrentEmail);
        tvCurrentEmail.setText(currentEmail);


    }

    public void logout(View view) {
        mAuth.signOut();
        Intent intent = new Intent(Homepage.this,login.class);
        startActivity(intent);
        finish();
    }


    public void delete(View view) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Homepage.this, "Delete!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Homepage.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(Homepage.this, "Cant delete, Try Again!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void writeTask(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("tasks");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String taskMessage = etNewTask.getText().toString().trim();

        myRef.child("users")
                .child("uid:"+uid)
                .child("userTasks")
                .setValue(taskMessage)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            etNewTask.setText("");
                        }
                    }
                });
    }

    public void readTasks(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("tasks");
    }
}