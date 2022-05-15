package com.example.gsblocation.controller;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.District;
import com.example.gsblocation.model.Flat;
import com.example.gsblocation.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ControlRequestOwner extends AppCompatActivity {
    private static ControlRequestOwner instance = null;
    ArrayList<Flat> flatsList = new ArrayList<>();
    ArrayList<Request> requestsList = new ArrayList<>();
    ArrayList<String> typeList = new ArrayList<>();

    private ControlRequestOwner(){
        super();
    }

    public static final ControlRequestOwner getInstance(Context contexte) {
        if(ControlRequestOwner.instance == null) {
            ControlRequestOwner.instance = new ControlRequestOwner();
        }
        return ControlRequestOwner.instance;
    }

    //le but c'est de récupérer le type d'appartement que possède le propriétaire
    //et ensuite de récupérer les demandes qui concernent ce type là
    //ça fonctionne bien mais ça ne rempli pas correctement mes listes ensuite ahah vie naze
    public ArrayList<String> getTypeList() {
        int numProp = 1;
        APIAccess APIAccess = new APIAccess(ControlRequestOwner.this);
        APIAccess.ReturnFlatsByIdProp(numProp, new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error flats", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                //je passe bien dans mon for et il voit bien mon objet, il le parcourt correctement
                for (int i = 0; i < response.length(); i++) {
                    JSONObject FlatsObject = null;
                    try {
                        FlatsObject = response.getJSONObject(i);
                        Flat unFlat = new Flat(FlatsObject);
                        typeList.add(i, unFlat.getType());
                        flatsList.add(i, unFlat);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return typeList;
    }

    public ArrayList<Request> getRequestsList(ArrayList<String> typeList) {
        for (int i = 0; i < typeList.size(); i++) {
            String unType = typeList.get(i);
            APIAccess APIAccess = new APIAccess(ControlRequestOwner.this);
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
                            requestsList.add(i, uneDemande);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return requestsList;
    }
}
