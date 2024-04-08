package com.wguc196.schooltracker.helpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.wguc196.schooltracker.R;

public class Receiver extends BroadcastReceiver {
    String channelID = "test";
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //channelID = intent.getStringExtra("type");
        Toast.makeText(context, intent.getStringExtra("reminder"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context, channelID);
        Notification notification = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("reminder"))
                .setContentTitle("Alert!").build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, notification);

    }

    private void createNotificationChannel(Context context, String CHANNEL_ID) {
        CharSequence name = "channel";
        String description = "description";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}