package com.example.segfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClinicInformation extends AppCompatActivity {
    private Button clinichours, insurance, manageServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_information);

        clinichours = (Button) findViewById(R.id.hours);
        insurance = (Button) findViewById(R.id.insurance);
        manageServices = (Button) findViewById(R.id.manageServices);

        clinichours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clinichours();
            }
        });

        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insurance();
            }
        });

        manageServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageServices();
            }
        });
    }

    public void clinichours(){
        Intent intent = new Intent(getApplicationContext(), ClinicHours.class);
        startActivity(intent);
    }

    public void insurance(){
        Intent intent = new Intent(getApplicationContext(), insuranceandpayment.class);
        startActivity(intent);
    }

    public void manageServices(){
        Intent intent = new Intent(getApplicationContext(), EmployeeAddServices.class);
        startActivity(intent);
    }
}
