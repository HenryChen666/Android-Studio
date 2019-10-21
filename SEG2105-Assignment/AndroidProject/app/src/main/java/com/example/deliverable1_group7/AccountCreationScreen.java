package com.example.deliverable1_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class AccountCreationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_screen);
        Spinner dropdownMenu = (Spinner) findViewById(R.id.spinner1);

        String[] items = new String[]{"Employee", "Patient"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdownMenu.setAdapter(adapter);
    }


    public void OnCreateAccount(View view){
        //Application Context and Activity
        Intent intent2 = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivityForResult(intent2,0);
    }
}

