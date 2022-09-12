package com.example.drawerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_VID = 301;
    private static final int TAKE_VID = 302;
    VideoView videoView;
    Button chooseBtn1, recordBtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        chooseBtn1 = findViewById(R.id.btnChooseVideo);
        recordBtn1 = findViewById(R.id.btnRecordVideo);

        chooseBtn1.setOnClickListener(this);
        recordBtn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnChooseVideo:
                Intent videoIntent  = new Intent();
                videoIntent.setType("video/*");
                videoIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(videoIntent,PICK_VID);
                break;
            case R.id.btnRecordVideo:
                Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(cameraIntent,TAKE_VID);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (requestCode){
                case PICK_VID:
                    videoView.setVideoURI(data.getData());
                    videoView.start();
                    break;
                case TAKE_VID:
                    VideoView vView = new VideoView(this);
                    vView.setVideoURI(data.getData());
                    vView.start();
                    break;
            }
        }
    }
}