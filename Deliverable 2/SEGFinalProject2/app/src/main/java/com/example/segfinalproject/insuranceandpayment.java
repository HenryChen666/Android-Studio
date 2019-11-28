package com.example.segfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class insuranceandpayment extends AppCompatActivity {

    private CheckBox insurance1, insurance2, insurance3;
    private CheckBox payment1, payment2, payment3;
    private Button save;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insuranceandpayment);

        insurance1 = (CheckBox) findViewById(R.id.insurancetype1);
        insurance2 = (CheckBox) findViewById(R.id.insurancetype2);
        insurance3 = (CheckBox) findViewById(R.id.insurancetype3);
        payment1 = (CheckBox) findViewById(R.id.paymentmethod1);
        payment2 = (CheckBox) findViewById(R.id.paymentmethod2);
        payment3 = (CheckBox) findViewById(R.id.paymentmethod3);
        save = (Button) findViewById(R.id.save);

        insurance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insurance1.isChecked()){
                    Toast.makeText(getBaseContext(), "Personal health insurance Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Personal health insurance Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        insurance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insurance2.isChecked()){
                    Toast.makeText(getBaseContext(), "Critical illness insurance Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Critical illness insurance Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        insurance3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insurance3.isChecked()){
                    Toast.makeText(getBaseContext(), "Disability insurance Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Disability insurance Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payment1.isChecked()){
                    Toast.makeText(getBaseContext(), "Debit Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Debit Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        payment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payment2.isChecked()){
                    Toast.makeText(getBaseContext(), "Visa Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Visa Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        payment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payment3.isChecked()){
                    Toast.makeText(getBaseContext(), "Cash Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Cash Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });
    }

    public void save(View view){
        if (!payment1.isChecked() && !payment2.isChecked() && !payment3.isChecked()){
            Toast.makeText(this, "Please enter at least one payment info", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!insurance1.isChecked() && !insurance2.isChecked() && !insurance3.isChecked()){
            Toast.makeText(this, "Please enter at least one insurance info", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Saved Changes", Toast.LENGTH_SHORT).show();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser();

        DatabaseReference databasereference = firebaseDatabase.getReference("User");
        Query query = databasereference.orderByChild("email").equalTo(user.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String clinic_id = "" + ds.child("clinic").getValue();
                    final DatabaseReference clinicdatabase = firebaseDatabase.getReference("clinic");
                    final Query clinicquery = clinicdatabase.orderByChild("id").equalTo(clinic_id);

                    clinicdatabase.child("Personal health insurance").setValue(insurance1.isChecked());
                    clinicdatabase.child("Critical illness insurance").setValue(insurance2.isChecked());
                    clinicdatabase.child("Disability insurance").setValue(insurance3.isChecked());
                    clinicdatabase.child("Debit").setValue(payment1.isChecked());
                    clinicdatabase.child("Visa").setValue(payment2.isChecked());
                    clinicdatabase.child("Cash").setValue(payment3.isChecked());

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
