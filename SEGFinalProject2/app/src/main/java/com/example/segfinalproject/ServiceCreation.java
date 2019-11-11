package com.example.segfinalproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceCreation extends AppCompatActivity {


    Spinner employeeMenu;
    EditText serviceName;
    ListView serviceList;
    Button buttonAddService;

    List<Service> services;

    DatabaseReference databaseServices;

    String[] employees = new String[]{"Doctor", "Nurse", "Staff"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);

        serviceList = (ListView) findViewById(R.id.serviceListID);
        buttonAddService = (Button) findViewById(R.id.addButton);
        serviceName = (EditText) findViewById(R.id.serviceNameID);
        employeeMenu = (Spinner) findViewById(R.id.employeeMenu);


        //create a list of items for the user to select from
        services = new ArrayList<>();

        //create and set the spinner to the adapter made that coincides with the created string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, employees);
        employeeMenu.setAdapter(adapter);

        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService(view);
            }
        });

        serviceList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog(service.getId(), service.getServiceName());
                return true;
            }
        });

    }

    @Override
    protected void onStart(){

        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                services.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    Service service = postSnapshot.getValue(Service.class);

                    services.add(service);

                }

                ServiceList servicesAdapter = new ServiceList(ServiceCreation.this, services);
                serviceList.setAdapter(servicesAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    private void showUpdateDeleteDialog(final String serviceId, String serviceName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_update_delete_popup, null);
        dialogBuilder.setView(dialogView);

        final EditText editServiceName = (EditText) dialogView.findViewById(R.id.popupServiceName);
        final Spinner editSpinner = (Spinner) dialogView.findViewById(R.id.popupSpinner);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, employees);
        editSpinner.setAdapter(adapter);

        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editServiceName.getText().toString().trim();
                String employee = (editSpinner.getSelectedItem().toString());
                if (!TextUtils.isEmpty(name)) {
                    updateService(serviceId, name, employee);
                    b.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "Enter Service Name", Toast.LENGTH_LONG).show();
                }
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

    private boolean updateService(String id, String name, String employee){

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("services").child(id);

        Service service = new Service(id, name, employee);
        dr.setValue(service);

        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
        return true;

    }

    private boolean deleteService(String id){

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("services").child(id);
        dr.removeValue();

        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;

    }

    public void addService(View view){

        String name = serviceName.getText().toString().trim();
        String employee = employeeMenu.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){

            Toast.makeText(getApplicationContext(), "Service Added", Toast.LENGTH_LONG).show();

            String id = databaseServices.push().getKey();

            Service service = new Service(id, name, employee);

            databaseServices.child(id).setValue(service);

            serviceName.setText("");


        }else{
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }

    }
}
