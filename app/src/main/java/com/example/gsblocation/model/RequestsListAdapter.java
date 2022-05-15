package com.example.gsblocation.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gsblocation.R;

import java.util.ArrayList;
import java.util.List;

public class RequestsListAdapter extends ArrayAdapter<Request> {

    private Context rContext;
    int rRessource;

    public RequestsListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Request> objects) {
        super(context, resource, objects);
        rContext = context;
        rRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Integer numDem = getItem(position).getNumDem();
        String typeDem = getItem(position).getTypeDem();
        String dateLimite = getItem(position).getDateLimite();
        Integer num = getItem(position).getNum();

        Request request = new Request(numDem, typeDem, dateLimite, num);

        LayoutInflater inflater = LayoutInflater.from(rContext);
        convertView = inflater.inflate(rRessource, parent, false);

        TextView txNumD = (TextView) convertView.findViewById(R.id.textView6);
        TextView txTypeD = (TextView) convertView.findViewById(R.id.textView7);
        TextView txDateL = (TextView) convertView.findViewById(R.id.textView8);

        txNumD.setText(numDem);
        txTypeD.setText(typeDem);
        txDateL.setText(dateLimite);

        return convertView;
    }
}