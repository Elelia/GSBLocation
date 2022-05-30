package com.example.gsblocation.controller;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Flat;
import com.example.gsblocation.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ControlAddFlat extends AppCompatActivity {
    private static ControlAddFlat instance = null;

    private ControlAddFlat(){
        super();
    }

    public static final ControlAddFlat getInstance(Context contexte) {
        if(ControlAddFlat.instance == null) {
            ControlAddFlat.instance = new ControlAddFlat();
        }
        return ControlAddFlat.instance;
    }

    public void addFlat(Integer num, String type, String prixLoc, String prixChr, String rue, String arrondissement, String etage, String ascenseur, String pieces, String taille) {
        APIAccess APIAccess = new APIAccess(ControlAddFlat.this);
        //on insert into appartements
        APIAccess.sendNewFlat(num, type, prixLoc, prixChr, rue, arrondissement, etage, ascenseur, pieces, taille, new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error send Request", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Request ok", "**************" + response);
            }
        });
        APIAccess.returnLastRequest(new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error last request", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Last request ok", "**************" + response);
                JSONObject FlatObject = null;
                try {
                    FlatObject = response.getJSONObject(0);
                    Flat unAppartement = new Flat(FlatObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
