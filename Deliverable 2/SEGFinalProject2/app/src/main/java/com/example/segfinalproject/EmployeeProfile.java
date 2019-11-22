package com.example.segfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeProfile extends AppCompatActivity {

    private EditText addresstext;
    private EditText phone_number;
    private EditText clinic_name;
    private Button workhours;
    private Button insurance;
    private Button next;
    private DatabaseReference dr, temp;

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
                insuranceandpayment(v);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClinic(clinic_name.getText().toString(), addresstext.getText().toString(), phone_number.getText().toString());
            }
        });
    }

    public void workhour(View view){
        Intent intentService = new Intent(getApplicationContext(), workhours.class);
        startActivity(intentService);
    }

    public void createClinic(String name, String address, String phoneNumber) {

        final String clinicName, clinicAddress, clinicPhoneNumber;
        clinicName = name;
        clinicAddress = address;
        clinicPhoneNumber = phoneNumber;


        String tempId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference clinicCheck = FirebaseDatabase.getInstance().getReference("User").child(tempId);

        clinicCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("clinic")) {

                    Toast.makeText(getApplicationContext(), "There is already a Clinic associated with this user.", Toast.LENGTH_LONG).show();


                } else {

                    dr = FirebaseDatabase.getInstance().getReference("clinics");
                    temp = dr.push();

                    Clinic clinic = new Clinic(clinicName, clinicAddress, clinicPhoneNumber, temp.getKey());

                    temp.setValue(clinic);

                    clinicCheck.child("clinic").setValue(temp.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        public void insuranceandpayment(View view){
            Intent intentService = new Intent(getApplicationContext(), insuranceandpayment.class);
            startActivity(intentService);
        }
    }