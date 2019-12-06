package com.example.segfinalproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    String[] choices = new String[]{"Address", "Service"};
    String[] day = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};

    EditText addressService, startHour, startMin, endHour, endMin;
    Spinner days, addressSpinner;
    ListView results;
    Button addressButton, hoursButton;
    ArrayList<Clinic> clinics;

    DatabaseReference clinicRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        clinicRef = FirebaseDatabase.getInstance().getReference("clinics");

        addressService = (EditText) findViewById(R.id.addressText);
        startHour = (EditText) findViewById(R.id.StartHour);
        startMin = (EditText) findViewById(R.id.StartMin);
        endHour = (EditText) findViewById(R.id.EndHour);
        endMin = (EditText) findViewById(R.id.EndMin);

        days = (Spinner) findViewById(R.id.days);

        //create and set the spinner to the adapter made that coincides with the created string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, day);
        days.setAdapter(adapter);


        addressSpinner = (Spinner) findViewById(R.id.typeOfSearch);

        //create and set the spinner to the adapter made that coincides with the created string array
        ArrayAdapter<String> addressAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        addressSpinner.setAdapter(addressAdapter);

        results = (ListView) findViewById(R.id.ClinicResults);

        addressButton = (Button) findViewById(R.id.addressButton);
        hoursButton = (Button) findViewById(R.id.hoursButton);

        clinics = new ArrayList<>();

        results.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Clinic temp = clinics.get(i);
                Intent clinicPage = new Intent(getApplicationContext(),ClinicInfo.class);
                clinicPage.putExtra("Name", temp.getName());
                clinicPage.putExtra("Address",temp.getAddress());
                clinicPage.putExtra("Phone Number", temp.getPhoneNumber());
                clinicPage.putExtra("id", temp.getId());
                startActivity(clinicPage);

                return true;





            }
        });

        hoursButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getResultsHours();
            }
        });

        addressButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getResultsText();
            }
        });

        
    }

    public void getResultsHours(){

        if (TextUtils.isEmpty(startHour.getText().toString().trim()) || TextUtils.isEmpty(startMin.getText().toString().trim()) || TextUtils.isEmpty(endHour.getText().toString().trim()) ||TextUtils.isEmpty(endMin.getText().toString().trim())){
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
            return;
        }

        final int start = Integer.parseInt(startHour.getText().toString() + startMin.getText().toString());
        final int end = Integer.parseInt(endHour.getText().toString()  + endMin.getText().toString());

        final String day = days.getSelectedItem().toString().toLowerCase();

        clinicRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                clinics.clear();

                for(DataSnapshot post: dataSnapshot.getChildren()){

                    if(post.hasChild("hours")) {
                        DataSnapshot temp = post.child("hours").child(day);
                        if (!temp.child("start").getValue(String.class).equals("closed")) {
                            int tempStart = Integer.parseInt(temp.child("start").getValue(String.class));
                            int tempEnd = Integer.parseInt(temp.child("end").getValue(String.class));


                            if (start >= tempStart || end <= tempEnd) {

                                String name = post.child("name").getValue(String.class);
                                String address = post.child("address").getValue(String.class);
                                String number = post.child("phoneNumber").getValue(String.class);
                                String id = post.child("id").getValue(String.class);

                                Clinic clinic = new Clinic(name,address,number,id);

                                clinics.add(clinic);
                            }
                        }
                    }

                }

                if(clinics.size() == 0){
                    Toast.makeText(getApplicationContext(), "No Clinics Found", Toast.LENGTH_SHORT).show();
                }

                ClinicList clinicAdapter = new ClinicList(Search.this, clinics);
                results.setAdapter(clinicAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getResultsText(){
        if (TextUtils.isEmpty(addressService.getText().toString().trim()) ){
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
            return;
        }

        String search = addressSpinner.getSelectedItem().toString().toLowerCase();

        if(search.equals("address")) {
            clinicRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    clinics.clear();

                    for(DataSnapshot post: dataSnapshot.getChildren()){

                        if(addressService.getText().toString().equals(post.child("address").getValue(String.class))){
                            String name = post.child("name").getValue(String.class);
                            String address = post.child("address").getValue(String.class);
                            String number = post.child("phoneNumber").getValue(String.class);
                            String id = post.child("id").getValue(String.class);

                            Clinic clinic = new Clinic(name,address,number,id);

                            clinics.add(clinic);
                        }

                    }

                    if(clinics.size() == 0){
                        Toast.makeText(getApplicationContext(), "No Clinics Found", Toast.LENGTH_SHORT).show();
                    }

                    ClinicList clinicAdapter = new ClinicList(Search.this, clinics);
                    results.setAdapter(clinicAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{

            clinicRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    clinics.clear();

                        for (DataSnapshot clinic : dataSnapshot.getChildren()) {

                            if(clinic.hasChild("services")) {

                                for (DataSnapshot service : clinic.child("services").getChildren()) {

                                    if (addressService.getText().toString().equals(service.child("serviceName").getValue(String.class))) {

                                        String name = clinic.child("name").getValue(String.class);
                                        String address = clinic.child("address").getValue(String.class);
                                        String number = clinic.child("phoneNumber").getValue(String.class);
                                        String id = clinic.child("id").getValue(String.class);

                                        Clinic clinicss = new Clinic(name, address, number, id);

                                        clinics.add(clinicss);

                                    }


                                }
                            }

                    }

                    if(clinics.size() == 0){
                        Toast.makeText(getApplicationContext(), "No Clinics Found", Toast.LENGTH_SHORT).show();
                    }

                    ClinicList clinicAdapter = new ClinicList(Search.this, clinics);
                    results.setAdapter(clinicAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
}
