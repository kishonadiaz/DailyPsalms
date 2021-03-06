package com.example.kproductions.dailypsalm.notification;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.kproductions.dailypsalm.FloatingViewService;
import com.example.kproductions.dailypsalm.MainActivity;
import com.example.kproductions.dailypsalm.R;


public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "com.example.notification.channelId";
    public FloatingViewService floatingViewService;
    public String text;



    @Override
    public void onReceive(Context context, Intent intent) {

        floatingViewService = new FloatingViewService();
        Intent notificationIntent = new Intent(context,floatingViewService.getClass());



        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Toast.makeText(context, "here", Toast.LENGTH_SHORT).show();

        NotificationSystem notifacationSystem = new NotificationSystem(context,CHANNEL_ID);
//
//
        notifacationSystem.sendNotification(FloatingViewService.class, R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground);
//        notifacationSystem.setContentIntent(pendingIntent).build();


        Toast.makeText(context, getThisText(), Toast.LENGTH_SHORT).show();
    }

    public String getThisText() {
        return text;
    }

    public void setThisText(String text) {
        this.text = text;
    }

    public FloatingViewService getFloatingViewService() {
        return floatingViewService;
    }
}
