package com.example.segfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText useremail;
    private EditText userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        useremail = ();
    }

    public void OnEnter(View view) {
//        Application Context and Activity
//        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
//        startActivityForResult(intent,0);
    }

    public void OnCreateAccount(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivityForResult(intent, 0);
    }
}
