package com.example.segfinalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {

    private Activity context;
    List<Service> services;

    public ServiceList(Activity context, List<Service> services){
        super(context, R.layout.activity_layout_product_list, services);
        this.context = context;
        this.services = services;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_layout_product_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewService);
        TextView textViewEmployee = (TextView) listViewItem.findViewById(R.id.textViewEmployee);

        Service service = services.get(position);
        textViewName.setText(service.getServiceName());
        textViewEmployee.setText(String.valueOf(service.getServiceEmployee()));
        return listViewItem;

    }

}
