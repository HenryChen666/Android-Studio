package com.example.deliverable1_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void OnBack(View view){
        //Creating a Return intent to pass Main Activity
        Intent returnIntent = new Intent();

        //Finishing Activity and return to main screen
        finish();
    }

}
