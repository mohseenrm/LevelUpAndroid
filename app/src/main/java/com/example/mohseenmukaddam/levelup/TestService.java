package com.example.mohseenmukaddam.levelup;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import static com.example.mohseenmukaddam.levelup.R.id.task_description;
import static com.example.mohseenmukaddam.levelup.TaskTimer.*;

// Service Not Required in General, get Timestamp after start stop and Calculate the difference to get
// required time elapsed.

@EFragment( R.layout.activity_test_service )
public class TestService extends Fragment {

    @ViewById (R.id.StartService)
    Button StartService;

    @ViewById (R.id.stopService)
    Button stopService;

    @ViewById
    TextView textViewService;

    private final BroadcastReceiver mYourBroadcastReceiver = new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            // now you can call all your fragments method here
            long data = intent.getLongExtra("TimePassed", 0);
            Log.d("RECEIVER", "Received Content"+data);
            Toast.makeText(getContext(), "BroadCast Received"+data, Toast.LENGTH_SHORT);
            textViewService.setText(""+data);
        }
    };

    @Override
    public void onDestroyView()
    {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mYourBroadcastReceiver);
        super.onDestroyView();
    }

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        // start listening for refresh local file list in
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mYourBroadcastReceiver,
                new IntentFilter(TaskTimer.MY_ACTION));

        return inflater.inflate(R.layout.activity_test_service, null, true);
    }





    @Click( R.id.StartService )
    void listenStart(){
        //Start our own service
        Intent intent = new Intent(getActivity(),TaskTimer.class);
        getActivity().startService(intent);
    }

    @Click( R.id.stopService )
    void listenStop(){
        Log.d("LISTENER","Into Stop");
        getActivity().stopService(new Intent(getActivity(),TaskTimer.class));
    }



}
