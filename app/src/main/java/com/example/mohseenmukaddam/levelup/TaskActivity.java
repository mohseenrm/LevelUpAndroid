package com.example.mohseenmukaddam.levelup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

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




    DatabaseReference ref;
    FirebaseListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_user_task, container, false);
         ref =  Utils.getDatabase().getReference().child("tasklist");

        ListView taskRecyclerView = (ListView) v.findViewById(R.id.tasklist);
        FirebaseListAdapter<TaskTest> mAdapter = new FirebaseListAdapter<TaskTest>(getActivity(), TaskTest.class, android.R.layout.two_line_list_item, ref) {
            @Override
            protected void populateView(View view, TaskTest chatMessage, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(chatMessage.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(chatMessage.getDescription());



            }
        };
        taskRecyclerView.setAdapter(mAdapter);
        return v;
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mAdapter.cleanup();
//    }


}
