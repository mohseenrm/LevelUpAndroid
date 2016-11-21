package com.example.mohseenmukaddam.levelup;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Hitendra on 11/20/2016.
 */

public class TaskTimer extends Service {

    final static String MY_ACTION = "MY_ACTION";

    private long timeElapsed = 0;
    private Date timeLapseStart = new Date();
    private Boolean running = true;
    private MyThread myThread;
    private Intent intent;
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        running = true;
        Log.d("Service"," Thread is Starting");
        timeLapseStart = new Date();
        myThread = new MyThread();
        myThread.start();
        //Toast.makeText(TaskTimer.this, "TaskTimer Service Started",Toast.LENGTH_SHORT);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("Service","Into Destroy");
        running = false;
        timeElapsed = (new Date()).getTime() - timeLapseStart.getTime();
        intent = new Intent(TaskTimer.MY_ACTION);
        Log.d("Service","sending Broadcast "+timeElapsed);
        intent.putExtra("TimePassed", timeElapsed);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        //Toast.makeText(TaskTimer.this, "Time Elapsed ="+timeElapsed,Toast.LENGTH_SHORT);
        // Let the main application know that service is completed a
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyThread extends Thread{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(running){
                try {
                        Log.d("Thread","Thread is Running");
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
//                timeElapsed = (new Date()).getTime() - timeLapseStart.getTime();
//                intent = new Intent(TaskTimer.MY_ACTION);
//                Log.d("Service","sending Broadcast "+timeElapsed);
//                intent.putExtra("TimePassed", timeElapsed);
//                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
            }
    }

}
