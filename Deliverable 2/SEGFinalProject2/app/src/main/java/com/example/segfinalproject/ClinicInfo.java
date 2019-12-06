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

    private TextView clinic_name, clinic_address, monday_hour, tuesday_hour, wendsday_hour, thursday_hour, friday_hour, saturday_hour, sunday_hour, totalRating;
    private Button book_appointment, reviewClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_info);

        Intent intent = getIntent();

        clinic_name = (TextView) findViewById(R.id.clinicinfoname);
        clinic_address = (TextView) findViewById(R.id.clinicinfoaddress);
        monday_hour = (TextView) findViewById(R.id.mondayhour);
        tuesday_hour = (TextView) findViewById(R.id.tuesdayhour);
        wendsday_hour = (TextView) findViewById(R.id.wednesdayhour);
        thursday_hour = (TextView) findViewById(R.id.thursdayhour);
        friday_hour = (TextView) findViewById(R.id.fridayhour);
        saturday_hour = (TextView) findViewById(R.id.saturdayhour);
        sunday_hour = (TextView) findViewById(R.id.sundayhour);
        book_appointment = (Button) findViewById(R.id.bookappointment);
        reviewClinic = (Button) findViewById(R.id.rateclinic);
        totalRating = (TextView) findViewById(R.id.clinicinforating);

        final String name = intent.getStringExtra("Name");
        final String address = intent.getStringExtra("Address");
        final String clinicId = intent.getStringExtra("id");

        clinic_name.setText(name);
        clinic_address.setText("Address: "+ address);

        getTotalRatings(clinicId);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clinics");
        //The path I added
        Query friday = databaseReference.child(clinicId);
        /////
        friday.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.hasChild("hours")) {
                        String monday_end = dataSnapshot.child("hours").child("monday").child("end").getValue(String.class);
                        String monday_start = dataSnapshot.child("hours").child("monday").child("start").getValue(String.class);
                        monday_hour.setText("Monday: " + monday_start + " - " + monday_end);
                        String tuesday_end = dataSnapshot.child("hours").child("tuesday").child("end").getValue(String.class);
                        String tuesday_start = dataSnapshot.child("hours").child("tuesday").child("start").getValue(String.class);
                        tuesday_hour.setText("Tuesday: " + tuesday_start + " - " + tuesday_end);
                        String wednesday_end = dataSnapshot.child("hours").child("wednesday").child("end").getValue(String.class);
                        String wednesday_start = dataSnapshot.child("hours").child("wednesday").child("start").getValue(String.class);
                        wendsday_hour.setText("Wednesday: " + wednesday_start + " - " + wednesday_end);
                        String thursday_end = dataSnapshot.child("hours").child("thursday").child("end").getValue(String.class);
                        String thursday_start = dataSnapshot.child("hours").child("thursday").child("start").getValue(String.class);
                        thursday_hour.setText("Thursday: " + thursday_start + " - " + thursday_end);
                        String friday_end = dataSnapshot.child("hours").child("friday").child("end").getValue(String.class);
                        String friday_start = dataSnapshot.child("hours").child("friday").child("start").getValue(String.class);
                        friday_hour.setText("Friday: " + friday_start + " - " + friday_end);
                        String saturday_end = dataSnapshot.child("hours").child("saturday").child("end").getValue(String.class);
                        String saturday_start = dataSnapshot.child("hours").child("saturday").child("start").getValue(String.class);
                        saturday_hour.setText("Saturday: " + saturday_start + " - " + saturday_end);
                        String sunday_end = dataSnapshot.child("hours").child("sunday").child("end").getValue(String.class);
                        String sunday_start = dataSnapshot.child("hours").child("sunday").child("start").getValue(String.class);
                        sunday_hour.setText("Sunday: " + sunday_start  + " - " + sunday_end);

                    }else{
                        monday_hour.setText("Friday: " + "closed" + " - " + "closed");
                        tuesday_hour.setText("Friday: " + "closed" + " - " + "closed");
                        wendsday_hour.setText("Friday: " + "closed" + " - " + "closed");
                        thursday_hour.setText("Friday: " + "closed" + " - " + "closed");
                        friday_hour.setText("Friday: " + "closed" + " - " + "closed");
                        saturday_hour.setText("Friday: " + "closed" + " - " + "closed");
                        sunday_hour.setText("Friday: " + "closed" + " - " + "closed");
                    }

                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        book_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), bookappointment.class);
                intent.putExtra("clinic_id", clinicId);
                startActivity(intent);
            }
        });

        reviewClinic.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), CreateReview.class);
                intent.putExtra("id", clinicId);
                intent.putExtra("name", name);
                startActivity(intent);
            }

        });


    }

    public void getTotalRatings(String id){

        final DatabaseReference ratingRef = FirebaseDatabase.getInstance().getReference("clinics").child(id);

        ratingRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild("ratings")){

                    double total;
                    int temp = 0;
                    int count = 0;

                    for(DataSnapshot review: dataSnapshot.child("ratings").getChildren()){

                        count++;
                        temp += review.child("rating").getValue(Long.class);



                    }

                    total = temp/count;

                    totalRating.setText("Rating: " + total + "/5");


                }else{

                    totalRating.setText("No Ratings");

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
