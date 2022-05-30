package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.controller.ControlProfileBuyer;
import com.example.gsblocation.controller.ControlRequestBuyer;
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Request;
import com.example.gsblocation.model.RequestsListAdapter;
import com.example.gsblocation.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileBuyer extends AppCompatActivity {

    private ControlProfileBuyer control;
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
        setContentView(R.layout.activity_profile_buyer);
        this.control = ControlProfileBuyer.getInstance(this);
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

        this.control.getRequestsByUser(numBuyer);
        //à partir de là normalement ma liste est remplie right ?
        //en fait on dirait que j'appelle ma méthode à la fin du onCreate c'est nul
        Log.d("Try", "***************" + requestsList);

        /* avec ça ça plante
        Request req1 = new Request(1,"studio","2022-07-24",100);
        ArrayList<Request> testList = new ArrayList<>();
        testList.add(req1);*/

        //RequestsListAdapter adapter = new RequestsListAdapter(this, R.layout.adapter_view_layout, requestsList);
        //mListView.setAdapter(adapter);
        //évidemment l'affichage ne fonctionne pas ahah
        onClickUpdate();
        returnLogin();
    }

    private void onClickUpdate() {
        ((Button) findViewById(R.id.buttonUpdateInfo)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String modifNom = userNom.getText().toString();
                String modifPrenom = userPrenom.getText().toString();
                String modifVille = userVille.getText().toString();
                String modifAdresse = userAdresse.getText().toString();
                String modifTel = userTelephone.getText().toString();
                Integer num = 100;
                control.updateProfile(modifNom, modifPrenom, modifVille, modifAdresse, modifTel, num);
            }
        });
    }

    private void returnLogin() {
        ((Button) findViewById(R.id.buttonReturnHome)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileBuyer.this, Home.class);
                startActivity(intent);
            }
        });
    }
}