package com.example.mohseenmukaddam.levelup;

/**
 * Created by Mohd on 11/12/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mohseenmukaddam.levelup.baseclasses.Player;
import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.example.mohseenmukaddam.levelup.baseclasses.Skillset;
import com.example.mohseenmukaddam.levelup.baseclasses.Task;
import com.example.mohseenmukaddam.levelup.baseclasses.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
@EFragment( R.layout.frag_leaderboard )
public class Tab3_Activity extends Fragment {

//    @ViewById
//    Spinner spinner1;
//    @ViewById
//    Spinner spinner2;
//    @ViewById
//    TableLayout tbl;

//    /*@Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v =inflater.inflate(R.layout.user_creation,container,false);
//        return v;
//    }}*/

    DatabaseReference mRef = Utils.getDatabase().getReference().child("users");
    Query queryRef;

    //child("/users/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile");
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems=new ArrayList<String>();
    @ViewById(R.id.leaderBoard)
    ListView   leaderBoard;



//    @AfterViews
//    void initUI() {
////
////        // Populate Both the dropdown
////        String[] items = new String[]{"Local", "Global"};
////        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
////        spinner1.setAdapter(adapter);
////
////        // IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP,INVALID_DEFAULT
////        String[] items1 = new String[]{"IQ", "Charisma", "Endurance", "Strength", "Creativity", "Leadership"};
////        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items1);
////        spinner2.setAdapter(adapter1);
////
////        // Populate Table based on database Records
////        for(int i=1; i<50; i++) {
////            // delcare a new row
////            TableRow newRow = new TableRow(getContext());
////
////            TextView tvRanking = new TextView(getContext());
////            tvRanking.setText(""+i);
////            TextView tvName = new TextView(getContext());
////            tvName.setText("Player"+i);
////            TextView tvScore = new TextView(getContext());
////            tvScore.setText(""+i*5);
////
////
////            // add views to the row
////            newRow.addView(tvRanking);
////            newRow.addView(tvName);
////            newRow.addView(tvScore);
////
////            // add the row to the table layout
////            tbl.addView(newRow);
////        }
////
////    }
//
//        this.setOnDataChangeListener();
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);



        //queryRef = mRef.orderByChild("/profile/player/level").limitToLast(10);

        FirebaseListAdapter<User> mAdapter = new FirebaseListAdapter<User>(getActivity(), User.class, android.R.layout.two_line_list_item, mRef.orderByChild("/profile/player/level").limitToFirst(10)) {
            @Override
            protected void populateView(View view, User chatMessage, int position) {

                if (chatMessage.getUsername() != "Default") {
                    ((TextView) view.findViewById(android.R.id.text1)).setText(chatMessage.getUsername());
                    ((TextView) view.findViewById(android.R.id.text2)).setText(Integer.toString(chatMessage.getprofile().getPlayer().getLevel()));
                }

            }
        };
        leaderBoard.setAdapter(mAdapter);





    }




}



