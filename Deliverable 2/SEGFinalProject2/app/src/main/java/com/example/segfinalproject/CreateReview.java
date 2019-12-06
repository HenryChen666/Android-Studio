package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
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

public class CreateReview extends AppCompatActivity {

    private EditText reviewerName,comment;
    private TextView clinicName;
    private RatingBar rating;
    private Button submit;
    private boolean rated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_review);

        Intent intent = getIntent();

        final String id = intent.getStringExtra("id");
        final String name = intent.getStringExtra("name");

        clinicName = (TextView) findViewById(R.id.clinicName);

        clinicName.setText("Clinic Name: " + name);

        reviewerName = (EditText) findViewById(R.id.nameInput);
        comment = (EditText) findViewById(R.id.commentInput);
        rating = (RatingBar) findViewById(R.id.ratingInput);

        submit = (Button) findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                clickSumbit(v,id);
            }
        });

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rated = true;
            }
        });

    }

    public void clickSumbit(View view, String id){


        DatabaseReference ratingRef = FirebaseDatabase.getInstance().getReference("clinics").child(id).child("ratings").push();

        if (TextUtils.isEmpty(reviewerName.getText()) || TextUtils.isEmpty(comment.getText()) || !rated){
            Toast.makeText(getApplicationContext(), "Please enter all information", Toast.LENGTH_SHORT).show();
            return;
        }


        ratingRef.child("name").setValue(reviewerName.getText().toString());
        ratingRef.child("rating").setValue(rating.getRating());
        ratingRef.child("comment").setValue(comment.getText().toString());

        
    }
}
