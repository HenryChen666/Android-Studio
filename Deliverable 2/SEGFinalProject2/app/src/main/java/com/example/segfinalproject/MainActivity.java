package com.example.segfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{

    private EditText useremail;
    private EditText userpassword;
    private Button login;
    private Button signup;

    private FirebaseAuth firebaseauth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser user;

    String checkemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // Email Pattern

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseauth = FirebaseAuth.getInstance();
        useremail = (EditText) findViewById(R.id.mainusermeail);
        userpassword = (EditText) findViewById(R.id.userpasswordlogin);
        login = (Button) findViewById(R.id.mainlogin);
        signup = (Button) findViewById(R.id.createAccountBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        user = firebaseauth.getCurrentUser();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(v);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnCreateAccount(v);
            }
        });


    }

    private void loginUser(View view){
        final String email = useremail.getText().toString().trim();
        String password = userpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your Email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // check admin username and password
        if (email.equals("admin") && password.equals("5T5ptQ")){
            Toast.makeText(MainActivity.this, "Logged in as Admin", Toast.LENGTH_SHORT).show();
            serviceCreation(view);
            return;
        }
        if (!email.matches(checkemail)){
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length()<6){
            Toast.makeText(this, "Password should be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        
        firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    DatabaseReference databasereference = firebaseDatabase.getReference("User");
                    Query query = databasereference.orderByChild("email").equalTo(email);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()){
                                String usertype = "" + ds.child("usertype").getValue();
                                if (!usertype.equals("Patient")){
                                    Toast.makeText(MainActivity.this, "Employee", Toast.LENGTH_SHORT).show();
                                    Intent intentwelcome = new Intent(getApplicationContext(), loginwelcomepage.class);
                                    startActivity(intentwelcome);
                                    return;
                                }else{
                                    Toast.makeText(MainActivity.this, "patients", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), patientsloginwelcomepage.class);
                                    startActivity(intent);
                                    return;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else{
                    Toast.makeText(MainActivity.this, "Login Unsuccessful, Please check your Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void serviceCreation(View view){
        Intent intentService = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(intentService);


    }


    public void OnCreateAccount(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivityForResult(intent, 0);
    }
}
