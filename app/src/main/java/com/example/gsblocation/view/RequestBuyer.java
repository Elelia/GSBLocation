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
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.District;
import com.example.gsblocation.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestBuyer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Control control;
    Spinner spinner;
    ArrayList<Integer> districtsList = new ArrayList<Integer>();;
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
        this.control = Control.getInstance(this);
        spinner = (Spinner)findViewById(R.id.spinnerDistricts);
        bRequest = findViewById(R.id.btnSendRequest);
        typeTxt = findViewById(R.id.typeEdit);
        dateTxt = findViewById(R.id.dateEdit);
        Intent intent = getIntent();
        String whoisonline = intent.getStringExtra("whoisonline");

        //je rempli ma liste, enfin je crois ?
        getDistrictsList();
        //pourquoi y a rien dans le districtlist et qu'il le rempli quand même
        Log.d("District list ok", "**************" + districtsList);
        populateSpinner();
        getItem();
        sendRequest();
    }

    private void populateSpinner() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, districtsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    private void getItem() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String test = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d("Nothing selected", "**************" );
            }
        });
    }

    //on appelle l'api pour récupérer les districts de la base de données et ensuite les afficher dans le spinner
    public void getDistrictsList() {
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
    }

    //Il ne veut pas récupérer l'id de l'utilisateur connecté
    //en fonction de l'arrondissement sélectionné je dois aussi l'enregistrer dans la table concerner (numArron + numDem)
    public void sendRequest() {
        String type = typeTxt.getText().toString();
        String date = dateTxt.getText().toString();
        /*int num = Integer.parseInt(whoisonline);*/
        bRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIAccess APIAccess = new APIAccess(RequestBuyer.this);
                /*String type = "studio";
                String date = "2022-06-12";*/
                int num = 100;
                int numArron = 20;
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
                APIAccess.sendConcernRequest(numDem, numArron, new APIAccess.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("Error send concern", "***************" + message);
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Concern ok", "**************" + response);
                    }
                });
                Toast.makeText(RequestBuyer.this, "NUL OMG ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}