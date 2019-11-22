package com.example.segfinalproject;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class workhours extends AppCompatActivity {

    Switch mondayswt, tuesdayswt, wednesdayswt, thursdayswt, fridayswt, saturdayswy, sundayswt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workhours);

        mondayswt = (Switch) findViewById(R.id.monday);
        tuesdayswt = (Switch) findViewById(R.id.tuesday);
        wednesdayswt = (Switch) findViewById(R.id.wednesday);
        thursdayswt = (Switch) findViewById(R.id.thursday);
        fridayswt = (Switch) findViewById(R.id.friday);
        saturdayswy = (Switch) findViewById(R.id.saturday);
        sundayswt = (Switch) findViewById(R.id.sunday);

        mondayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "turn on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "turn off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tuesdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "turn on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "turn off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        wednesdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "turn on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "turn off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        thursdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "turn on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "turn off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fridayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "turn on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "turn off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saturdayswy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "turn on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "turn off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sundayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "turn on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "turn off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
