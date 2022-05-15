package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.controller.ControlRequestBuyer;
import com.example.gsblocation.controller.ControlRequestOwner;
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.District;
import com.example.gsblocation.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestBuyer extends AppCompatActivity {

    ControlRequestBuyer control;
    Spinner spinner;
    ArrayList<Integer> districtsList = new ArrayList<Integer>();
    Button bRequest;
    EditText typeTxt;
    EditText dateTxt;
    String whoisonline;
    Integer numDem;
    //Integer numArron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        this.control = ControlRequestBuyer.getInstance(this);
        spinner = (Spinner)findViewById(R.id.spinnerDistricts);
        bRequest = findViewById(R.id.btnSendRequest);
        typeTxt = findViewById(R.id.typeEdit);
        dateTxt = findViewById(R.id.dateEdit);
        Intent intent = getIntent();
        String whoisonline = intent.getStringExtra("whoisonline");

        //je rempli ma liste, enfin je crois ?
        //faire une liste déroulante pour le type d'appartement aussi
        districtsList = control.getDistrictsList();
        //pourquoi y a rien dans le districtlist et qu'il le rempli quand même
        Log.d("District list ok", "**************" + districtsList);
        populateSpinner();
        //getItem();
        onClickSend();

        returnLogin();
    }

    private void populateSpinner() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, districtsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String test = parentView.getItemAtPosition(position).toString();
                Toast.makeText(parentView.getContext(), "Selected: " + test, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d("Nothing selected", "**************" );
            }
        });
    }

    private void getItem() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String test = parentView.getItemAtPosition(position).toString();
                Toast.makeText(parentView.getContext(), "Selected: " + test, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d("Nothing selected", "**************" );
            }
        });
    }

    //on appelle l'api pour récupérer les districts de la base de données et ensuite les afficher dans le spinner
    /*public void getDistrictsList() {
        APIAccess APIAccess = new APIAccess(RequestBuyer.this);
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
    }*/

    //Il ne veut pas récupérer l'id de l'utilisateur connecté
    //en fonction de l'arrondissement sélectionné je dois aussi l'enregistrer dans la table concerner (numArron + numDem)
    public void onClickSend() {
        /*int num = Integer.parseInt(whoisonline);*/
        bRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = typeTxt.getText().toString();
                String date = dateTxt.getText().toString();
                Integer numArron = 20;
                Integer num = 100;
                control.sendRequest(type, date, numArron, num);
                //on insert into demandes
                /*APIAccess.sendDistrictRequest(type, date, num, new APIAccess.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("Error send Request", "***************" + message);
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Request ok", "**************" + response);
                    }
                });
                //on retrouve la dernière demande donc celle qui vient d'être créer normalement
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
                APIAccess.sendConcernRequest(9, numArron, new APIAccess.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("Error send concern", "***************" + message);
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Concern ok", "**************" + response);
                    }
                });
                Toast.makeText(RequestBuyer.this, "NUL OMG ", Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    private void returnLogin() {
        ((Button) findViewById(R.id.buttonReturnHome)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RequestBuyer.this, Home.class);
                startActivity(intent);
            }
        });
    }
}