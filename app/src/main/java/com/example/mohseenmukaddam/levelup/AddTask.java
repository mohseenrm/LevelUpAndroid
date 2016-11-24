package com.example.mohseenmukaddam.levelup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.example.mohseenmukaddam.levelup.baseclasses.Task;
import com.example.mohseenmukaddam.levelup.baseclasses.Update;
import com.example.mohseenmukaddam.levelup.baseclasses.UpdateArgs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by mohseenmukaddam on 10/27/16.
 */
@EActivity( R.layout.task_builder_2 )
public class AddTask extends AppCompatActivity {

    String currentUser;

    int key = 0;
    String latestKey;
    Query queryRef;
    DatabaseReference mRef= Utils.getDatabase().getReference().child("/users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile").child("taskList");
    HashMap<String, Object> postValues = new HashMap<>();
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

        //Intent i = getIntent();
        //currentUser  = (String)i.getSerializableExtra("uid");
        //ref =  Utils.getDatabase().getReference("users").child(currentUser).child("profile").child("tasklist");

        //queryRef = ref.orderByChild("username");



        latestKey = latestKey;



        //task_description.setText(count);

        //Toast.makeText(this, "Count is "+count, Toast.LENGTH_SHORT).show();
    }


    @AfterViews
    void setMultipleChoiceButtons() {
        //added android annotations
        skillButtons1.enableMultipleChoice(true);
        skillButtons2.enableMultipleChoice(true);
        queryRef = mRef.orderByKey().limitToLast(1);
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                    latestKey = childSnapshot.getKey().toString();
                    // Toast.makeText(AddTask.this, "Count is "+latestKey, Toast.LENGTH_SHORT).show();
                    if(!latestKey.isEmpty() && latestKey != null) {
                        key = Integer.parseInt(latestKey)+1;
                        Toast.makeText(AddTask.this, "Count is " + latestKey + " " + key, Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //key = (latestKey);




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
            Update update = new Update(new UpdateArgs(0, 0, 0, 0, "NORMAL" ) );
            List<String> listOfSkills =  new ArrayList<String>();
            a = skillButtons1.getStates();
            if (a[0] == true)
            {
                listOfSkills.add("IQ");
            }
            if (a[1] == true)
            {
                listOfSkills.add("CREATIVITY");
            }
            if (a[2] == true)
            {
                listOfSkills.add("STRENGTH");
            }
            a = skillButtons2.getStates();
            if (a[0] == true)
            {
                listOfSkills.add("ENDURANCE");
            }
            if (a[1] == true)
            {
                listOfSkills.add("CHARISMA");
            }
            if (a[2] == true)
            {
                listOfSkills.add("LEADERSHIP");
            }
            if (listOfSkills.isEmpty())
            {
                Toast.makeText(this, "Skills not selected", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Task newTask = new Task(task_name_str, task_desc, update, listOfSkills);
                //writeNewUser(newTask);
                postValues.put("name", task_name_str);
                postValues.put("description", task_desc);
                postValues.put("update", update);
                postValues.put("listOfSkills", listOfSkills);
                String keystring = Integer.toString(key);
                mRef.child(keystring).setValue(postValues);
                startActivity(new Intent(AddTask.this, Home_Activity.class));
            }
        }
        else {



            Toast.makeText(this, "Enter some valid name and description", Toast.LENGTH_SHORT).show();
        }
        }

//
//    private void writeNewUser(final Task task) {
//        //DatabaseReference mDatabase = Utils.getDatabase().getReference().child("users").child();
//        //MainActivity.User user = new MainActivity.User(name, emailId,profile);
//        //Map<String, Object> postValues = user.toMap();
//        //https://levelupandroid-8541e.firebaseio.com/
//
//
//
//        //TODO: look into this bugger!
//
//    }


}