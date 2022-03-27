package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.model.APIAccess;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    EditText loginTxt;
    EditText mdpTxt;
    Control control;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.control = Control.getInstance(this);
        loginTxt = (EditText)findViewById(R.id.login);
        mdpTxt = (EditText)findViewById(R.id.mdp);
        bLogin = findViewById(R.id.buttonlogin);
        tryLogin();
    }

    //méthode qui permet de connecter l'utilisateur
    private void tryLogin() {
        //permet de lancer le code lorque l'on clique sur le bouton de connexion
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on instancie l'API access pour pouvoir accéder à l'API ensuite
                APIAccess APIAccess = new APIAccess(MainActivity.this);
                //on récupère les données saisies par l'utilisateur
                String login = loginTxt.getText().toString();
                String password = mdpTxt.getText().toString();
                //on appelle la méthode de connexion de la classe APIAccess
                APIAccess.UserConnexion(login, password, new APIAccess.VolleyResponseListener() {
                    @Override
                    //si on retourne un message d'erreur
                    public void onError(String message) {
                        Log.d("Error while login", "***************" + message);
                        Toast.makeText(MainActivity.this, "Error"+ message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    //si la connexion s'effectue correctement
                    public void onResponse(JSONArray response) {
                        Log.d("Login is a success", "**************" + response);
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        intent.putExtra("whoisonline", response.toString());
                        startActivity(intent);
                    }
                });
            }
        });
    }
}