package com.example.segfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Welcomepage extends AppCompatActivity {
    TextView welcomedisply;
    String name,type;
    Button mainmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        mainmenu = (Button) findViewById(R.id.loginwelcome);

        welcomedisply = (TextView) findViewById(R.id.displaywelcome);
        name = getIntent().getExtras().getString("Name");
        type = getIntent().getExtras().getString("Type");
        welcomedisply.setText("Welcome " + name+ "! You are logged-in as " + type);

        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlreadyRegister();
            }
        });
    }



    public void AlreadyRegister(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
