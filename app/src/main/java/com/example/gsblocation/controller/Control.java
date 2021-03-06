package com.example.gsblocation.controller;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Buyer;
import com.example.gsblocation.model.District;
import com.example.gsblocation.model.Flat;
import com.example.gsblocation.model.Owner;
import com.example.gsblocation.model.Request;
import com.example.gsblocation.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Control extends AppCompatActivity {
    private static Control instance = null;
    private User user;
    User ConnectedUser;
    Buyer ConnectedBuyer;
    Owner ConnectedOwner;

    private Control(){
        super();
    }

    public static final Control getInstance(Context contexte) {
        if(Control.instance == null) {
            Control.instance = new Control();
        }
        return Control.instance;
    }

    //méthode appelée depuis Home afin de créer un user en fonction des données de connexion
    public User whoLogin(String whoisonline) {
        try {
            JSONArray UserArray = new JSONArray(whoisonline);
            JSONObject UserInfo = UserArray.getJSONObject(0);
            ConnectedUser = new User(UserInfo.getInt("num"),UserInfo.getString("login"),UserInfo.getString("mdp"),UserInfo.getString("nom"),UserInfo.getString("prenom"),UserInfo.getString("adresse"),UserInfo.getString("codeVille"),UserInfo.getString("telephone"),UserInfo.getString("type"));
            Log.d("Connected user", "********************" + ConnectedUser.convertToJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(ConnectedUser.getType().equals("c")) {
            ConnectedBuyer = new Buyer(ConnectedUser.getNum(),ConnectedUser.getLogin(),ConnectedUser.getMdp(),ConnectedUser.getNom(),ConnectedUser.getPrenom(),ConnectedUser.getAdresse(),ConnectedUser.getCodeVille(),ConnectedUser.getTelephone(),ConnectedUser.getType());
            Log.d("info buyer", "********************" + ConnectedBuyer.convertToJSON());
        }
        else if(ConnectedUser.getType().equals("p")) {
            ConnectedOwner = new Owner(ConnectedUser.getNum(),ConnectedUser.getLogin(),ConnectedUser.getMdp(),ConnectedUser.getNom(),ConnectedUser.getPrenom(),ConnectedUser.getAdresse(),ConnectedUser.getCodeVille(),ConnectedUser.getTelephone(),ConnectedUser.getType());
            Log.d("info buyer", "********************" + ConnectedOwner.convertToJSON());
        }
        //a voir comment retourner le buyer ou le owner, là il me retourne juste l'utilisateur mais pas les enfants
        return ConnectedUser;
    }
}
