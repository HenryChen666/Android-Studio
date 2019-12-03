package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateAccount extends AppCompatActivity  implements View.OnClickListener{

    private EditText username;
    private EditText useremail;
    private EditText userpassword;
    private Spinner usertype;
    private Button register;
    private Button login;

    private FirebaseAuth firebaseauth;

    String type[] = new String[]{"Employee", "Patient"};
    ArrayAdapter<String> adapter;

    String welcomename, welcometype;

    String checkemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // Email Pattern

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        usertype = (Spinner) findViewById(R.id.usertype);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,type);
        usertype.setAdapter(adapter);


        firebaseauth = FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.username);
        useremail = (EditText) findViewById(R.id.useremail);
        userpassword = (EditText) findViewById(R.id.userpassword);
        register = (Button) findViewById(R.id.createAccount);
        login = (Button) findViewById(R.id.login);

        register.setOnClickListener(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlreadyRegister(v);
            }
        });



    }


    public void AlreadyRegister(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void signupUser(){
        final String name = username.getText().toString().trim();
        final String email = useremail.getText().toString().trim();
        final String password = userpassword.getText().toString().trim();
        final String type = usertype.getSelectedItem().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
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




        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            List<List> hours = new ArrayList<>();
                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            User user = new User(name, email,type, id, password);

                           // Toast.makeText(CreateAccount.this,"Authentication Successful",Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(CreateAccount.this,"Register Successful",Toast.LENGTH_SHORT).show();
                                }
                            });

                            FirebaseDatabase.getInstance().getReference("User").child("services").push();
                            Intent intentwelcome = new Intent(getApplicationContext(), Welcomepage.class);
                            welcomename = name;
                            welcometype = type;
                            intentwelcome.putExtra("Name",welcomename);
                            intentwelcome.putExtra("Type",welcometype);
                            startActivity(intentwelcome);

                        }else{
                            Toast.makeText(CreateAccount.this,"Register Unsuccessful, Please Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == register){
            signupUser();
        }
    }

}
