package com.example.segfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    Button setHours;
    String userId;
    DatabaseReference dr, clinicReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workhours);

        setHours = (Button) findViewById(R.id.sethours);

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

        final StringBuilder monStart, monEnd, tueStart, tueEnd, wedStart, wedEnd, thuStart, thuEnd, friStart, friEnd, satStart, satEnd, sunStart, sunEnd;
        monStart = new StringBuilder();
        monEnd = new StringBuilder();
        tueStart= new StringBuilder();
        tueEnd= new StringBuilder();
        wedStart= new StringBuilder();
        wedEnd= new StringBuilder();
        thuStart= new StringBuilder();
        thuEnd= new StringBuilder();
        friStart= new StringBuilder();
        friEnd= new StringBuilder();
        satStart= new StringBuilder();
        satEnd= new StringBuilder();
        sunStart= new StringBuilder();
        sunEnd= new StringBuilder();

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        clinicReference = FirebaseDatabase.getInstance().getReference("User").child(userId);


        String clinicId = getClinicId(clinicReference);
        tuesdayendhour.setText(clinicId);
        dr = FirebaseDatabase.getInstance().getReference("clinics");

        mondayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setEndTime(mondayendhour, monEnd);
                    setStartTime(mondaystarthour, monStart);

                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    mondayendhour.setText("End time: Not open");
                    mondaystarthour.setText("Start time: Not open");

                    monEnd.delete(0,monEnd.length());
                    monStart.delete(0,monStart.length());

                    monEnd.append("closed");
                    monStart.append("closed");
                }
            }
        });

        tuesdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setEndTime(tuesdayendhour, tueEnd);
                    setStartTime(tuesdaystarthour, tueStart);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    tuesdayendhour.setText("End time: Not open");
                    tuesdaystarthour.setText("Start time: Not open");

                    tueEnd.delete(0,tueEnd.length());
                    tueStart.delete(0,tueStart.length());

                    tueEnd.append("closed");
                    tueStart.append("closed");
                }
            }
        });

        wednesdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setEndTime(wednesdayendhour,wedEnd);
                    setStartTime(wednesdaystarthour, wedStart);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    wednesdayendhour.setText("End time: Not open");
                    wednesdaystarthour.setText("Start time: Not open");

                    wedEnd.delete(0,wedEnd.length());
                    wedStart.delete(0,wedStart.length());

                    wedEnd.append("closed");
                    wedStart.append("closed");
                }
            }
        });

        thursdayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setEndTime(thursdayendhour,thuEnd);
                    setStartTime(thursdaystarthour,thuStart);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    thursdayendhour.setText("End time: Not open");
                    thursdaystarthour.setText("Start time: Not open");

                    thuEnd.delete(0,thuEnd.length());
                    thuStart.delete(0,thuStart.length());

                    thuEnd.append("closed");
                    thuStart.append("closed");
                }
            }
        });

        fridayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setEndTime(fridayendhour,friEnd);
                    setStartTime(fridaystarthour, friStart);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    fridayendhour.setText("End time: Not open");
                    fridaystarthour.setText("Start time: Not open");

                    friEnd.delete(0,friEnd.length());
                    friStart.delete(0,friStart.length());

                    friEnd.append("closed");
                    friStart.append("closed");
                }
            }
        });

        saturdayswy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setEndTime(saturdayendhour,satEnd);
                    setStartTime(saturdaystarthour,satStart);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    saturdayendhour.setText("End time: Not open");
                    saturdaystarthour.setText("Start time: Not open");

                    satEnd.delete(0,satEnd.length());
                    satStart.delete(0,satStart.length());

                    satEnd.append("closed");
                    satStart.append("closed");
                }
            }
        });

        sundayswt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Set working hours", Toast.LENGTH_SHORT).show();
                    setEndTime(sundayendhour,sunEnd);
                    setStartTime(sundaystarthour,sunStart);
                } else {
                    Toast.makeText(getBaseContext(), "Not open", Toast.LENGTH_SHORT).show();
                    sundayendhour.setText("End time: Not open");
                    sundaystarthour.setText("Start time: Not open");

                    sunEnd.delete(0,sunEnd.length());
                    sunStart.delete(0,sunStart.length());

                    sunEnd.append("closed");
                    sunStart.append("closed");
                }
            }
        });

        setHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clinicReference.child("clinic").addListenerForSingleValueEvent(new ValueEventListener() {
                    String clinic;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        clinic = dataSnapshot.getValue(String.class);
                        dr = dr.child(clinic).child("hours");

                        addToDatabase(dr, "monday", "start", monStart);
                        addToDatabase(dr, "monday", "end", monEnd);

                        addToDatabase(dr, "tuesday", "start", tueStart);
                        addToDatabase(dr, "tuesday", "end", tueEnd);

                        addToDatabase(dr, "wednesday", "start", wedStart);
                        addToDatabase(dr, "wednesday", "end", wedEnd);

                        addToDatabase(dr, "thursday", "start", thuStart);
                        addToDatabase(dr, "thursday", "end", thuEnd);

                        addToDatabase(dr, "friday", "start", friStart);
                        addToDatabase(dr, "friday", "end", friEnd);

                        addToDatabase(dr, "saturday", "start", satStart);
                        addToDatabase(dr, "saturday", "end", satEnd);

                        addToDatabase(dr, "sunday", "start", sunStart);
                        addToDatabase(dr, "sunday", "end", sunEnd);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    public void setEndTime(final TextView textView, final StringBuilder time){

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(ClinicHours.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if(minute == 0){
                    textView.setText("End time: "+ hourOfDay + ":" + "00");

                    time.append(hourOfDay);
                    time.append("00");

                    //dr.child(day).child("end").setValue(hourOfDay + "" + "00");
                }else {
                    textView.setText("End time: " + hourOfDay + ":" + minute);

                    //dr.child(day).child("end").setValue(hourOfDay + "" + minute);
                    time.append(hourOfDay);
                    time.append(minute);

                }
            }
        },hour, minute,true);


        timePickerDialog.show();
    }

    public void setStartTime(final TextView textView, final StringBuilder time){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(ClinicHours.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(minute == 0){
                    textView.setText("Start time: "+ hourOfDay + ":" + "00");
                    time.append(hourOfDay);
                    time.append("00");
                    //dr.child(day).child("start").setValue(hourOfDay + "" + "00");
                }else {
                    textView.setText("Start time: " + hourOfDay + ":" + minute);
                    time.append(hourOfDay);
                    time.append(minute);
                    //dr.child(day).child("start").setValue(hourOfDay + "" + minute);
                }
            }
        },hour, minute,true);
        timePickerDialog.show();
    }

    public String getClinicId(DatabaseReference clinicReference){

        final StringBuilder clinicId = new StringBuilder();
        clinicReference.child("clinic").addListenerForSingleValueEvent(new ValueEventListener() {
            String clinic;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clinic = dataSnapshot.getValue(String.class);
                mondaystarthour.setText(clinic);
                clinicId.append(clinic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return clinicId.toString();
    }

    public void addToDatabase(DatabaseReference ref, String day, String bound, StringBuilder time){

        if(time.toString().equals("")){
            time.append("closed");
        }

        ref.child(day).child(bound).setValue(time.toString());

    }
}
