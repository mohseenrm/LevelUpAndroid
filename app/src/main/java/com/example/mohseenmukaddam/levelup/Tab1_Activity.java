package com.example.mohseenmukaddam.levelup;

/**
 * Created by Mohd on 11/12/2016.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.LinkedHashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.Paint.Style.FILL;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by hp1 on 21-01-2015.
 */
@EFragment( R.layout.activity_profile_ui )
public class Tab1_Activity extends Fragment {
    public Profile currentProfile;

    private RelativeLayout profileInfo;
    private ImageView profilePic;
    private PieChart mChart;
    private View v;

    private String imageName= "";
    private String avatarNameStr = "";

    @ViewById
    RadarChartView2 radar_chart;

    @ViewById (R.id.profile_image)
    CircleImageView profile_image;

    @ViewById (R.id.avatarName)
    TextView avatarName;

    @ViewById(R.id.level)
    EditText level;
    @ViewById(R.id.health_bar)
    RoundCornerProgressBar health_bar;
    @ViewById(R.id.exp_bar)
    RoundCornerProgressBar exp_bar;
    @ViewById(R.id.profile_image)
    ImageView pic;

    @Click(R.id.profile_image)
    void redirectToUserSettings() {
        startActivity(new Intent(getActivity(), UserSettings.class));
    }


    @AfterViews
    void init_radar() {
        // Prepare the data. We're going to show the top ten cheese producing U.S. states in 2013 (in 1,000 pounds)
        // IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP
        this.setOnDataChangeListener();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        avatarNameStr = settings.getString("avatarName", "");
        imageName = settings.getString("imageName","");


        String numbers= imageName.replaceAll("[^0-9]", "");
        int id = getResources().getIdentifier("com.example.mohseenmukaddam.levelup:drawable/avatar" + numbers, null, null);
        if(id > 0)
            profile_image.setImageResource(id);
        avatarName.setText(avatarNameStr);


    }

    void setOnDataChangeListener(){
        DatabaseReference mRef = Utils.getDatabase().getReference().child("/users/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot msnapshot : dataSnapshot.getChildren()) {
                    if (msnapshot.getKey().equals("player")) {
                        setPlayerWidgets( msnapshot.getValue(Player.class) );
                    }
                    else if(msnapshot.getKey().equals("skillset")) {
                        setSkillsetWidgets( msnapshot.getValue( Skillset.class ) );
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    void setPlayerWidgets( Player current_user ){
        if(current_user!=null && level != null && health_bar != null && exp_bar!= null) {
            int levelI = current_user.getLevel();
            level.setText(Integer.toString(levelI));

            double health = current_user.getHealth();
            health_bar.setProgress((float) health);
            health_bar.setSecondaryProgress((float) (health + 1.5));

            double exp = current_user.getExp();
            exp_bar.setProgress((float) exp);
            exp_bar.setSecondaryProgress((float) (exp + 1.5));
        }
    }

    void setSkillsetWidgets( Skillset current_user ){
        final Map<String, Float> axis = new LinkedHashMap<>(6);
        axis.put("IQ", (float) current_user.getIq());
        axis.put("CR", (float) current_user.getCreativity());
        axis.put("ST", (float) current_user.getStrength());
        axis.put("EN", (float) current_user.getEndurance());
        axis.put("CH", (float) current_user.getCharisma());
        axis.put("LD", (float) current_user.getLeadership());
        if(radar_chart!=null) {
            radar_chart.invalidate();
            radar_chart.setAxis(axis);

//        Toast.makeText(getActivity(), "current_user_updated +"+current_user.getIq() , Toast.LENGTH_SHORT).show();
//test2
            radar_chart.setAxisMax(100.000F);         // set max value for the chart

            radar_chart.setAutoSize(false);             // auto balance the chart
            radar_chart.setCirclesOnly(false);          // if you want circles instead of polygons
            radar_chart.setChartStyle(FILL);           // chart drawn with this style will be filled not stroked
            radar_chart.setSmoothGradient(true);
        }
    }

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



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.activity_profile_ui,container,false);

        return v;
    }

}
