package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private Handler myHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    private Runnable timerLogin = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(ResetPassword.this, login.class);
            startActivity(intent);
            finish();
        }
    };

    public void resetPassword(View view) {
        EditText etEmail = (EditText)findViewById(R.id.editTextEmail);
        String email = etEmail.getText().toString().trim();

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ResetPassword.this, "Chek Your Email", Toast.LENGTH_SHORT).show();
                            myHandler.postDelayed(timerLogin,2000);
                        }
                        else{
                            Toast.makeText(ResetPassword.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}