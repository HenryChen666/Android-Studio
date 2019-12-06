package com.example.segfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ClinicInfo extends AppCompatActivity {

    private TextView clinic_name, clinic_address, friday_hour;
    private Button book_appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_info);

        Intent intent = getIntent();

        clinic_name = (TextView) findViewById(R.id.clinicinfoname);
        clinic_address = (TextView) findViewById(R.id.clinicinfoaddress);
        friday_hour = (TextView) findViewById(R.id.fridayhour);
        book_appointment = (Button) findViewById(R.id.bookappointment);

        String name = intent.getStringExtra("Name");
        final String address = intent.getStringExtra("Address");
        final String clinicId = intent.getStringExtra("id");

        clinic_name.setText(name);
        clinic_address.setText("Address: "+ address);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clinics");
        //The path I added
        Query friday = databaseReference.child(clinicId);
        /////
        friday.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.hasChild("hours")) {
                        String friday_end = dataSnapshot.child("hours").child("monday").child("end").getValue(String.class);
                        String friday_start = dataSnapshot.child("hours").child("tuesday").child("start").getValue(String.class);
                        friday_hour.setText("Friday: " + friday_start + " - " + friday_end);
                    }else{
                        friday_hour.setText("Friday: " + "closed" + " - " + "closed");


                    }

                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        book_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookappointmentbtn();
            }
        });
    }
    public void bookappointmentbtn(){
        Intent intent = new Intent(getApplicationContext(), bookappointment.class);
        startActivity(intent);
    }
}
