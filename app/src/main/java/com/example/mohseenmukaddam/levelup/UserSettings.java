package com.example.mohseenmukaddam.levelup;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;

/**
 * Created by Hitendra on 11/24/2016.
 */


public class UserSettings extends AppCompatActivity {
    private final String TAG = "SETTINGS ACTIVITY";
    private ImageView clickedImageView = null;
    EditText editText ;

    public void initVar(){
        editText = (EditText) findViewById(R.id.editTextAvName);
        List<ImageView> imageViews = new ArrayList<>(12);
        FButton fButton;

        for (int i=0; i<imageViews.size(); i++){
            ImageView currentImgView = (ImageView) imageViews.get(i);
            currentImgView.setClickable(true);
            currentImgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(YourActivityName.this,
//                            "The favorite list would appear on clicking this icon",
//                            Toast.LENGTH_LONG).show();
                    clickedImageView = (ImageView) v;
                    //clickedImageView.setColorFilter(Color.BLUE);
                    //Log.d(TAG,clickedImageView.get);
                    Log.d(TAG,"Image CLicked: "+clickedImageView.getId());

                    //clickedImageView.setBackgroundResource(R.drawable.button_pressed);
                }
            });
        }

        fButton = (FButton) findViewById(R.id.button4);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doWorkAfterComfim();
            }
        });
    }


    public void doWorkAfterComfim(){
        Log.d(TAG,"Button CLicked: Doing Work ...");

        if (editText.getText().length() != 0){
            Log.d(TAG,"Doing Work ..."+ editText.getText());
        }else {
            Log.d(TAG,"Done TextView");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_creation);
        initVar();
    }
}
