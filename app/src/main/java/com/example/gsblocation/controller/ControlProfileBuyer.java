package com.example.gsblocation.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsblocation.R;
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Buyer;
import com.example.gsblocation.model.Owner;
import com.example.gsblocation.model.Request;
import com.example.gsblocation.model.User;
import com.example.gsblocation.view.ProfileBuyer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ControlProfileBuyer extends AppCompatActivity {
    private static ControlProfileBuyer instance = null;

    private ArrayList<Request> requestsList = new ArrayList<>();

    private ControlProfileBuyer(){
        super();
    }

    public static final ControlProfileBuyer getInstance(Context contexte) {
        if(ControlProfileBuyer.instance == null) {
            ControlProfileBuyer.instance = new ControlProfileBuyer();
        }
        return ControlProfileBuyer.instance;
    }

    public void getRequestsByUser(Integer numBuyer) {
        APIAccess APIAccess = new APIAccess(ControlProfileBuyer.this);
        //dans le doute on try
        //mais évidemment le numBuyer il le retrouve pas en fait alors il faut le mettre en dur et c'est nul
        APIAccess.ReturnRequestsByNumC(numBuyer, new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error get request", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Request ok", "**************" + response);
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject RequestsObject = response.getJSONObject(i);
                        Request oneRequest = new Request(RequestsObject);
                        requestsList.add(i, oneRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void updateProfile(String nom, String prenom, String ville, String adresse, String tel, Integer num) {
        APIAccess APIAccess = new APIAccess(ControlProfileBuyer.this);
        APIAccess.UpdateUserInfo(num, nom, prenom, adresse, ville, tel, new APIAccess.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("Error update user", "***************" + message);
            }

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Update ok", "**************" + response);
                //Intent intent = new Intent(ProfileBuyer.this, ProfileBuyer.class);
                //startActivity(intent);
                Toast.makeText(ControlProfileBuyer.this, "Succes ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
