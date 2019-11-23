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
    private Button mainMenu, profile, manageServices, clinicinfo;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwelcomepage);

        welcomeDisply = (TextView) findViewById(R.id.logindisplaywelcome);
        mainMenu = (Button) findViewById(R.id.loginwelcome);
        profile = (Button) findViewById(R.id.editprofile);
        manageServices = (Button) findViewById(R.id.mangeservicesbtn);
        clinicinfo = (Button) findViewById(R.id.clinicinfo);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser();



        DatabaseReference databasereference = firebaseDatabase.getReference("User");
        Query query = databasereference.orderByChild("email").equalTo(user.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String username = "" +  ds.child("name").getValue();
                    String usertype = "" + ds.child("usertype").getValue();

                    welcomeDisply.setText("Welcome "+ username + "! You are logged in as " + usertype);
                }
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

        manageServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageServices();
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

    public void manageServices(){
        Intent intent = new Intent(getApplicationContext(), EmployeeAddServices.class);
        startActivity(intent);
    }

    public void clinicinfo(){
        final DatabaseReference clinicCheck = FirebaseDatabase.getInstance().getReference("User").child(id);

        clinicCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("clinic")) {

                    Intent intent = new Intent(getApplicationContext(), ClinicInformation.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getBaseContext(), "No clinic is associated to your account", Toast.LENGTH_SHORT).show();show();
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
