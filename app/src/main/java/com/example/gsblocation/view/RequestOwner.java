package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.ControlRequestOwner;
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Flat;
import com.example.gsblocation.model.Request;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestOwner extends AppCompatActivity {

    private ControlRequestOwner control;
    private String whoisonline;
    private ListView listView;
    private ArrayList<String> typesList = new ArrayList<>();
    private ArrayList<String> tryTypeList = new ArrayList<>();
    private ArrayList<Request> requestsList = new ArrayList<>();
    private ArrayList<Request> tryRequestsList = new ArrayList<>();
    private ArrayList<Flat> flatsList = new ArrayList<>();
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_owner);
        this.control = ControlRequestOwner.getInstance(this);
        listView = (ListView)findViewById(R.id.listview);
        table = (TableLayout)findViewById(R.id.mytable);

        Intent intent = getIntent();
        whoisonline = intent.getStringExtra("whoisonline");

        typesList = control.getTypeList();
        requestsList = control.getRequestsList(typesList);

        ArrayAdapter<String> adapterFlats = new ArrayAdapter(RequestOwner.this, android.R.layout.simple_list_item_1, requestsList);
        listView.setAdapter(adapterFlats);
        //ça marche pas j'adore yeah
        returnLogin();
    }

    //il me faudrait un truc pour afficher sous forme de tableau
    //ou de liste idk ce qui serait le mieux
    /*public void populateTable(ArrayList<Request> list) {
        for(int i=0; i < list.size(); i++) {
            TableRow row = new TableRow(this);

        }
    }*/

    private void returnLogin() {
        ((Button) findViewById(R.id.buttonReturnHome)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RequestOwner.this, Home.class);
                startActivity(intent);
            }
        });
    }

}