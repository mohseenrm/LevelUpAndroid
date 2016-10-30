package com.example.mohseenmukaddam.levelup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import butterknife.BindView;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class TestLayout extends AppCompatActivity {
    @BindView( R.id.mstb_multi_id_1 ) MultiStateToggleButton skillButtons1;
    @BindView( R.id.mstb_multi_id_2 ) MultiStateToggleButton skillButtons2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_builder);

        skillButtons1.enableMultipleChoice( true );
        skillButtons2.enableMultipleChoice( true );

        //this.L = new CharacterAnimated( )
    }
}
