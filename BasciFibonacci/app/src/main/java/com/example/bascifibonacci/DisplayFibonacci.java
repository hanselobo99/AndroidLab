package com.example.bascifibonacci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayFibonacci extends AppCompatActivity {
    ListView listView;
    int limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fibonacci);
        Intent intent = getIntent();
        limit = intent.getIntExtra("Limit", 0);
        listView = findViewById(R.id.listView);
        displayFibonacciSeries();

    }

    protected void displayFibonacciSeries() {
        if (limit <= 0) {
            Toast.makeText(DisplayFibonacci.this, "Enter a vallid limit", Toast.LENGTH_LONG).show();
        } else {
            ArrayList<Integer> data = new ArrayList<>();
            int f1 = 0;
            int f2 = 1;
            int f3;

            if (limit == 1) {
                data.add(0);
            } else if (limit == 2) {
                data.add(0);
                data.add(1);
            } else {
                data.add(0);
                data.add(1);
                for (int i = 0; i < limit; i++) {
                    f3 = f1 + f2;
                    f1 = f2;
                    f2 = f3;
                    data.add(f3);
                }
            }
            ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(DisplayFibonacci.this, android.R.layout.simple_list_item_1, data);
            listView.setAdapter(arrayAdapter);
        }
    }
}