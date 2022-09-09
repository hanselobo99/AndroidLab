package com.example.simpleaudioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Start(View view) {
        Toast.makeText(this, "Music started", Toast.LENGTH_SHORT).show();
        startService(new Intent(this, MyService.class));
    }

    public void Stop(View view) {
        Toast.makeText(this, "Music stopped", Toast.LENGTH_SHORT).show();
        stopService(new Intent(this, MyService.class));
    }
}