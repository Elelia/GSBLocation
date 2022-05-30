package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.controller.ControlProfileOwner;
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Request;
import com.example.gsblocation.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileOwner extends AppCompatActivity {

    private ControlProfileOwner control;
    private Control controlMain;
    private Integer numBuyer;
    private ArrayList<Request> requestsList = new ArrayList<>();
    private User ConnectedUser;
    private EditText userPrenom;
    private EditText userNom;
    private EditText userVille;
    private EditText userAdresse;
    private EditText userTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_owner);
        this.control = ControlProfileOwner.getInstance(this);
        this.controlMain = Control.getInstance(this);
        ListView mListView = (ListView) findViewById(R.id.listView1);
        userPrenom = (EditText)findViewById(R.id.textPrenom);
        userNom = (EditText)findViewById(R.id.textNom);
        userVille = (EditText)findViewById(R.id.textVille);
        userAdresse = (EditText)findViewById(R.id.textAdresse);
        userTelephone = (EditText)findViewById(R.id.textTelephone);


        //on récupère le numéro de l'utilisateur
        Intent intent = getIntent();
        String whoisonline = intent.getStringExtra("whoisonline");
        ConnectedUser = this.controlMain.whoLogin(whoisonline);
        numBuyer = ConnectedUser.getNum();
        Log.d("Try", "***************" + numBuyer);
        String nom = ConnectedUser.getNom();
        String prenom = ConnectedUser.getPrenom();
        String ville = ConnectedUser.getCodeVille();
        String adresse = ConnectedUser.getAdresse();
        String telephone = ConnectedUser.getTelephone();

        userNom.setText(nom);
        userPrenom.setText(prenom);
        userVille.setText(ville);
        userAdresse.setText(adresse);
        userTelephone.setText(telephone);

        updateProfile();
        returnLogin();
    }

    private void updateProfile() {
        ((Button) findViewById(R.id.buttonUpdateInfo)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String modifNom = userNom.getText().toString();
                String modifPrenom = userPrenom.getText().toString();
                String modifVille = userVille.getText().toString();
                String modifAdresse = userAdresse.getText().toString();
                String modifTel = userTelephone.getText().toString();
                Integer num = 100;
                APIAccess APIAccess = new APIAccess(ProfileOwner.this);
                APIAccess.UpdateUserInfo(num, modifNom, modifPrenom, modifAdresse, modifVille, modifTel, new APIAccess.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("Error update user", "***************" + message);
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Update ok", "**************" + response);
                        //Intent intent = new Intent(ProfileBuyer.this, ProfileBuyer.class);
                        //startActivity(intent);
                        Toast.makeText(ProfileOwner.this, "Succes ! ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void returnLogin() {
        ((Button) findViewById(R.id.buttonReturnHome)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileOwner.this, Home.class);
                startActivity(intent);
            }
        });
    }
}