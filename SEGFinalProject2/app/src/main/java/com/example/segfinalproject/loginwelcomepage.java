package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginwelcomepage extends AppCompatActivity {
    TextView welcomedisply;
    Button mainmenu;

    DatabaseReference userref;
    String currentuserID;

    private FirebaseAuth firebaseauth;
    private String username;
    private String usertype;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwelcomepage);

        mainmenu = (Button) findViewById(R.id.loginwelcome);
        firebaseauth = FirebaseAuth.getInstance();
        userref = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseauth.getCurrentUser();
        currentuserID = user.getUid();

        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        welcomedisply = (TextView) findViewById(R.id.logindisplaywelcome);
        welcomedisply.setText("Welcome " + username+ "! You are logged-in as " + usertype);
    }



}
