package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    Button update, delete;
    EditText username, password;
    TextView userId;
    public static final String SHARED_PREF_NAME = "MyPref";
    SharedPreferences sharedPreferences;
    DatabaseManager dbManager;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        username = findViewById(R.id.uName);
        password = findViewById(R.id.pass);
        userId = findViewById(R.id.userId);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        id = sharedPreferences.getLong("id", 0);
        fetch();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update:
                update();
                break;
            case R.id.delete:
                delete();
                break;
        }
    }

    private void fetch() {
        Cursor cursor = dbManager.Fetch(id);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_NAME));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PASSWORD));
            username.setText(name);
            password.setText(pass);
        }
    }

    private void update() {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        dbManager.Update(id, name, pass);
    }

    private void delete() {
        dbManager.Delete(id);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(this,"Logging out",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}