package com.example.segfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class loginwelcomepage extends AppCompatActivity {
    private TextView welcomeDisply;
    private Button mainMenu, profile, clinicinfo;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwelcomepage);

        welcomeDisply = (TextView) findViewById(R.id.logindisplaywelcome);
        mainMenu = (Button) findViewById(R.id.loginwelcome);
        profile = (Button) findViewById(R.id.editprofile);
        clinicinfo = (Button) findViewById(R.id.clinicinfo);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();



        DatabaseReference databasereference = firebaseDatabase.getReference("User");
        Query query = databasereference.child(userId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String username = "" +  dataSnapshot.child("name").getValue(String.class);
                    String usertype = "" + dataSnapshot.child("usertype").getValue(String.class);

                    welcomeDisply.setText("Welcome "+ username + "! You are logged in as " + usertype);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlreadyRegister();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        clinicinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clinicinfo();
            }
        });


    }

    public void AlreadyRegister(){

        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    public void editProfile(){
        Intent intent = new Intent(getApplicationContext(), EmployeeProfile.class);
        startActivity(intent);
    }

    public void clinicinfo(){
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final DatabaseReference clinicCheck = FirebaseDatabase.getInstance().getReference("User").child(userId);

        clinicCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("clinic")) {

                    Intent intent = new Intent(getApplicationContext(), ClinicInformation.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getBaseContext(), "No clinic is associated to your account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            Intent intentwelcome = new Intent(this, MainActivity.class);
            startActivity(intentwelcome);
        }
    }
}
