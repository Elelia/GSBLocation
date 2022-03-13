package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.model.APIAccess;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private EditText loginTxt;
    private EditText mdpTxt;
    private TextView testText;
    private Control control;
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.control = Control.getInstance(this);
        loginTxt = (EditText)findViewById(R.id.login);
        mdpTxt = (EditText)findViewById(R.id.mdp);
        testText = (TextView)findViewById(R.id.textView2);
        bLogin = findViewById(R.id.buttonlogin);
        tryLogin();
    }

    private void tryLogin() {
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIAccess APIAccess = new APIAccess(MainActivity.this);
                String login = loginTxt.getText().toString();
                String password = mdpTxt.getText().toString();
                APIAccess.UserConnexion(login, password, new APIAccess.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("Error while login", "***************" + message);
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Login si a success", "**************" + response);
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        intent.putExtra("whoisonline", response.toString());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void afficheResult(String login, String password) {
        this.control.createUser(0, login, password, "","","","","","");
        String nom = this.control.getNomTest();
        testText.setText(nom+login+password);
    }
}