package com.example.drawerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.io.IOException;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_AUDIO = 101;
    TextView statusTxt;
    Button chooseBtn, recordBtn;
    ToggleButton toggleButton;

    MediaPlayer player = null;
    MediaRecorder recorder = null;

    String storePath = "MyVoice.3gp";
    File audioFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        statusTxt = findViewById(R.id.txtStatus);
        chooseBtn = findViewById(R.id.btnChooseAudio);
        recordBtn = findViewById(R.id.btnRecordAudio);
        toggleButton = findViewById(R.id.toggleButton);
        chooseBtn.setOnClickListener(this);
        recordBtn.setOnClickListener(this);
        audioFile = new File(getExternalFilesDir("MyDir"), storePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getBaseContext().checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChooseAudio:
                Intent chooseIntent = new Intent();
                chooseIntent.setType("audio/*");
                chooseIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(chooseIntent, PICK_AUDIO);
                break;
            case R.id.btnRecordAudio:
                statusTxt.setText("The Recording will be stored at :" + storePath);
                if (recordBtn.getText().equals("RECORD AUDIO")) {
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    recorder.setOutputFile(audioFile);
                    try {
                        recorder.prepare();

                        recorder.start();

                        recordBtn.setText("STOP RECORDING");
                    } catch (IOException e) {
                        Log.d("MyError", e.toString());
                        e.printStackTrace();
                    }
                } else {

                    try {
                        recorder.stop();
                        player = new MediaPlayer();
                        player.setDataSource(audioFile.toString());
                        player.prepare();
                        player.start();
                        recordBtn.setText("RECORD AUDIO");
                    } catch (Exception e) {
                        Log.d("MyError", e.toString());
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.toggleButton:
                if (player != null && player.isPlaying()) {
                    player.pause();
                } else if (player != null) {
                    player.release();
                    player = null;
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PICK_AUDIO:
                    player = MediaPlayer.create(getBaseContext(), data.getData());
                    player.start();
                    statusTxt.setText("Media is playing from :" + data.getDataString());
                    toggleButton.toggle();
                    break;
            }
        }
    }
}