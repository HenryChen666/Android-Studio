package com.example.segfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ClinicHours extends AppCompatActivity {

    Switch mondayswt, tuesdayswt, wednesdayswt, thursdayswt, fridayswt, saturdayswy, sundayswt;
    TextView mondaystarthour, mondayendhour;
    TextView tuesdaystarthour, tuesdayendhour;
    TextView wednesdaystarthour, wednesdayendhour;
    TextView thursdaystarthour, thursdayendhour;
    TextView fridaystarthour, fridayendhour;
    TextView saturdaystarthour, saturdayendhour;
    TextView sundaystarthour, sundayendhour;

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

        mondaystarthour = (TextView) findViewById(R.id.mondaystartText);
        mondayendhour = (TextView) findViewById(R.id.mondayendText);
        tuesdaystarthour = (TextView) findViewById(R.id.tuesdaystartText);
        tuesdayendhour = (TextView) findViewById(R.id.tuesdayendText);
        wednesdaystarthour = (TextView) findViewById(R.id.wednesdaystartText);
        wednesdayendhour = (TextView) findViewById(R.id.wednesdayendText);
        thursdaystarthour = (TextView) findViewById(R.id.thursdaystartText);
        thursdayendhour = (TextView) findViewById (R.id.thursdayendText);
        fridaystarthour = (TextView) findViewById(R.id.fridaystartText);
        fridayendhour = (TextView) findViewById(R.id.fridayendText);
        saturdaystarthour = (TextView) findViewById(R.id.saturdaystartText);
        saturdayendhour = (TextView) findViewById(R.id.saturdayendText);
        sundaystarthour = (TextView) findViewById(R.id.sundaystartText);
        sundayendhour = (TextView) findViewById(R.id.sundayendText);


        mondayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setstartTime(mondayendhour);
                    setendTime(mondaystarthour);

                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    mondayendhour.setText("End time: Not open");
                    mondaystarthour.setText("Start time: Not open");
                }
            }
        });

        tuesdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setstartTime(tuesdayendhour);
                    setendTime(tuesdaystarthour);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    tuesdayendhour.setText("End time: Not open");
                    tuesdaystarthour.setText("Start time: Not open");
                }
            }
        });

        wednesdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setstartTime(wednesdayendhour);
                    setendTime(wednesdaystarthour);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    wednesdayendhour.setText("End time: Not open");
                    wednesdaystarthour.setText("Start time: Not open");
                }
            }
        });

        thursdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setstartTime(thursdayendhour);
                    setendTime(thursdaystarthour);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    thursdayendhour.setText("End time: Not open");
                    thursdaystarthour.setText("Start time: Not open");
                }
            }
        });

        fridayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setstartTime(fridayendhour);
                    setendTime(fridaystarthour);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    fridayendhour.setText("End time: Not open");
                    fridaystarthour.setText("Start time: Not open");
                }
            }
        });

        saturdayswy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setstartTime(saturdayendhour);
                    setendTime(saturdaystarthour);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    saturdayendhour.setText("End time: Not open");
                    saturdaystarthour.setText("Start time: Not open");
                }
            }
        });

        sundayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setstartTime(sundayendhour);
                    setendTime(sundaystarthour);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    sundayendhour.setText("End time: Not open");
                    sundaystarthour.setText("Start time: Not open");
                }
            }
        });
    }


    public void setstartTime(final TextView textView){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(ClinicHours.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView.setText("End time: "+ hourOfDay + ":" + minute);
            }
        },hour, minute,true);
        timePickerDialog.show();
    }

    public void setendTime(final TextView textView){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(ClinicHours.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView.setText("Start time: "+ hourOfDay + ":" + minute);
            }
        },hour, minute,true);
        timePickerDialog.show();
    }
}
