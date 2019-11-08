package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity{

    private EditText useremail;
    private EditText userpassword;
    private Button login;
    private Button signup;

    private FirebaseAuth firebaseauth;

    DatabaseReference userref;
    String currentuserID;

    private String username;
    private String usertype;
    String welcomename, welcometype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseauth = FirebaseAuth.getInstance();
        useremail = (EditText) findViewById(R.id.mainusermeail);
        userpassword = (EditText) findViewById(R.id.userpasswordlogin);
        login = (Button) findViewById(R.id.mainlogin);
        signup = (Button) findViewById(R.id.createAccountBtn);

        FirebaseUser user = firebaseauth.getCurrentUser();
        currentuserID = user.getUid();
        userref = FirebaseDatabase.getInstance().getReference();


        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnCreateAccount(v);
            }
        });


    }

    private void showData(DataSnapshot datasnapshot){
        for(DataSnapshot postSnapshot: datasnapshot.getChildren()){
            User userinformation = new User();
            userinformation.setEmail(postSnapshot.child(currentuserID).getValue(User.class).getEmail());
            userinformation.setName(postSnapshot.child(currentuserID).getValue(User.class).getName());
            userinformation.setUsertype(postSnapshot.child(currentuserID).getValue(User.class).getUsertype());

            username = userinformation.getName();
            usertype = userinformation.getUsertype();
        }
    }

    private void loginuser(){
        String email = useremail.getText().toString().trim();
        String password = userpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your Email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intentwelcome = new Intent(getApplicationContext(), Welcomepage.class);
                welcomename = username;
                welcometype = usertype;
                intentwelcome.putExtra("Name",welcomename);
                intentwelcome.putExtra("Type",welcometype);
                startActivity(intentwelcome);
            }
        });
    }
    public void OnCreateAccount(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivityForResult(intent, 0);
    }
}
