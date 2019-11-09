package com.example.segfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    Button serviceButton, manageEmployees, managePatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        serviceButton = (Button) findViewById(R.id.manClinicServices);
        manageEmployees = (Button) findViewById(R.id.manageEmployees);
        managePatients = (Button) findViewById(R.id.registeredPatients);

        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceMenu(view);
            }
        });
    }

    public void ServiceMenu(View view){

        Intent intent = new Intent(getApplicationContext(), service_creation.class);
        startActivity(intent);

    }
}
