package com.example.mohseenmukaddam.levelup;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Mohd on 11/12/2016.
 * THIS ACTIVITY IS FOR THE TABBED
 */

public class Home_Activity extends AppCompatActivity {

    public Profile current_user;
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Profile","Tasks","Ranking","GUILD"};
    int Numboftabs = 4;
    private ShareActionProvider shareActionProvider;

    public void getProfileFromDB(){

        DatabaseReference mRef= Utils.getDatabase().getReference().child("/users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot msnapshot:dataSnapshot.getChildren()){
                    if(msnapshot.getKey().equals("profile")){
                        current_user = msnapshot.getValue(Profile.class);

                       // Toast.makeText(Home_Activity.this,"current_user_updated",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.current_user = (Profile)this.getIntent().getSerializableExtra( "profile" );
        getProfileFromDB();

        CalligraphyConfig.initDefault( new CalligraphyConfig.Builder()
                .setDefaultFontPath( "fonts/Rixel.otf" )
                .setFontAttrId( R.attr.fontPath )
                .build()
        );
        adapter =new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
           @Override
            public int getIndicatorColor(int position) {
               return getResources().getColor(R.color.lighter_blue);
               }
           });

       // Setting the ViewPager For the SlidingTabsLayout
       tabs.setViewPager(pager);

        // FAB SECTION
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.menu2) );

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView itemIcon = new ImageView(this);

        itemIcon.setImageDrawable( getResources().getDrawable(R.drawable.log_oout_3) );
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageDrawable( getResources().getDrawable(R.drawable.add)  );
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageDrawable( getResources().getDrawable(R.drawable.avatar1)  );
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        // FAB set size
        FloatingActionButton.LayoutParams params=new FloatingActionButton.LayoutParams(400,400);
        FloatingActionButton.LayoutParams subparams=new FloatingActionButton.LayoutParams(200,200);
        button1.setLayoutParams(subparams);
        button2.setLayoutParams(subparams);
        button3.setLayoutParams(subparams);
        //actionButton.setLayoutParams(params);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)


                .attachTo(actionButton)
                .build();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(Home_Activity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                Toast.makeText(Home_Activity.this,"signed out",Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(Home_Activity.this, MainActivity.class));
                                finish();
                            }
                        });;
                AuthUI.getInstance()
                        .delete(Home_Activity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Deletion succeeded
                                    Toast.makeText(Home_Activity.this,"account deleted",Toast.LENGTH_SHORT).show();
                                } else {
                                    // Deletion failed
                                    Toast.makeText(Home_Activity.this,"account not deleted out",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Activity.this, AddTask_.class));

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "here goes your content body";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Share subject");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        // FAB SECTION END



       }

    private void setShareIntent(Intent shareIntent){
        if(shareActionProvider != null){
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

    MenuItem item = menu.findItem(R.id.menu_item_share);

    shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        return true;
        }

@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
         }

        return super.onOptionsItemSelected(item);
        }
}








