package com.example.kproductions.dailypsalm.notification;

import android.content.Context;
import android.media.MediaPlayer;

public class MyMediaPlayer extends MediaPlayer{
    public Context context;
    public int resource;

    public MyMediaPlayer(Context context, int resource) {
        this.context = context;
        this.resource = resource;

        create(context,resource);
    }


    public void Start(){
        start();
    }

    public void Stop(){
        stop();
    }

    public void Pause(){
        pause();
    }
}
