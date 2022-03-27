package com.example.gsblocation.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.gsblocation.R;

import java.util.ArrayList;

public class FlatAdapter extends ArrayAdapter<Flat> {

    public FlatAdapter(Context context, ArrayList<Flat> flats) {
        super(context, 0, flats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Flat flat = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_search, parent, false);
        }
        //TextView flatType = (TextView) convertView.findViewById(R.id.flatType);
        //TextView flatTaille = (TextView) convertView.findViewById(R.id.flatTaille);
        //flatType.setText(flat.getType());
        //flatTaille.setText(flat.getTaille());
        return convertView;
    }
}
