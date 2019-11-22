package com.example.segfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.provider.ContactsContract;
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
                checkIfExists(clinic_name.getText().toString(), addresstext.getText().toString(), phone_number.getText().toString());
            }
        });
    }

    public void workhour(View view){
        if (TextUtils.isEmpty(addresstext.getText().toString().trim()) || TextUtils.isEmpty(phone_number.getText().toString().trim()) || TextUtils.isEmpty(clinic_name.getText().toString().trim())){
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intentService = new Intent(getApplicationContext(), workhours.class);
        startActivity(intentService);
    }

    public void checkIfExists(String name, String address, String phoneNumber){

        final String clinicName, clinicAddress, clinicPhoneNumber;
        clinicName = name;
        clinicAddress = address;
        clinicPhoneNumber = phoneNumber;

        DatabaseReference clinics = FirebaseDatabase.getInstance().getReference("clinics");

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User").child(userId);

        clinics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ps : dataSnapshot.getChildren()){

                    if(clinicName.equals(ps.child("name").getValue())){

                        Toast.makeText(getApplicationContext(), "A clinic with this name already exists, you have been added to that clinic", Toast.LENGTH_LONG).show();
                        userRef.child("clinic").setValue(ps.getKey());
                        break;

                    }else{
                        createClinic(clinicName, clinicAddress, clinicPhoneNumber);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void createClinic(String name, String address, String phoneNumber){

        final String clinicName, clinicAddress, clinicPhoneNumber;
        clinicName = name;
        clinicAddress = address;
        clinicPhoneNumber = phoneNumber;

        if (TextUtils.isEmpty(addresstext.getText().toString().trim()) || TextUtils.isEmpty(phone_number.getText().toString().trim()) || TextUtils.isEmpty(clinic_name.getText().toString().trim())){
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
            return;
        }

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
            if (TextUtils.isEmpty(addresstext.getText().toString().trim()) || TextUtils.isEmpty(phone_number.getText().toString().trim()) || TextUtils.isEmpty(clinic_name.getText().toString().trim())){
                Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intentService = new Intent(getApplicationContext(), insuranceandpayment.class);
            startActivity(intentService);
        }
    }