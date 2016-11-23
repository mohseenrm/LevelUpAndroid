package com.example.mohseenmukaddam.levelup;

/**
 * Created by Mohd on 11/12/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.mohseenmukaddam.levelup.baseclasses.Player;
import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.example.mohseenmukaddam.levelup.baseclasses.Skillset;
import com.example.mohseenmukaddam.levelup.graph.RadarChartView2;
import com.github.mikephil.charting.charts.PieChart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.LinkedHashMap;
import java.util.Map;

import static android.graphics.Paint.Style.FILL;

/**
 * Created by hp1 on 21-01-2015.
 */
@EFragment( R.layout.activity_profile_ui )
public class Tab1_Activity extends Fragment {
    private RelativeLayout profileInfo;
    private ImageView profilePic;
    private PieChart mChart;
    private View v;

    @ViewById
    RadarChartView2 radar_chart;

    @ViewById(R.id.level)
    EditText level;
    @ViewById(R.id.health_bar)
    RoundCornerProgressBar health_bar;
    @ViewById(R.id.exp_bar)
    RoundCornerProgressBar exp_bar;





    @AfterViews
    void init_radar() {
        // Prepare the data. We're going to show the top ten cheese producing U.S. states in 2013 (in 1,000 pounds)
        // IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP
        //TODO: connect to db and pull latest stats

            DatabaseReference mRef = Utils.getDatabase().getReference().child("/users/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile");
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot msnapshot : dataSnapshot.getChildren()) {
                        if (msnapshot.getKey().equals("player")) {
                            Player current_user = msnapshot.getValue(Player.class);
                            int levelI = current_user.getLevel();
                            level.setText(Integer.toString(levelI));

                            health_bar.setProgress((float) current_user.getHealth());
                            exp_bar.setProgress((float) current_user.getExp());
                        }
                        if(msnapshot.getKey().equals("skillset")) {
                            Skillset current_user = msnapshot.getValue(Skillset.class);
                            final Map<String, Float> axis = new LinkedHashMap<>(6);
                            axis.put("IQ", (float) current_user.getIq());
                            axis.put("CR", (float) current_user.getCreativity());
                            axis.put("ST", (float) current_user.getStrength());
                            axis.put("EN", (float) current_user.getEndurance());
                            axis.put("CH", (float) current_user.getCharisma());
                            axis.put("LD", (float) current_user.getLeadership());

                            // Set your data to the view
                            //final RadarChartView chartView = (RadarChartView) findViewById(R.id.radar_chart);
                            radar_chart.invalidate();
                            radar_chart.setAxis( axis );
                            Toast.makeText(getActivity(), "current_user_updated +"+current_user.getIq() , Toast.LENGTH_SHORT).show();

                            radar_chart.setAxisMax( 100.000F );         // set max value for the chart
                            //chartView.addOrReplace("WI", 2855.681F); // add new axis
                            //chartView.addOrReplace("OH", 281.59F);   // change the existing value
                            radar_chart.setAutoSize( false );             // auto balance the chart
                            radar_chart.setCirclesOnly( false );          // if you want circles instead of polygons
                            radar_chart.setChartStyle( FILL );           // chart drawn with this style will be filled not stroked
                            radar_chart.setSmoothGradient( true );

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });






    }


    //public Profile currentProfile = ((Home_Activity)getActivity()).current_user;
//    public void getProfileFromDB(){
//
//        if(FirebaseAuth.getInstance() != null){
//            DatabaseReference mRef= Utils.getDatabase().getReference().child("/users/"+FirebaseAuth.getInstance().getCurrentUser().getUid() );
//            mRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot msnapshot:dataSnapshot.getChildren()){
//                        if(msnapshot.getKey().equals("profile")){
//                            currentProfile = msnapshot.getValue(Profile.class);
//                            Log.v("santiDB","profile"+currentProfile.toString());
//                            //Toast.makeText( getContext(),currentProfile.toString(),Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                }
//            });
//        }
//    }

    private void updateRadarChartView(Map<String, Float> axis){
        if(axis.size() != 6){
            System.err.println("Pass All 6 Profile Parameters i.e " +
                    "{IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP}");
        }


        radar_chart.setAxis(axis);

        radar_chart.setAxisMax(2855.681F);         // set max value for the chart
        //chartView.addOrReplace("OH", 281.59F);   // change the existing value
        radar_chart.setAutoSize(true);             // auto balance the chart
        radar_chart.setCirclesOnly(true);          // if you want circles instead of polygons
        radar_chart.setChartStyle(FILL);           // chart drawn with this style will be filled not stroked

        radar_chart.invalidate();
    }

//    @AfterInject
//    void setCurrentProfile() {
//        this.getProfileFromDB();
//        Toast.makeText( getContext(), "Got profile, level is: " + currentProfile.getPlayer().getLevel(), Toast.LENGTH_SHORT ).show();
//    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        getProfileFromDB();
//        Toast.makeText( getContext(), "Got profile, level is: " + currentProfile.getPlayer().getLevel(), Toast.LENGTH_SHORT ).show();
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.activity_profile_ui,container,false);

        return v;
    }

}
