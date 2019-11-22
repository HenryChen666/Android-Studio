package com.example.segfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeProfile extends AppCompatActivity {

    private EditText addresstext;
    private EditText phone_number;
    private EditText clinic_name;
    private Button workhours;
    private Button insurance;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        addresstext = (EditText) findViewById(R.id.address);
        phone_number = (EditText) findViewById(R.id.phonenumber);
        clinic_name = (EditText) findViewById(R.id.clinicname);
        workhours = (Button) findViewById(R.id.workinghour);
        insurance = (Button) findViewById(R.id.payment);
        next = (Button) findViewById(R.id.nextbtn);

        workhours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workhour(v);
            }
        });

        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  //              AlreadyRegister(v);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               AlreadyRegister(v);
            }
        });
    }

    public void workhour(View view){
        Intent intentService = new Intent(getApplicationContext(), workhours.class);
        startActivity(intentService);
    }

}
