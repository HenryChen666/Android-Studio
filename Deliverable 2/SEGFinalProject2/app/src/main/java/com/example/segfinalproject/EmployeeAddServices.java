package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAddServices extends AppCompatActivity {

    ListView employeeServices, availableServices;

    DatabaseReference databaseAvailableServices, employeeReference, addService, temp;

    List<Service> listEmployeeServices, listAvailableServices;
    Service service;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Gets path of list of services created by the admin
        databaseAvailableServices = FirebaseDatabase.getInstance().getReference("services");

        //Get path of the current user
        employeeReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        //Gets path of that users services
        addService = employeeReference.child("services");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_services);

        employeeServices = (ListView) findViewById(R.id.EmployeeServices);
        availableServices = (ListView) findViewById(R.id.AvailableServices);

        listEmployeeServices = new ArrayList<>();
        listAvailableServices = new ArrayList<>();

        availableServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //This makes sure that the user's id String in the object and the actual id are the same (otherwise deleting doesn't work)
                Service service = listAvailableServices.get(i);
                temp = addService.push();
                key = temp.getKey();
                service.setId(key);

                //adds service object to list
                temp.setValue(service);

                return true;
            }
        });

        employeeServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Service service = listEmployeeServices.get(i);
                showUpdateDeleteDialog(service.getId(), service.getServiceName(), service.getServiceEmployee());
                return true;
            }
        });

    }

    @Override
    protected void onStart(){

        super.onStart();

        addService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listEmployeeServices.clear();

                for(DataSnapshot ps: dataSnapshot.getChildren()){

                    Service service = ps.getValue(Service.class);

                    listEmployeeServices.add(service);

                }

                ServiceList servicesAdapter = new ServiceList(EmployeeAddServices.this, listEmployeeServices);
                employeeServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseAvailableServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listAvailableServices.clear();

                for(DataSnapshot ps: dataSnapshot.getChildren()){

                    Service service = ps.getValue(Service.class);

                    listAvailableServices.add(service);

                }

                ServiceList servicesAdapter = new ServiceList(EmployeeAddServices.this, listAvailableServices);
                availableServices.setAdapter(servicesAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showUpdateDeleteDialog(final String serviceId, String serviceName, String employee){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.employee_service_delete, null);
        dialogBuilder.setView(dialogView);

        //initializes buttons
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deleteButton);
        final Button buttonBack = (Button) dialogView.findViewById(R.id.backButton);

        //shows popup screen
        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        //sets button functionality
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
                b.dismiss();
            }

        });

    }

    public boolean deleteService(String id){

        employeeReference.child("services").child(id).removeValue();

        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}
