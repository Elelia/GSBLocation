package com.example.gsblocation.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.gsblocation.model.Buyer;
import com.example.gsblocation.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Control {
    private static Control instance = null;
    private static User user;
    User ConnectedUser;
    Buyer ConnectedBuyer;

    private Control(){
        super();
    }

    public static final Control getInstance(Context contexte) {
        if(Control.instance == null) {
            Control.instance = new Control();
        }
        return Control.instance;
    }

    public User createUser(Integer num, String login, String mdp, String nom, String prenom, String adresse, String codeville, String tel, String type) {
        user = new User(num, login, mdp, nom, prenom, adresse, codeville, tel, type);
        return user;
    }

    public String getNomTest() {
        user.testResult();
        return user.getNom();
    }

    public User whoLogin(String whoisonline) {
        try {
            JSONArray UserArray = new JSONArray(whoisonline);
            JSONObject UserInfo = UserArray.getJSONObject(0);
            //Toast.makeText(Dashboard.this, "Succes : "+ UserInfo, Toast.LENGTH_SHORT).show();
            ConnectedUser = new User(UserInfo.getInt("num"),UserInfo.getString("login"),UserInfo.getString("mdp"),UserInfo.getString("nom"),UserInfo.getString("prenom"),UserInfo.getString("adresse"),UserInfo.getString("codeVille"),UserInfo.getString("telephone"),UserInfo.getString("type"));
            Log.d("info user", "********************" + ConnectedUser.convertToJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*if(ConnectedUser.getType().equals("c")) {
            ConnectedBuyer = new Buyer(ConnectedUser);
            Log.d("info buyer", "********************" + ConnectedBuyer.convertToJSON());
        }
        else if(ConnectedUser.getType().equals("p")) {

        }*/
        Log.d("value", "********************" + ConnectedUser.convertToJSON());
        return ConnectedUser;
    }
}
