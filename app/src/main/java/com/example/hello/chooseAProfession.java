package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class chooseAProfession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_aprofession);
    }

    public void toMain(View view){
        Intent intent = new Intent(chooseAProfession.this,login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.exitApp) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToJobs(View view) {
        Intent intent = new Intent(this, CategoryInfo.class);

        switch(view.getId()){
            case R.id.Electric:
                intent.putExtra("category","Electric");
                break;
            case R.id.Enginners:
                intent.putExtra("category","Enginners");
                break;
            case R.id.Delivery:
                intent.putExtra("category","Delivery");
                break;
            case R.id.Marcketing:
                intent.putExtra("category","Marcketing");
                break;
        }
        startActivity(intent);
        finish();
    }
}