package com.example.backgroundthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView progressText;
    ImageView logoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressText = findViewById(R.id.tvProgress);
        logoImage = findViewById(R.id.image_logo);
        progressText.setVisibility(View.INVISIBLE);

    }
    public void startDownload(View v){
        DownloadImage downloadImage = new DownloadImage();
        downloadImage.execute("https://sac-aimit.in/alogo.png");
    }

    class  DownloadImage extends AsyncTask<String,Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressText.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try{
                URL imageUrl = new URL(strings[0]);
                publishProgress(25);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                publishProgress(50);
                InputStream inputStream = connection.getInputStream();
                publishProgress(75);
                bmp = BitmapFactory.decodeStream(inputStream);
                publishProgress(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressText.setText("Downloaded: "+values[0]+ "%");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            logoImage.setImageBitmap(bitmap);
            progressText.setVisibility(View.INVISIBLE);
        }
    }

}