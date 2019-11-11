package com.example.segfinalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    List<User> users;

    public UserList(Activity context, List<User> users){
        super(context, R.layout.activity_layout_product_list, users);
        this.context = context;
        this.users  = users;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_layout_product_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewService);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmployee);

        User user = users.get(position);
        textViewName.setText(user.getName());
        textViewEmail.setText(user.getEmail());

        return listViewItem;

    }

}
