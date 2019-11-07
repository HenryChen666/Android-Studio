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

public class CreateAccount extends AppCompatActivity  implements View.OnClickListener{

    private EditText username;
    private EditText useremail;
    private EditText userpassword;
    private Spinner usertype;
    private Button register;
    private Button login;

    private FirebaseAuth firebaseauth;
    private ProgressBar progress;

    String type[] = new String[]{"Employee", "Patient"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        usertype = (Spinner) findViewById(R.id.usertype);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,type);
        usertype.setAdapter(adapter);


        firebaseauth = FirebaseAuth.getInstance();
        progress = new ProgressBar(this);

        username = (EditText) findViewById(R.id.username);
        useremail = (EditText) findViewById(R.id.useremail);
        userpassword = (EditText) findViewById(R.id.userpassword);
        register = (Button) findViewById(R.id.createAccount);
        login = (Button) findViewById(R.id.login);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    public void AlreadyRegister(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent,0);
    }

    private void signupUser(){
        String name = username.getText().toString().trim();
        String email = useremail.getText().toString().trim();
        String password = userpassword.getText().toString().trim();
//        String type = usertype.getSelectedItem().toString().trim();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please fill all the Required information", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length()<6){
            Toast.makeText(this, "password should be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        progress.setVisibility(View.VISIBLE);

        firebaseauth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            Toast.makeText(CreateAccount.this,"Register Successful",Toast.LENGTH_SHORT).show();
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
