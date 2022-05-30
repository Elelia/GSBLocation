package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.ControlAddFlat;
import com.example.gsblocation.controller.ControlRequestBuyer;

public class AddFlat extends AppCompatActivity {

    private ControlAddFlat control;
    private Button bAddFlat;
    private EditText textType;
    private EditText textPrixLoc;
    private EditText textPrixChr;
    private EditText textRue;
    private EditText textArrondissement;
    private EditText textEtage;
    private EditText textAscenseur;
    private EditText textPieces;
    private EditText textTaille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat);
        this.control = ControlAddFlat.getInstance(this);

        bAddFlat = findViewById(R.id.buttonSend);
        textType = findViewById(R.id.textType);
        textPrixLoc = findViewById(R.id.textPrixLoc);
        textPrixChr = findViewById(R.id.textPrixChr);
        textRue = findViewById(R.id.textRue);
        textArrondissement = findViewById(R.id.textArrondissement);
        textEtage = findViewById(R.id.textEtage);
        textAscenseur = findViewById(R.id.textAscenseur);
        textPieces = findViewById(R.id.textPieces);
        textTaille = findViewById(R.id.textTaille);

        returnLogin();
        onClickSend();
    }

    public void onClickSend() {
        /*int num = Integer.parseInt(whoisonline);*/
        bAddFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer num = 18;
                String type = textType.getText().toString();
                String prixLoc = textPrixLoc.getText().toString();
                String prixChr = textPrixChr.getText().toString();
                String rue = textRue.getText().toString();
                String arrondissement = textArrondissement.getText().toString();
                String etage = textEtage.getText().toString();
                String ascenseur = textAscenseur.getText().toString();
                String pieces = textPieces.getText().toString();
                String taille = textTaille.getText().toString();
                control.addFlat(num, type, prixLoc, prixChr, rue, arrondissement, etage, ascenseur, pieces, taille);
                //afficher un message si tout s'est bien passé
            }
        });
    }

    private void returnLogin() {
        ((Button) findViewById(R.id.buttonReturnHome)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddFlat.this, Home.class);
                startActivity(intent);
            }
        });
    }
}