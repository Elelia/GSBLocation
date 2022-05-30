package com.example.gsblocation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.model.User;

import androidx.appcompat.app.AppCompatActivity;

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
        ImageButton buttonRequest = (ImageButton)findViewById(R.id.btnRequestOwner);
        ImageButton buttonOwner = (ImageButton)findViewById(R.id.btnProfileOwner);
        ImageButton buttonAdd = (ImageButton)findViewById(R.id.btnAdd);
        ImageButton buttonSearch = (ImageButton)findViewById(R.id.btnSearch);

        Intent intent = getIntent();
        String whoisonline = intent.getStringExtra("whoisonline");
        ConnectedUser = this.control.whoLogin(whoisonline);
        Toast.makeText(Home.this, "Succes : "+ ConnectedUser.convertToJSON(), Toast.LENGTH_SHORT).show();
        String nom = ConnectedUser.getNom();
        String prenom = ConnectedUser.getPrenom();
        userInfo.setText("Bienvenue "+ prenom + " " + nom + " !");
        if(ConnectedUser.getType().equals("c")) {
            buttonRequest.setVisibility(View.INVISIBLE);
            buttonOwner.setVisibility(View.INVISIBLE);
            buttonAdd.setVisibility(View.INVISIBLE);
        }
        if(ConnectedUser.getType().equals("p")) {
            goRequestOwner();
            goProfileOwner();
            goAddOwner();
            buttonSearch.setVisibility(View.INVISIBLE);
        }
        //me ramène à la page de connexion
        returnLogin();
        //pour aller à la recherche
        goSearch();
        //pour aller au profil buyer
        goProfileBuyer();
        //pour aller aux demandes buyer
        goRequest();
    }

    private void goProfileBuyer() {
        ((ImageButton) findViewById(R.id.btnProfileBuyer)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ProfileBuyer.class);
                intent.putExtra("whoisonline", ConnectedUser.toString());
                startActivity(intent);
            }
        });
    }

    private void goProfileOwner() {
        ((ImageButton) findViewById(R.id.btnProfileOwner)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ProfileOwner.class);
                intent.putExtra("whoisonline", ConnectedUser.getNum().toString());
                startActivity(intent);
            }
        });
    }

    private void goRequest() {
        ((ImageButton) findViewById(R.id.btnRequest)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, RequestBuyer.class);
                intent.putExtra("whoisonline", ConnectedUser.getNum().toString());
                startActivity(intent);
            }
        });
    }

    private void goSearch() {
        ((ImageButton) findViewById(R.id.btnSearch)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            }
        });
    }

    private void goRequestOwner() {
        ((ImageButton) findViewById(R.id.btnRequestOwner)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, RequestOwner.class);
                intent.putExtra("whoisonline", ConnectedUser.getNum().toString());
                startActivity(intent);
            }
        });
    }

    private void goAddOwner() {
        ((ImageButton) findViewById(R.id.btnAdd)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AddFlat.class);
                intent.putExtra("whoisonline", ConnectedUser.getNum().toString());
                startActivity(intent);
            }
        });
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