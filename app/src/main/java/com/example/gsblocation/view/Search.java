package com.example.gsblocation.view;

import com.example.gsblocation.R;
import com.example.gsblocation.controller.Control;
import com.example.gsblocation.model.APIAccess;
import com.example.gsblocation.model.Flat;
import com.example.gsblocation.model.FlatAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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
    }

}