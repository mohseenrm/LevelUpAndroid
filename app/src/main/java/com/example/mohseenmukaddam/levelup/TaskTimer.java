package com.example.mohseenmukaddam.levelup;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Hitendra on 11/20/2016.
 */

public class TaskTimer extends Service {

    final static String MY_ACTION = "MY_ACTION";

    private long timeElapsed = 0;
    Date timeLapseStart;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(TaskTimer.this, "TaskTimer Service Started",Toast.LENGTH_SHORT);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        timeElapsed = (new Date()).getTime() - timeLapseStart.getTime();
        Intent intent = new Intent();
        intent.setAction(MY_ACTION);
        intent.putExtra("TimePassed", timeElapsed);
        sendBroadcast(intent);
        Toast.makeText(TaskTimer.this, "Time Elapsed ="+timeElapsed,Toast.LENGTH_SHORT);
        // Let the main application know that service is completed a
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
