package com.example.deliverable1_group7;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccountFirebase extends AppCompatActivity {
    DatabaseReference databaseAccount;
    EditText username;
    EditText userEmail;
    EditText userpassword;
    Spinner userType;
    Button adduser1;

    List<User> accounts;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_account_creation_screen);
        databaseAccount = FirebaseDatabase.getInstance().getReference("account");
        username =(EditText)findViewById(R.id.usernameLogin);
        userEmail = (EditText) findViewById(R.id.emailLogin);
        userpassword = (EditText) findViewById(R.id.passwordLogin);
        adduser1 = (Button) findViewById(R.id.createAccountBtn1);
        userType = (Spinner) findViewById(R.id.spinner1);

//        accounts = new ArrayList<>();

        adduser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAccount();
            }
        });

    }

//    @Override
//       protected void onStart(){
//            super.onStart();
//            databaseAccount.addValueEventListener(new ValueEventListener(){
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot){
//                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                        User account = postSnapshot.getValue(User.class);
//                        accounts.add(account);  //Add the account to the list
//                    }
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//    }
        public void addAccount(){
            String name = username.getText().toString();
            String email = userEmail.getText().toString();
            String password = userpassword.getText().toString();
            AccountCreationScreen pq = new AccountCreationScreen();
            int type = pq.getUserType();

            //if (!TextUtils.isEmpty(name)){
                String id = databaseAccount.push().getKey();
                User user = new User(id,name,email,password,type);
                databaseAccount.child(id).setValue(user);
                username.setText("");
                userEmail.setText("");
                userpassword.setText("");
                Toast.makeText(this, "User Added", Toast.LENGTH_LONG).show();

//            }else{
//                Toast.makeText(this, "unsuccessful", Toast.LENGTH_LONG).show();
//            }
        }
}
