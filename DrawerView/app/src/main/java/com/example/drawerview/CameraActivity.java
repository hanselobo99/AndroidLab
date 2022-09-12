package com.example.drawerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMAGE = 401;
    private static final int TAKE_IMAGE = 402;
    ImageView profileImg;
    Button browseBtn, takeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        profileImg = findViewById(R.id.ivProfilePic);
        browseBtn = findViewById(R.id.btnBrowsePic);
        takeBtn = findViewById(R.id.btnTakePicture);
        browseBtn.setOnClickListener(this);
        takeBtn.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(CameraActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CameraActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    402);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBrowsePic:
                Intent browseIntent = new Intent();
                browseIntent.setType("image/*");
                browseIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(browseIntent, PICK_IMAGE);
                break;
            case R.id.btnTakePicture:
                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);   //android.provider.MediaStore.ACTION_IMAGE_CAPTURE
                startActivityForResult(takeIntent, TAKE_IMAGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PICK_IMAGE:
                    profileImg.setImageURI(data.getData());
                    break;
                case TAKE_IMAGE:
                    Bitmap captureImage = (Bitmap) data.getExtras().get("data");
                    profileImg.setImageBitmap(captureImage);
                    break;
            }
        }
    }
}