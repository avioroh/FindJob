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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
    }



    public void signIn(View view) {
        EditText etEmail = findViewById(R.id.editTextEmailAddress);
        EditText etPass = findViewById(R.id.editTextTextPassAddress);

        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();

        if (email.length() > 0 && password.length() >= 6) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
               mAuth.signInWithEmailAndPassword(email,password)
                       .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){

                                   checkNavigation(email);
                               }else{
                                   Toast.makeText(login.this, "Auth Failed "  , Toast.LENGTH_SHORT).show();

                               }
                           }
                       });
            }
            else {
                Toast.makeText(login.this, "Wrong Email", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToSignup(View view) {
        Intent intent = new Intent(login.this, Signup.class);
        startActivity(intent);
        finish();
    }

    public void moveToReset(View view) {
        Intent intent = new Intent(login.this, ResetPassword.class);
        startActivity(intent);
        finish();

    }



    public void checkNavigation(String email){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(login.this);
        String status = preferences.getString("STATUS", null);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        //checkEmail()

        if (status.equals("CANDIDATE")){
            myRef  = database.getReference("candidate");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean statusOk = false;

                    for (DataSnapshot person: snapshot.getChildren()){
                        if(person.exists()){
                           String personEmail = person.getValue(Candidate.class).getEmail().toString();

                            if (personEmail.equals(email)){ // checked status ok

                                statusOk = true;
                                break;
                            }
                       }
                    }
                    if (statusOk){
                        Toast.makeText(login.this, "status ok", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, chooseAProfession.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //status error מודיע על שגיאה ומחזיר למסך הבחירה
                        Toast.makeText(login.this, "wrong status ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else{


            myRef = database.getReference("employer");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean statusOk = false;


                    for (DataSnapshot person: snapshot.getChildren()){
                        if(person.exists()){
                            String personEmail = person.child("details").getValue(Employer.class).getEmail().toString();

                            Toast.makeText(login.this, personEmail + "- " + email, Toast.LENGTH_SHORT).show();

                            if (personEmail.equals(email)){ // checked status ok

                                statusOk = true;
                                break;
                            }
                        }
                    }
                    if (statusOk){
                        Toast.makeText(login.this, "status ok", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, UpNewJobOrWatchJobExisting.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //status error מודיע על שגיאה ומחזיר למסך הבחירה
                        Toast.makeText(login.this, "wrong status ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


    }
}