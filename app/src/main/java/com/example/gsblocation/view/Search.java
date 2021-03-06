package com.example.gsblocation.view;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.model.Flat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search extends AppCompatActivity {

    private Control control;
    private Flat flatList;
    private Button bSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.control = Control.getInstance(this);
        bSearch = findViewById(R.id.btnSearch);

        returnLogin();
    }

    private void returnLogin() {
        ((Button) findViewById(R.id.buttonReturnHome)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, Home.class);
                startActivity(intent);
            }
        });
    }

}