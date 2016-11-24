package com.example.mohseenmukaddam.levelup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohseenmukaddam.levelup.baseclasses.Task;
import com.example.mohseenmukaddam.levelup.baseclasses.TaskTest;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Mohd on 11/19/2016.
 */

public class TaskActivity extends Fragment {
//
//    String username;
//    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("tasklist");
//    ArrayList tasks=new ArrayList<>();
//    @ViewById(R.id.tasklist)
//    ListView taskView;
//
//    FirebaseListAdapter mAdapter;
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.task_builder_2, container, false);
//    MainActivity.User user;
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseUser new_user = auth.getCurrentUser();
//        String username = new_user.getEmail();
//        return v;
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mAdapter.cleanup();
//    }
//
//    @AfterViews
//    void setListViewData(){
//        //added android annotations
//       // mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        mAdapter = new FirebaseListAdapter<Task>(super.getActivity(), Task.class, android.R.layout.two_line_list_item, mDatabase) {
//            @Override
//            protected void populateView(View view, Task task, int position) {
//                ((TextView) view.findViewById(android.R.id.text1)).setText(task.getName());
//                ((TextView) view.findViewById(android.R.id.text2)).setText(task.getDescription());
//
//            }
//        };
//        taskView.setAdapter(mAdapter);
//
//    }



    FirebaseAuth auth = FirebaseAuth.getInstance();
    private String currentUserId;
    DatabaseReference ref;
    FirebaseListAdapter mAdapter;
    ListView taskRecyclerView;

    private Boolean toggle = true;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_user_task, container, false);
        taskRecyclerView = (ListView) v.findViewById(R.id.tasklist);
        // start listening for refresh local file list in
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mYourBroadcastReceiver,
                new IntentFilter(TaskTimer.MY_ACTION));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(auth.getCurrentUser()!=null){
            currentUserId = auth.getCurrentUser().getUid();
        }
        ref =  Utils.getDatabase().getReference().child("/users/"+currentUserId+"/profile/taskList");


        FirebaseListAdapter<Task> mAdapter = new FirebaseListAdapter<Task>(getActivity(), Task.class, android.R.layout.two_line_list_item, ref) {
            @Override
            protected void populateView(View view, Task chatMessage, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(chatMessage.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(chatMessage.getDescription());

            }
        };
        taskRecyclerView.setAdapter(mAdapter);

        taskRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast.makeText(getActivity(), "myPos "+i, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), TaskTimer.class);

                // Task name here
                Task task = (Task) av.getItemAtPosition(i);
                String data= task.getName();
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                TextView tv1 = (TextView) view.findViewById(android.R.id.text2);
                Log.d("TASKNAME IS:",data);
                intent.putExtra("taskName",data);

                if (toggle) { // Service Started
                    // Start Service to get time duration
                    getActivity().startService(intent);
                    toggle = Boolean.FALSE;
                    tv.setBackgroundColor(Color.GREEN);
                    tv1.setBackgroundColor(Color.GREEN);

                }else{ // Service Stopped
              //      Log.d("LISTENER","Into Stop");
                    getActivity().stopService(intent);

                    toggle = Boolean.TRUE;
                    tv.setBackgroundColor(Color.BLACK);
                    tv1.setBackgroundColor(Color.BLACK);
                }

            }
        });
    }

    @Override
    public void onDestroyView()
    {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mYourBroadcastReceiver);
        super.onDestroyView();
    }

    private final BroadcastReceiver mYourBroadcastReceiver = new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            // now you can call all your fragments method here
            // Update Function Should go here
            long data = intent.getLongExtra("TimePassed", 0);
            String taskName = intent.getStringExtra("taskName");

            Log.d("RECEIVER", "Task: "+taskName);
            Log.d("RECEIVER", "Task Duration: "+data);

            // Take TaskNamefrom service and pass to MOMOM
            //Toast.makeText(getContext(), "BroadCast Received"+data, Toast.LENGTH_SHORT);

        }
    };
}
