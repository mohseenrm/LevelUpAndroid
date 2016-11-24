package com.example.mohseenmukaddam.levelup;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Santosh on 11/21/2016.
 */

public class Utils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase(){
        if(mDatabase == null){
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
    return mDatabase;
    }


}
