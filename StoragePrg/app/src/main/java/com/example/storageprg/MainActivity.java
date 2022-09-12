package com.example.storageprg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button external, internal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        external = findViewById(R.id.external);
        internal = findViewById(R.id.internalBtn);
        external.setOnClickListener(this);
        internal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.external:
                intent = new Intent(MainActivity.this, ExternalStorage.class);
                startActivity(intent);
                break;
            case R.id.internalBtn:
                intent = new Intent(MainActivity.this, InternalStorage.class);
                startActivity(intent);
                break;
        }

    }


}