package com.example.deliverable1_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class AccountCreationScreen extends AppCompatActivity {

    EditText gUser, gEmail, gPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_screen);

        //get spinner from xml
        Spinner dropdownMenu = (Spinner) findViewById(R.id.spinner1);

        //create a list of items for the user to select from
        String[] items = new String[]{"Employee", "Patient"};

        //create and set the spinner to the adapter made that coincides with the created string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdownMenu.setAdapter(adapter);
    }


    public void OnCreateAccount(View view){
        //Application Context and Activity

        gUser = (EditText)findViewById(R.id.usernameLogin);
        gEmail = (EditText)findViewById(R.id.emailLogin);
        gPass = (EditText)findViewById(R.id.passwordLogin);

        Intent newUser = new Intent(getApplicationContext(), User.class);
        newUser.putExtra(gUser, )


        Intent intent2 = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivityForResult(intent2,0);
    }
}

