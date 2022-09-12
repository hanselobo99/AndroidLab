package com.example.drawerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button audioBtn, videoBtn, cameraBtn;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioBtn = findViewById(R.id.btnAudio);
        videoBtn = findViewById(R.id.btnVideo);
        cameraBtn = findViewById(R.id.btnCamera);
        audioBtn.setOnClickListener(this);
        videoBtn.setOnClickListener(this);
        cameraBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAudio:
                i = new Intent(MainActivity.this, AudioActivity.class);
                startActivity(i);
                break;
            case R.id.btnVideo:
                i = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(i);
                break;
            case R.id.btnCamera:
                i = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(i);
                break;
        }
    }
}