package com.example.segfinalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ClinicList extends ArrayAdapter<Clinic> {

    private Activity context;
    List<Clinic> clinics;

    public ClinicList(Activity context, List<Clinic> clinics){
        super(context, R.layout.activity_layout_product_list, clinics);
        this.context = context;
        this.clinics = clinics;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_layout_product_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewService);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmployee);

        Clinic clinic = clinics.get(position);
        textViewName.setText(clinic.getName());
        textViewEmail.setText(clinic.getAddress());

        return listViewItem;

    }
}
