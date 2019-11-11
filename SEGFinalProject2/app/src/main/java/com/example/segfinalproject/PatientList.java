package com.example.segfinalproject;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientList extends AppCompatActivity {

    ListView patientListView;
    DatabaseReference databasePatients;
    private FirebaseDatabase firebaseDataBase;
    List<User> patients;
    EmployeeList eList = new EmployeeList();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        databasePatients = FirebaseDatabase.getInstance().getReference("User");
        firebaseDataBase = FirebaseDatabase.getInstance();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_list_view);

        patientListView = (ListView) findViewById(R.id.patientListView);

        patients = new ArrayList<>();

        patientListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User patient = patients.get(position);
                showUpdateDeleteDialog(patient.getEmail(), patient.getName(), patient.getId(), patient.getPassword());
                return true;
            }
        });

    }

    @Override
    protected void onStart(){

        super.onStart();

        databasePatients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                patients.clear();

                for(DataSnapshot post: dataSnapshot.getChildren()){

                    User patient = post.getValue(User.class);

                    if(patient.getUsertype().equals("Patient")){
                        patients.add(patient);
                    }

                }

                UserList patientAdapter = new UserList(PatientList.this, patients);
                patientListView.setAdapter(patientAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void showUpdateDeleteDialog(final String email, final String name, final String id, final String password){

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
                deleteUser(id, email, password);
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

    private boolean deleteUser(final String id, String email, final String password){

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("User").child(id);
        dr.removeValue();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
        FirebaseAuth.getInstance().getCurrentUser().delete();

        Toast.makeText(getApplicationContext(), "Patient Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
}

