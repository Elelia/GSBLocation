package com.example.gsblocation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.model.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Home extends AppCompatActivity {

    private User ConnectedUser;
    private Control control;
    private TextView userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.control = Control.getInstance(this);
        userInfo = (TextView)findViewById(R.id.textUser);

        //me ramène à la page de connexion
        returnLogin();
        Intent intent = getIntent();
        String whoisonline = intent.getStringExtra("whoisonline");
        ConnectedUser = this.control.whoLogin(whoisonline);
        Toast.makeText(Home.this, "Succes : "+ ConnectedUser.convertToJSON(), Toast.LENGTH_SHORT).show();
        String nom = ConnectedUser.getNom();
        String prenom = ConnectedUser.getPrenom();
        userInfo.setText("Bienvenue "+ prenom + " " + nom + " !");
    }

    private void returnLogin() {
        ((Button) findViewById(R.id.buttongoLoginpage)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}