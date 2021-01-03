package com.example.studyplanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notifications extends BroadcastReceiver
{
    public static final String CHANNEL_1_ID =  "channel1";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("This is the reminder")
                .setContentText("classes starting in 15 minutes !!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, notification.build());
    }
}
