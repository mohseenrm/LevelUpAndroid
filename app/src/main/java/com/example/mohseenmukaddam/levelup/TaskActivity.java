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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.example.mohseenmukaddam.levelup.baseclasses.Task;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by Mohd on 11/19/2016.
 */

public class TaskActivity extends Fragment {

    HashMap<String, Object> postValues = new HashMap<>();
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
    private int startedId = -1;

    Profile currentUser,latestuser;

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
        this.setOnDataChangeListener();
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

                // This will make sure we stop one task before startting other
                if(startedId != -1 && i!=startedId)
                    i = startedId;

                // Task name here
                Task task = (Task) av.getItemAtPosition(i);
                String data= task.getName();
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                TextView tv1 = (TextView) view.findViewById(android.R.id.text2);
                Log.d("TASKNAME IS:",data);
                intent.putExtra("taskName",data);
                intent.putExtra("task",task);

                if (toggle) { // Service Started

                    // Start Service to get time duration
                    getActivity().startService(intent);
                    startedId = i;
                    toggle = Boolean.FALSE;
                    tv.setBackgroundColor(Color.GREEN);
                    tv1.setBackgroundColor(Color.GREEN);

                }else{ // Service Stopped

              //      Log.d("LISTENER","Into Stop");
                    getActivity().stopService(intent);
                    startedId = -1;
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
            long timeElapsed = intent.getLongExtra("TimePassed", 0);
            String taskName = intent.getStringExtra("taskName");

            Log.d("RECEIVER", "Task: "+taskName);
            Log.d("RECEIVER", "Task Duration: "+ timeElapsed);
            Task currentTask = (Task) intent.getSerializableExtra("task");

            Log.d("RECEIVER",currentTask.toString());
            // Call Update to modfify the View Here
            // TODO: 11/24/2016 - MoMo to implement

            updateTask( currentTask, timeElapsed*60*10 );
//            int index = 0;
//            for ( Task task : currentUser.getTaskList() ){
//                if( task.getName() == currentTask.getName() ){
//
//                    break;
//                }
//            }

        }};

    void updateTask( Task updateTask, long time ){
       // this.setOnDataChangeListener();
        currentUser.taskComplete( time, updateTask.getListOfSkills() );
        DatabaseReference dRef = Utils.getDatabase().getReference().child("/users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        postValues.put("profile",currentUser );
        dRef.setValue(postValues);



    }

    //Assuming this is set till service get started
    void setOnDataChangeListener(){
        DatabaseReference mRef = Utils.getDatabase().getReference().child("/users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot msnapshot : dataSnapshot.getChildren()) {
                    if (msnapshot.getKey().equals("profile")) {
//                        setPlayerWidgets( msnapshot.getValue(Profile.class) );
                        currentUser = msnapshot.getValue(Profile.class);

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}


