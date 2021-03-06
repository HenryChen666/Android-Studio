package com.example.deliverable1_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    String username, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

//        User displayUser = (User)getIntent().getSerializableExtra("currentUser");
//        String name = displayUser.getUsername();
//        String type;
//        if (displayUser.getUserType() == 1){
//            type = "Employee";
//        } else {
//            type = "Patient";
//        }

        TextView display = (TextView) findViewById(R.id.textView2);


        username = getIntent().getExtras().getString("username");
        role = getIntent().getExtras().getString("role");

        display.setText("Welcome " + username + "! You are logged in as " + role + ".");

    }

    public void OnBack(View view){
        //Creating a Return intent to pass Main Activity
        Intent returnIntent = new Intent();

        //Finishing Activity and return to main screen
        finish();
    }

}
