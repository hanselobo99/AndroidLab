package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button login;
    DatabaseManager dbManager;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "MyPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        long id = sharedPreferences.getLong("id",0);
        if(id != 0){
            Intent intent = new Intent(this,SecondActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        long id = dbManager.Insert(name, pass);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("id", id);
        editor.apply();
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}