package com.example.gsblocation.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gsblocation.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String whoisonline = intent.getStringExtra("whoisonline");
        int num = Integer.parseInt(whoisonline);
        Log.d("Error send Request", "***************" + num);
    }
}