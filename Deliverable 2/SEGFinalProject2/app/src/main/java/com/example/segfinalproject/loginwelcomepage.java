package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginwelcomepage extends AppCompatActivity {
    private TextView welcomedisply;
    private Button mainmenu, manageServices;

    private FirebaseAuth firebaseauth;
    private FirebaseDatabase firebasedatabase;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwelcomepage);

        welcomedisply = (TextView) findViewById(R.id.logindisplaywelcome);
        mainmenu = (Button) findViewById(R.id.loginwelcome);
        manageServices = (Button) findViewById(R.id.manageServiesBtn);

        firebaseauth = FirebaseAuth.getInstance();
        firebasedatabase = FirebaseDatabase.getInstance();
        user = firebaseauth.getCurrentUser();



        DatabaseReference databasereference = firebasedatabase.getReference("User");
        Query query = databasereference.orderByChild("email").equalTo(user.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String username = "" +  ds.child("name").getValue();
                    String usertype = "" + ds.child("usertype").getValue();

                    welcomedisply.setText("Welcome "+ username + "! You are logged in as " + usertype);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlreadyRegister();
            }
        });

        manageServices.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ManageServices();
            }
        });
    }

    public void AlreadyRegister(){

        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void ManageServices(){
        Intent intent = new Intent(getApplicationContext(), EmployeeAddServices.class);
        startActivity(intent);
    }

    @Override
    public void onStart(){
        super.onStart();
        if (firebaseauth.getCurrentUser() == null){
            finish();
            Intent intentwelcome = new Intent(this, MainActivity.class);
            startActivity(intentwelcome);
        }
    }
}
