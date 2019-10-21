package com.example.deliverable1_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.Serializable;


public class AccountCreationScreen extends AppCompatActivity {

    EditText gUser, gEmail, gPass;
    String username, role;
    Spinner dropdownMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_screen);

        //get spinner from xml
        dropdownMenu = (Spinner) findViewById(R.id.spinner1);

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

        String type = dropdownMenu.getSelectedItem().toString();
        int userType;
        if (type == "Employee"){
            userType = 1;
        } else {
            userType = 2;
        }


        User createUser = new User();

        createUser.setEmail(gEmail.getText().toString());
        createUser.setUsername(gUser.getText().toString());
        createUser.setPassword(gPass.getText().toString());
        createUser.setUserType(userType);

     //   Intent newUser = new Intent(getApplicationContext(), User.class);
     //   newUser.putExtra("currentUser", (Serializable) createUser);


        Intent intent2 = new Intent(AccountCreationScreen.this, WelcomeScreen.class);
        username = gUser.getText().toString();
        role = type;
        intent2.putExtra("username", username);
        intent2.putExtra("role",type);
        startActivity(intent2);
        finish();

        //startActivityForResult(intent2,0);
    }
}

