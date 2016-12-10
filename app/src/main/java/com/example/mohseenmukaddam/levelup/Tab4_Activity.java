package com.example.mohseenmukaddam.levelup;


import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohseenmukaddam.levelup.baseclasses.GuildTask;
import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.example.mohseenmukaddam.levelup.baseclasses.Task;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Mohd on 11/19/2016.
 */
public class Tab4_Activity extends Fragment {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private String currentUserId;
    DatabaseReference ref;
    //    FirebaseListAdapter mAdapter;
    ListView taskRecyclerView;
    private int startedId = -1;

    Profile currentUser, latestuser;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.guild, container, false);
        taskRecyclerView = (ListView) v.findViewById(R.id.guildtask);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        if(auth.getCurrentUser()!=null){
//            currentUserId = auth.getCurrentUser().getUid();
//        }
//        ref = Utils.getDatabase().getReference().child("/Guild");

        if(auth.getCurrentUser()!=null){
            currentUserId = auth.getCurrentUser().getUid();
        }
        ref =  Utils.getDatabase().getReference().child("/users/"+"1qZQ4BDwsJf82bD6fA8C9wY9uNd2"+"/profile/taskList");

        final FirebaseListAdapter<Task> mAdapter = new FirebaseListAdapter<Task>(getActivity(), Task.class, android.R.layout.two_line_list_item, ref) {
            @Override
            protected void populateView(View view, Task chatMessage, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(chatMessage.getName());

                ((TextView) view.findViewById(android.R.id.text2)).setText(chatMessage.getDescription());
//this
            }
        };

        taskRecyclerView.setAdapter(mAdapter);


    }
}
