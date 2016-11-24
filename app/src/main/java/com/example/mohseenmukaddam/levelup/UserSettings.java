package com.example.mohseenmukaddam.levelup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;

/**
 * Created by Hitendra on 11/24/2016.
 */


public class UserSettings extends AppCompatActivity {
    private final String TAG = "SETTINGS ACTIVITY";
    private ImageView clickedImageView = null;
    private String avatarName = "";

    EditText editText ;
    List<ImageView> imageViews;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_creation);
        init();
    }

    public void resetRemainingImageViews(int id){
        for(int i=0; i<imageViews.size(); i++){
            ImageView tmp;
            tmp = imageViews.get(i);
            if(tmp.getId() != id){
                tmp.setBackgroundResource(R.drawable.button_not_pressed);
            }
        }
    }
    public void init(){
        FButton fButton;
        editText = (EditText) findViewById(R.id.editTextAvName);
        imageViews = new ArrayList<>(12);
        clickedImageView = (ImageView) findViewById(R.id.imageView1);

        // Populate ImageViews
        for(int i=1; i<=12; i++){

            imageViews.add((ImageView) findViewById(R.id.imageView1));
            imageViews.add((ImageView) findViewById(R.id.imageView2));
            imageViews.add((ImageView) findViewById(R.id.imageView3));
            imageViews.add((ImageView) findViewById(R.id.imageView4));
            imageViews.add((ImageView) findViewById(R.id.imageView5));
            imageViews.add((ImageView) findViewById(R.id.imageView6));
            imageViews.add((ImageView) findViewById(R.id.imageView7));
            imageViews.add((ImageView) findViewById(R.id.imageView8));
            imageViews.add((ImageView) findViewById(R.id.imageView9));
            imageViews.add((ImageView) findViewById(R.id.imageView10));
            imageViews.add((ImageView) findViewById(R.id.imageView11));
            imageViews.add((ImageView) findViewById(R.id.imageView12));
        }

        // Registering ImageViews Listeners
        for (int i=0; i<imageViews.size(); i++){
            ImageView currentImgView = (ImageView) imageViews.get(i);
            currentImgView.setClickable(true);
            currentImgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedImageView = (ImageView) v;
                    Log.d(TAG,"Image CLicked: "+clickedImageView.getId());

                    clickedImageView.setBackgroundResource(R.drawable.button_pressed);
                    resetRemainingImageViews(clickedImageView.getId());

                }
            });
        }

        // Registering Confirm Button Listeners
        fButton = (FButton) findViewById(R.id.button4);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doWorkAfterComfim();
            }
        });
    }


    public void doWorkAfterComfim(){

        if (editText.getText().length() != 0){
            avatarName = editText.getText().toString();
            //Log.d(TAG,"Doing Work ..."+ editText.getText());
        }else {
            avatarName = "Player1";
            //Log.d(TAG,"Done TextView");
        }

        // TODO: 11/24/2016 ---> Add here whatever Confirm Button should do
        // You have : clickedImageView amd avatarName to play with
        // save string in sharedPreferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("avatarName", avatarName); // here string is the value you want to save
        String imageName = getResources().getResourceEntryName(clickedImageView.getId());
        editor.putString("imageName",imageName);
        editor.commit();


        Toast.makeText(this,"Avatar: "+avatarName+" Image: "+imageName,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UserSettings.this, Home_Activity.class));

    }


}
