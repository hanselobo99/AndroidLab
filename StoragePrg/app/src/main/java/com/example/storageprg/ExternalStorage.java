package com.example.storageprg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorage extends AppCompatActivity implements View.OnClickListener {
    EditText fileName, fileText;
    String filePath = "MyFileDir";
    Button load, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        fileName = findViewById(R.id.fileName);
        fileText = findViewById(R.id.fileText);
        load = findViewById(R.id.btnLoad);
        save = findViewById(R.id.btnSave);
        load.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoad:
                loadData();
                break;
            case R.id.btnSave:
                saveData();
                break;
        }
    }

    public boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public void saveData() {
        if (isExternalStorageWritable()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
            File txtFile = new File(getExternalFilesDir(filePath), fileName.getText().toString());
            try {
                FileOutputStream fos = new FileOutputStream(txtFile);
                fos.write(fileText.getText().toString().getBytes());
                fos.close();
                Toast.makeText(this, "File is saved", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileText.setText("");
        } else {
            Toast.makeText(this, "Cannot save the file", Toast.LENGTH_LONG).show();
        }
    }

    public void loadData() {
        FileInputStream fis = null;
        try {
            File txtFile = new File(getExternalFilesDir(filePath), fileName.getText().toString());
            fis = new FileInputStream(txtFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            fileText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
