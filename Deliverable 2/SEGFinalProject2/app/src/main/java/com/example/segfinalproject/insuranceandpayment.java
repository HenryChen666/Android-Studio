package com.example.segfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class insuranceandpayment extends AppCompatActivity {

    private CheckBox insurance1, insurance2, insurance3;
    private CheckBox payment1, payment2, payment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insuranceandpayment);

        insurance1 = (CheckBox) findViewById(R.id.insurancetype1);
        insurance2 = (CheckBox) findViewById(R.id.insurancetype2);
        insurance3 = (CheckBox) findViewById(R.id.insurancetype3);
        payment1 = (CheckBox) findViewById(R.id.paymentmethod1);
        payment2 = (CheckBox) findViewById(R.id.paymentmethod2);
        payment3 = (CheckBox) findViewById(R.id.paymentmethod3);

        insurance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insurance1.isChecked()){
                    Toast.makeText(getBaseContext(), "Personal health insurance Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Personal health insurance Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        insurance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insurance2.isChecked()){
                    Toast.makeText(getBaseContext(), "Critical illness insurance Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Critical illness insurance Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        insurance3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insurance3.isChecked()){
                    Toast.makeText(getBaseContext(), "Disability insurance Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Disability insurance Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payment1.isChecked()){
                    Toast.makeText(getBaseContext(), "Debit Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Debit Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        payment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payment2.isChecked()){
                    Toast.makeText(getBaseContext(), "Visa Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Visa Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        payment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payment3.isChecked()){
                    Toast.makeText(getBaseContext(), "Cash Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Cash Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
