package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        databaseEmployees = FirebaseDatabase.getInstance().getReference("User");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_view);

        employeeListView = (ListView) findViewById(R.id.employeeListView);

        employees = new ArrayList<>();

        employeeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                User employee = employees.get(i);
                showUpdateDeleteDialog(employee.getName(), employee.getEmail(), employee.getId());
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

                for(DataSnapshot post: dataSnapshot.getChildren()){

                    User employee = post.getValue(User.class);

                    if(employee.getUsertype().equals("Employee")) {
                        employees.add(employee);
                    }

                }

                UserList employeeAdapter = new UserList(EmployeeList.this, employees);
                employeeListView.setAdapter(employeeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));

    }

    private void showUpdateDeleteDialog(final String email, final String name, final String id){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView2 = inflater.inflate(R.layout.delete_user, null);
        dialogBuilder.setView(dialogView2);

        final TextView userName = (TextView) dialogView2.findViewById(R.id.userNameTxt);
        final TextView userEmail = (TextView) dialogView2.findViewById(R.id.userEmailTxt);
        final Button deleteButton = (Button) dialogView2.findViewById(R.id.deleteUserButton);
        final Button backButton = (Button) dialogView2.findViewById(R.id.backButton);

        userName.setText(name);
        userEmail.setText(email);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        deleteButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                deleteUser(id);
                b.dismiss();
            }

        });

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                b.dismiss();
            }

        });

    }

    private boolean deleteUser(String id){

        Toast.makeText(getApplicationContext(), "Employee Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}
