package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //public void clicked(View view){
     //   Toast.makeText(MainActivity.this, "Btn Clicked"+view.getId(), Toast.LENGTH_LONG).show();
   // }



    //public void toLogin(View view){
    //    Intent intent = new Intent(MainActivity.this,login.class);
     //   startActivity(intent);
     //   finish();
   // }

    public void moveToLogin(View view) {
        if(view.getId() == R.id.btn_Candidate){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("STATUS","CANDIDATE");
            editor.apply();
        }else{
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("STATUS","EMPLOYER");
                editor.apply();
        }

        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
        finish();
    }

}