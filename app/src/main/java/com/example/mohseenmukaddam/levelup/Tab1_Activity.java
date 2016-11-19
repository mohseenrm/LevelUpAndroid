package com.example.mohseenmukaddam.levelup;

/**
 * Created by Mohd on 11/12/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dzaitsev.android.widget.RadarChartView;
import com.github.mikephil.charting.charts.PieChart;

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
    RadarChartView radar_chart;

    @AfterViews
    void init_radar(){
        // Prepare the data. We're going to show the top ten cheese producing U.S. states in 2013 (in 1,000 pounds)
        // IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP
        //TODO: connect to db and pull latest stats
        final Map<String, Float> axis = new LinkedHashMap<>(10);
        axis.put("IQ", 2312.895F);
        axis.put("CV", 871.640F);
        axis.put("ST", 751.280F);
        axis.put("EN", 661.293F);
        axis.put("CH", 661.293F);
        axis.put("LD", 426.985F);

        // Set your data to the view
        //final RadarChartView chartView = (RadarChartView) findViewById(R.id.radar_chart);
        radar_chart.setAxis( axis );

        radar_chart.setAxisMax( 2855.681F );         // set max value for the chart
        //chartView.addOrReplace("WI", 2855.681F); // add new axis
        //chartView.addOrReplace("OH", 281.59F);   // change the existing value
        radar_chart.setAutoSize( true );             // auto balance the chart
        radar_chart.setCirclesOnly( false );          // if you want circles instead of polygons
        radar_chart.setChartStyle( FILL );           // chart drawn with this style will be filled not stroked
        radar_chart.setSmoothGradient( true );
    }

    private void updateRadarChartView(Map<String, Float> axis){
        if(axis.size() != 6){
            System.err.println("Pass All 6 Profile Parameters i.e " +
                    "{IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP}");
        }

        // Set your data to the view
        final RadarChartView chartView = (RadarChartView) v.findViewById(R.id.radar_chart);
        chartView.setAxis(axis);

        chartView.setAxisMax(2855.681F);         // set max value for the chart
        //chartView.addOrReplace("OH", 281.59F);   // change the existing value
        chartView.setAutoSize(true);             // auto balance the chart
        chartView.setCirclesOnly(true);          // if you want circles instead of polygons
        chartView.setChartStyle(FILL);           // chart drawn with this style will be filled not stroked

        chartView.invalidate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.activity_profile_ui,container,false);

        return v;
    }
}