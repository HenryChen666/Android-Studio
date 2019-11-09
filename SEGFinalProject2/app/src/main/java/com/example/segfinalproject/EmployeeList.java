package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList extends AppCompatActivity {

    ListView employeeListView;
    DatabaseReference databaseEmployees;

    List<User> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        databaseEmployees = FirebaseDatabase.getInstance().getReference("users");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        employeeListView = (ListView) findViewById(R.id.employeeListView);

        employees = new ArrayList<>();

        employeeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                User employee = employees.get(i);
                showUpdateDeleteDialog(employee.getName(), employee.getEmail());
                return true;
            }
        });
    }

    @Override
    protected void onStart(){

        super.onStart();

        databaseEmployees.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employees.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    User employee = postSnapshot.getValue(User.class);

                    if(employee.getUsertype() == "Employee"){
                        employees.add(employee);
                    }

                }

                //ServiceList employeeAdapter = new ServiceList(this, employees);
                //employeeListView.setAdapter(employeeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));

    }

    private void showUpdateDeleteDialog(final String email, final String name){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

    }

}
