package com.example.mohseenmukaddam.levelup;

/**
 * Created by Hitendra on 11/24/2016.
 */


/*
 * Implement this Interface for separate thread for sleep Timer, this wont affect the normal UI operation
 *
 * To call TimerThread
 * new TimerThread(ClassWhichImplementedCallbackClass, timeInSeconds).start();
 */
public interface CallbackClass {
    public void updateOnFinished();
}
