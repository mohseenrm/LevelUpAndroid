package com.example.mohseenmukaddam.levelup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.honorato.multistatetogglebutton.MultiStateToggleButton;



/**
 * Created by mohseenmukaddam on 10/27/16.
 */
@EActivity( R.layout.task_builder_2 )
public class TestLayout extends AppCompatActivity {

    @ViewById( R.id.mstb_multi_id_1 )
    MultiStateToggleButton skillButtons1;

    @ViewById( R.id.mstb_multi_id_2 )
    MultiStateToggleButton skillButtons2;

    @ViewById
    EditText task_name;

    @ViewById
    EditText task_description;

    //@BindView( R.id.mstb_multi_id_1 ) MultiStateToggleButton skillButtons1;
    //@BindView( R.id.mstb_multi_id_2 ) MultiStateToggleButton skillButtons2;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        //setContentView(R.layout.task_builder);
        // setContentView(R.layout.user_creation);
        //added butterknife
        //skillButtons1.enableMultipleChoice( true );
        //skillButtons2.enableMultipleChoice( true );


        //this.L = new CharacterAnimated( )
    }
    @AfterViews
    void setMultipleChoiceButtons(){
        //added android annotations
        skillButtons1.enableMultipleChoice( true );
        skillButtons2.enableMultipleChoice( true );
    }

    @Click( R.id.button_confirm )
    void validate_and_confirm(){
        final String task_name_str = task_name.getText().toString();
        final String task_desc = task_description.getText().toString();
        if( task_desc != null && task_name_str != null ){
            //TODO: load profile page
            // TODO: add record to database
            //setContentView( R.layout.activity_profile_ui );



            startActivity(new Intent(TestLayout.this, Home_Activity.class));
        }
        else
            Toast.makeText( this, "Enter some valid name and description", Toast.LENGTH_SHORT ).show();
        }

}