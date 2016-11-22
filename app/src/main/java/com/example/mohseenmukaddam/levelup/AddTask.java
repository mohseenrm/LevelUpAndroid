package com.example.mohseenmukaddam.levelup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohseenmukaddam.levelup.baseclasses.Update;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import java.util.List;


/**
 * Created by mohseenmukaddam on 10/27/16.
 */
@EActivity( R.layout.task_builder_2 )
public class AddTask extends AppCompatActivity {

    String currentUser;
    DatabaseReference ref;
    Query queryRef;
    @ViewById( R.id.mstb_multi_id_1 )
    MultiStateToggleButton skillButtons1;

    @ViewById( R.id.mstb_multi_id_2 )
    MultiStateToggleButton skillButtons2;

    @ViewById
    EditText task_name;

    @ViewById
    EditText task_description;

    //@BindView( R.id.mstb_multi_id_1 ) MultiStateToggleButton skillButtons1;
    //@BindView( R.id.mstb_multi_id_2 ) MultiStateToggleButton skillButtons2;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        Intent i = getIntent();
        currentUser  = (String)i.getSerializableExtra("uid");
        ref =  Utils.getDatabase().getReference("users").child(currentUser).child("profile").child("tasklist");
        queryRef = ref.orderByChild("username");

    }


    @AfterViews
    void setMultipleChoiceButtons() {
        //added android annotations
        skillButtons1.enableMultipleChoice(true);
        skillButtons2.enableMultipleChoice(true);
       // task_name.setText(currentUser);
//        ref.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                System.out.println(dataSnapshot.getValue());
//               // Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
//                    String name = Long.toString( (Long) dataSnapshot.getValue());
//                //String name1 = String.valueOf(value.get("level"));
//               // task_description.setText(name1);
//                task_description.setText(name);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//
//        });
    }



    @Click( R.id.button_confirm )
    void validate_and_confirm(){
        final String task_name_str = task_name.getText().toString();
        final String task_desc = task_description.getText().toString();
        if( !task_desc.isEmpty() && !task_name_str.isEmpty() ){
            //TODO: load profile page
            // TODO: add record to database
            //setContentView( R.layout.activity_profile_ui );
            boolean[] a;
            Update update;
            List<String> listOfSkills = null;
            a = skillButtons1.getStates();
            if (a[0] == true)
            {
                listOfSkills.add("Int");
            }

            currentUser = Boolean.toString(a[1]);
            task_name.setText(currentUser);




            startActivity(new Intent(AddTask.this, Home_Activity.class));
        }
        else {



            Toast.makeText(this, "Enter some valid name and description", Toast.LENGTH_SHORT).show();
        }
        }



}