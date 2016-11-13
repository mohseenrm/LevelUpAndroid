package com.example.mohseenmukaddam.levelup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.charts.PieChart;

import org.androidannotations.annotations.EActivity;

/*
 *
 */
@EActivity( R.layout.activity_profile_ui )
public class ProfileUIActivity extends AppCompatActivity {

    private RelativeLayout profileInfo;
    private ImageView profilePic;
    private PieChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ui);
        //profileInfo = (RelativeLayout) findViewById(R.id.ProfileInfo);

        //profilePic = (ImageView)findViewById(R.id.profilePic);

    }
}
