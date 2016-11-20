package com.example.mohseenmukaddam.levelup;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import com.github.mikephil.charting.charts.PieChart;
//
//
//import org.androidannotations.annotations.AfterViews;
//import org.androidannotations.annotations.EActivity;
//import org.androidannotations.annotations.ViewById;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import static android.graphics.Paint.Style.FILL;
//
//
///*
// *
// */
//@EActivity( R.layout.activity_profile_ui )
//public class ProfileUIActivity extends AppCompatActivity {
//
//    private RelativeLayout profileInfo;
//    private ImageView profilePic;
//    private PieChart mChart;
//
//    @ViewById
//    RadarChartView2 radar_chart;
//
//    @AfterViews
//    void init_radar(){
//        // Prepare the data. We're going to show the top ten cheese producing U.S. states in 2013 (in 1,000 pounds)
//        // IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP
//        //TODO: connect to db and pull latest stats
//        final Map<String, Float> axis = new LinkedHashMap<>(10);
//        axis.put("IQ", 2312.895F);
//        axis.put("CV", 871.640F);
//        axis.put("ST", 751.280F);
//        axis.put("EN", 661.293F);
//        axis.put("CH", 661.293F);
//        axis.put("LD", 426.985F);
//
//        // Set your data to the view
//        //final RadarChartView chartView = (RadarChartView) findViewById(R.id.radar_chart);
//        radar_chart.setAxis( axis );
//
//        radar_chart.setAxisMax( 2855.681F );         // set max value for the chart
//        //chartView.addOrReplace("WI", 2855.681F); // add new axis
//        //chartView.addOrReplace("OH", 281.59F);   // change the existing value
//        radar_chart.setAutoSize( true );             // auto balance the chart
//        radar_chart.setCirclesOnly( false );          // if you want circles instead of polygons
//        radar_chart.setChartStyle( FILL );           // chart drawn with this style will be filled not stroked
//        radar_chart.setSmoothGradient( true );
//    }
//
//    private void updateRadarChartView(Map<String, Float> axis){
//        if(axis.size() != 6){
//            System.err.println("Pass All 6 Profile Parameters i.e " +
//                    "{IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP}");
//        }
//
//        // Set your data to the view
//        final RadarChartView chartView = (RadarChartView) findViewById(R.id.radar_chart);
//        chartView.setAxis(axis);
//
//        chartView.setAxisMax(2855.681F);         // set max value for the chart
//        //chartView.addOrReplace("OH", 281.59F);   // change the existing value
//        chartView.setAutoSize(true);             // auto balance the chart
//        chartView.setCirclesOnly(true);          // if you want circles instead of polygons
//        chartView.setChartStyle(FILL);           // chart drawn with this style will be filled not stroked
//
//        chartView.invalidate();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile_ui);
//
//        //initRadarChartView();
//        // use updateRadarChart to update the Value in Real time for Chart
//    }
//}
