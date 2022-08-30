package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
    }

    public void moveToLogin(View view) {
        Intent intent = new Intent(Signup.this, login.class);
        startActivity(intent);
        finish();//
    }

    public void signup(View view) {
        EditText etEmail = findViewById(R.id.editTextEmailAddress);
        EditText etPass  = findViewById(R.id.editTextPassword);
        EditText etConfirmPass  = findViewById(R.id.editTextConfirmPassword);

        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        String confirmPassword = etConfirmPass.getText().toString().trim();

        if(password.length()>=6 && password.equals(confirmPassword)){
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                //if(email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.+[a-z]+")){   //REGEX

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Signup.this);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("EMAIL",email);
                                    editor.apply();

                                    //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Signup.this);
                                    String status = preferences.getString("STATUS", null);
                                    if (status.equals("EMPLOYER")){
                                        Intent intent = new Intent(Signup.this, CV_Employee.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Intent intent = new Intent(Signup.this, CV_Candidate.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                                else{
                                    Toast.makeText(Signup.this,"Signup failed ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else{
                //Toast.makeText(Signup.this,"Wrong Email", Toast.LENGTH_SHORT).show();
                etEmail.setError("Wrong Email");
                etEmail.requestFocus();
            }
        }
        else{
            etPass.setError("Wrong Pass");
            etPass.requestFocus();
        }
    }
}