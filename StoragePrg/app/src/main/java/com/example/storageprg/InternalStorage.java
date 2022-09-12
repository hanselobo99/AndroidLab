package com.example.storageprg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorage extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    TextView textView;
    final static String FILE_NAME = "file.txt";
    Button load, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        editText = findViewById(R.id.textBox);
        textView = findViewById(R.id.textView1);
        editText.setText("Hello");
        load = findViewById(R.id.btnLoadInternal);
        save = findViewById(R.id.btnSaveInternal);
        load.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadInternal:
                loadData();
                break;
            case R.id.btnSaveInternal:
                saveData();
                break;
        }
    }

    public void saveData() {
        String text = editText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            editText.getText().clear();
            Toast.makeText(this, "Text saved to:" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadData() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            textView.setText(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}