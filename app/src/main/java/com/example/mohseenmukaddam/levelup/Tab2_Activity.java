package com.example.mohseenmukaddam.levelup;

/**
 * Created by Mohd on 11/12/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.honorato.multistatetogglebutton.MultiStateToggleButton;

/**
 * Created by hp1 on 21-01-2015.
 */
@EFragment(R.layout.task_builder_2)
public class Tab2_Activity extends Fragment {


    @ViewById( R.id.mstb_multi_id_1 )
    MultiStateToggleButton skillButtons1;

    @ViewById( R.id.mstb_multi_id_2 )
    MultiStateToggleButton skillButtons2;

    @ViewById( R.id.task_name )
    EditText task_name;

    @ViewById( R.id.task_description )
    EditText task_description;


   /* @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.task_builder_2,container,false);

        return v;
    }
*/

    @Click( R.id.button_confirm )
    void validate_and_confirm(){
        
        final String task_name_str = task_name.getText().toString();
        final String task_desc = task_description.getText().toString();
        if( task_desc != null && task_name_str != null ){
            //TODO: load profile page
            // TODO: add record to database
            //setContentView( R.layout.activity_profile_ui );
        }
        else
            Toast.makeText( super.getActivity(), "Enter some valid name and description", Toast.LENGTH_SHORT ).show();
    }

    @AfterViews
    void setMultipleChoiceButtons(){
        //added android annotations

        skillButtons1.enableMultipleChoice( true );
        skillButtons2.enableMultipleChoice( true );
    }

    /*
     *
     *  To use this code add following Lines
     *  Register BroadcastReceiver to receive event from our service
     *
     * myReceiver = new MyReceiver();
     * IntentFilter intentFilter = new IntentFilter();
     * intentFilter.addAction(MyService.MY_ACTION);
     * registerReceiver(myReceiver, intentFilter);
     *
     *  unregisterReceiver(myReceiver); //To unregister the reciever
     */
    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int datapassed = intent.getIntExtra("TimePassed", 0);

            Toast.makeText(getContext(), "BroadCast Received", Toast.LENGTH_SHORT);
        }
    }

}

