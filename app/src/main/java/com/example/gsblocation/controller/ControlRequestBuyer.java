package com.example.gsblocation.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.District;
import com.example.gsblocation.model.Request;
import com.example.gsblocation.view.RequestBuyer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ControlRequestBuyer extends AppCompatActivity {
    private static ControlRequestBuyer instance = null;
    private ArrayList<Integer> districtsList = new ArrayList<Integer>();
    private Integer numDem;

    private ControlRequestBuyer(){
        super();
    }

    public static final ControlRequestBuyer getInstance(Context contexte) {
        if(ControlRequestBuyer.instance == null) {
            ControlRequestBuyer.instance = new ControlRequestBuyer();
        }
        return ControlRequestBuyer.instance;
    }

    //on appelle l'api pour récupérer les districts de la base de données et ensuite les afficher dans le spinner
    public ArrayList getDistrictsList() {
        APIAccess APIAccess = new APIAccess(ControlRequestBuyer.this);
        APIAccess.ReturnAllDistricts(new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error districts list", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i < response.length();i++) {
                    try {
                        JSONObject DistrictInfo = response.getJSONObject(i);
                        District unDistrict = new District(DistrictInfo.getInt("arrondisseDem"));
                        districtsList.add(unDistrict.getArrondisseDem());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return districtsList;
    }

    public void sendRequest(String type, String date, Integer numArron, Integer num) {
        APIAccess APIAccess = new APIAccess(ControlRequestBuyer.this);
        //on insert into demandes
        APIAccess.sendDistrictRequest(type, date, num, new APIAccess.VolleyResponseListener() {
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
                JSONObject RequestObject = null;
                try {
                    RequestObject = response.getJSONObject(0);
                    Request uneDemande = new Request(RequestObject);
                    numDem = uneDemande.getNumDem();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //et là on insert into concerner normalement
        //mais j'arrive pas à passer le numéro que je récupère dans mes paramètres
        APIAccess.sendConcernRequest(10, numArron, new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error send concern", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Concern ok", "**************" + response);
            }
        });
    }

}
