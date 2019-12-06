package com.example.segfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bookappointment extends AppCompatActivity {

    ListView selecte_services;
    List<Service> list_services;
    DatabaseReference get_services, clinic_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);


        String id = getIntent().getStringExtra("clinic_id");
        selecte_services = (ListView) findViewById(R.id.appointmentlist);
        get_services = FirebaseDatabase.getInstance().getReference("clinics").child(id).child("services");
        list_services = new ArrayList<>();

        selecte_services.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = list_services.get(position);
                showBookingDialog(service.getId(),service.getServiceName())
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        get_services.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list_services.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Service service = ds.getValue(Service.class);
                    list_services.add(service);
                }
                ServiceList servicesAdapter = new ServiceList(bookappointment.this, list_services);
                selecte_services.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
