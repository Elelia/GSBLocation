package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.ControlRequestOwner;
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Flat;
import com.example.gsblocation.model.Request;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestOwner extends AppCompatActivity {

    ControlRequestOwner control;
    String whoisonline;
    ListView listView;
    ArrayList<String> typesList = new ArrayList<>();
    ArrayList<String> tryTypeList = new ArrayList<>();
    ArrayList<Request> requestsList = new ArrayList<>();
    ArrayList<Request> tryRequestsList = new ArrayList<>();
    ArrayList<Flat> flatsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_owner);
        this.control = ControlRequestOwner.getInstance(this);
        listView = (ListView)findViewById(R.id.listview);
        //flatsList = new ArrayList<>();

        Intent intent = getIntent();
        whoisonline = intent.getStringExtra("whoisonline");

        typesList = getTypeList();
        requestsList = getRequestsList(typesList);

        ArrayAdapter<String> adapterFlats = new ArrayAdapter(RequestOwner.this, android.R.layout.simple_list_item_1, requestsList);
        listView.setAdapter(adapterFlats);
        //quand je clique sur une ligne du tableau (si il veut bien se créer ahah), il faut qu'elle m'envoit sur une création de visite
    }

    public ArrayList<String> getTypeList() {
        int numProp = 1;
        APIAccess APIAccess = new APIAccess(RequestOwner.this);
        APIAccess.ReturnFlatsByIdProp(numProp, new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error flats", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject FlatsObject = null;
                    try {
                        FlatsObject = response.getJSONObject(i);
                        Flat unFlat = new Flat(FlatsObject);
                        tryTypeList.add(i, unFlat.getType());
                        flatsList.add(i, unFlat);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Log.d("type list", "***************" + tryTypeList);
        return tryTypeList;
    }

    public ArrayList<Request> getRequestsList(ArrayList<String> typeList) {
        for (int i = 0; i < typeList.size(); i++) {
            String unType = typeList.get(i);
            APIAccess APIAccess = new APIAccess(RequestOwner.this);
            APIAccess.returnRequestsByType(unType, new APIAccess.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Log.d("Error request", "***************" + message);
                }

                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject RequestsObject = null;
                        try {
                            RequestsObject = response.getJSONObject(i);
                            Request uneDemande = new Request(RequestsObject);
                            tryRequestsList.add(i, uneDemande);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        Log.d("request list", "***************" + tryRequestsList);
        return tryRequestsList;
    }

}