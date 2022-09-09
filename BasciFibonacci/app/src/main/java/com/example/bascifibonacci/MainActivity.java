package com.example.bascifibonacci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.InCallService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button generate;
    EditText limit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generate = findViewById(R.id.submit);
        limit = findViewById(R.id.limit);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(limit.getText().toString());
                Intent intent = new Intent(MainActivity.this,DisplayFibonacci.class);
                intent.putExtra("Limit",number);
                startActivity(intent);
            }
        });
    }

}