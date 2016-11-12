package com.example.mohseenmukaddam.levelup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.honorato.multistatetogglebutton.MultiStateToggleButton;



/**
 * Created by mohseenmukaddam on 10/27/16.
 */
@EActivity( R.layout.task_builder )
public class TestLayout extends AppCompatActivity {
    @ViewById( R.id.mstb_multi_id_1 )
    MultiStateToggleButton skillButtons1;

    @ViewById( R.id.mstb_multi_id_2 )
    MultiStateToggleButton skillButtons2;

    @ViewById
    EditText task_input;

    @ViewById
    EditText task_description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.task_builder);


        //this.L = new CharacterAnimated( )
    }
    @AfterViews
    void setMultipleChoiceButtons(){
        //added android annotations
        skillButtons1.enableMultipleChoice( true );
        skillButtons2.enableMultipleChoice( true );
    }
    @Click( R.id.confirm_button )
    void validate_and_confirm(){
        final String task_name = task_input.getText().toString();
        final String task_desc = task_description.getText().toString();

        if( task_desc != null && task_name != null ){
            //TODO: load profile page
            //TODO: add record to database
            setContentView( R.layout.activity_profile_ui );
        }
        else
            Toast.makeText( this, "Enter some valid name and description", Toast.LENGTH_SHORT ).show();

    }
}
