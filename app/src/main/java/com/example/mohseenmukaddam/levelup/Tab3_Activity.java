package com.example.mohseenmukaddam.levelup;

/**
 * Created by Mohd on 11/12/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hp1 on 21-01-2015.
 */
@EFragment( R.layout.leaderboard )
public class Tab3_Activity extends Fragment {

    @ViewById
    Spinner spinner1;
    @ViewById
    Spinner spinner2;
    @ViewById
    TableLayout tbl;

    /*@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.user_creation,container,false);
        return v;
    }}*/

    @AfterViews
    void initUI(){

        // Populate Both the dropdown
        String[] items = new String[]{"Local", "Global"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner1.setAdapter(adapter);

        // IQ, CREATIVITY, STRENGTH, ENDURANCE, CHARISMA, LEADERSHIP,INVALID_DEFAULT
        String[] items1 = new String[]{"IQ", "Charisma", "Endurance", "Strength", "Creativity", "Leadership"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items1);
        spinner2.setAdapter(adapter1);

        // Populate Table based on database Records
        for(int i=1; i<50; i++) {
            // delcare a new row
            TableRow newRow = new TableRow(getContext());

            TextView tvRanking = new TextView(getContext());
            tvRanking.setText(""+i);
            TextView tvName = new TextView(getContext());
            tvName.setText("Player"+i);
            TextView tvScore = new TextView(getContext());
            tvScore.setText(""+i*5);


            // add views to the row
            newRow.addView(tvRanking);
            newRow.addView(tvName);
            newRow.addView(tvScore);

            // add the row to the table layout
            tbl.addView(newRow);
        }

    }

}