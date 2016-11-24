package com.example.mohseenmukaddam.levelup;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mohseenmukaddam.levelup.baseclasses.Task;

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
    private String taskName = "";
    private Task task = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        running = true;
        Log.d("Service"," Thread Started ...");
        timeLapseStart = new Date();
        myThread = new MyThread();
        myThread.start();
        taskName = intent.getStringExtra("taskName");
        task = (Task) intent.getSerializableExtra("task");
        //Toast.makeText(TaskTimer.this, "TaskTimer Service Started",Toast.LENGTH_SHORT);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        Log.d("Service","Thread Destroyed ....");
        running = false;

        timeElapsed = (new Date()).getTime() - timeLapseStart.getTime();
        intent = new Intent(TaskTimer.MY_ACTION);
        //Log.d("Service","sending Broadcast "+timeElapsed);

        intent.putExtra("TimePassed", timeElapsed);
        intent.putExtra("taskName", taskName);
        intent.putExtra("task",task);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

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
                        Log.d("Thread","Running ....");
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
    }

}
