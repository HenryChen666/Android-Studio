package com.example.deliverable1_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OnEnter(View view){
//        //Application Context and Activity
//        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
//        startActivityForResult(intent,0);
    }
    public void OnCreateAccount(View view){
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), AccountCreationScreen.class);
        startActivityForResult(intent,0);
    }

}
