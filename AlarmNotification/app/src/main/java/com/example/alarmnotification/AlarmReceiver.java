package com.example.alarmnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    NotificationManager manager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm is up", Toast.LENGTH_SHORT).show();
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Channel", "Reminder", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("www.google.com"));

        PendingIntent pi = PendingIntent.getActivity(context, 123, i, PendingIntent.FLAG_MUTABLE);
        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        MediaPlayer mp = MediaPlayer.create(context, ringToneUri);
        mp.start();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Channel");
        builder.setContentTitle("My notification")
                .setContentText("Alarm Notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSound(ringToneUri);
        manager.notify(456, builder.build());
    }
}
