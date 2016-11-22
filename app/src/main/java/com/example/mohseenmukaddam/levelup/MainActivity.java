package com.example.mohseenmukaddam.levelup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mohseenmukaddam.levelup.baseclasses.Player;
import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.example.mohseenmukaddam.levelup.baseclasses.Skillset;
import com.example.mohseenmukaddam.levelup.baseclasses.Task;
import com.example.mohseenmukaddam.levelup.baseclasses.Update;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser new_user = auth.getCurrentUser();

    public String currentUsername ;
    public String currentUserId ;
    public String currentEmailId;
    public Profile currentProfile;
    @IgnoreExtraProperties
    public class User {

        private String username;
        private String emailId;
        private Profile profile;
        //public Uri photoUrl;


        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)

        }

        public User(String username, String emailId,Profile profile) {
            this.username = username;
            this.emailId = emailId;
            this.profile = profile;
            //this.photoUrl = photoUrl;
        }

        public String getUsername(){
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmailId(){
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public Profile getProfile(){
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }
        //        public Uri getPhotoUrl(){
//            return photoUrl;
//        }
        public Map<String, Object> toMap(){
            HashMap<String, Object> result = new HashMap<>();
            result.put("username",username);
            result.put("emailId",emailId);
            result.put("profile",profile);

            return result;
        }
    }


    private void writeNewUser(final String userId, String name, String emailId, Profile profile) {
        DatabaseReference mDatabase = Utils.getDatabase().getReference().child("users");
        User user = new User(name, emailId,profile);
        Map<String, Object> postValues = user.toMap();
        //https://levelupandroid-8541e.firebaseio.com/
        if(currentProfile!=null) {
            mDatabase.child(userId).setValue(postValues);
        }
        //TODO: look into this bugger!

    }

    private void listernerForChild(){
        DatabaseReference mDatabase = Utils.getDatabase().getReference().child("users");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot msnapshot:dataSnapshot.getChildren()){

                    if(msnapshot.getKey()=="emailId"){
                        String email = msnapshot.getValue(String.class);
                        currentEmailId = email;
                        Log.v("santiDB","emailId is"+email);
                    }
                    else if(msnapshot.getKey()=="username"){
                        String username = msnapshot.getValue(String.class);
                        currentUsername = username;
                        Log.v("SantiDB","username is"+username);
                    }
                    else if(msnapshot.getKey()=="profile"){
                        Profile newAddedProfile = msnapshot.getValue(Profile.class);
                        currentProfile = newAddedProfile;
                        Log.v("santiDB","profile"+newAddedProfile.toString());
                        Toast.makeText(MainActivity.this,currentProfile.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
                //Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();


                //Log.v("santiDB",value.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

//                Map<String, Object> changedvalue = (Map<String, Object>) dataSnapshot.getValue();
//                for (Object eachValue:changedvalue.values()
//                     ) {
//                    Log.v("santiDB",eachValue.toString());
//                }
                for (DataSnapshot msnapshot:dataSnapshot.getChildren()){

                    if(msnapshot.getKey()=="emailId"){
                        String email = msnapshot.getValue(String.class);
                        currentEmailId = email;
                        Log.v("santiDB","emailId is"+email);
                    }
                    else if(msnapshot.getKey()=="username"){
                        String username = msnapshot.getValue(String.class);
                        currentUsername = username;
                        Log.v("SantiDB","username is"+username);
                    }
                    else if(msnapshot.getKey()=="profile"){
                        Profile newAddedProfile = msnapshot.getValue(Profile.class);
                        currentProfile = newAddedProfile;
                        Log.v("santiDB","profile"+newAddedProfile.toString());
                        Toast.makeText(MainActivity.this,currentProfile.toString(),Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Profile getProfileFromDB(){
        DatabaseReference mRef= Utils.getDatabase().getReference().child("/users/"+currentUserId);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot msnapshot:dataSnapshot.getChildren()){
                         if(msnapshot.getKey().equals("profile")){
                             currentProfile = msnapshot.getValue(Profile.class);
                             Log.v("santiDB","profile"+currentProfile.toString());
                             Toast.makeText(MainActivity.this,currentProfile.toString(),Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent( getBaseContext(), Home_Activity.class );
                             intent.putExtra( "profile", currentProfile );
//                startActivity(new Intent(this, Home_Activity.class));
                             startActivity( intent );
                         }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot msnapshot:dataSnapshot.getChildren()){
//                         if(msnapshot.getKey().equals("profile")){
//                             currentProfile = msnapshot.getValue(Profile.class);
//                             Log.v("santiDB","profile"+currentProfile.toString());
//                             Toast.makeText(MainActivity.this,currentProfile.toString(),Toast.LENGTH_SHORT).show();
//                             Intent intent = new Intent( getBaseContext(), Home_Activity.class );
//                             intent.putExtra( "profile", currentProfile );
////                startActivity(new Intent(this, Home_Activity.class));
//                             startActivity( intent );
//                         }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        return currentProfile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        DatabaseReference mDatabase = Utils.getDatabase().getReference().child("users");
        mDatabase.keepSynced(true);
        //setting Default font
        CalligraphyConfig.initDefault( new CalligraphyConfig.Builder()
                .setDefaultFontPath( "fonts/Rixel.otf" )
                .setFontAttrId( R.attr.fontPath )
                .build()
        );

        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);

        Picasso.with(MainActivity.this).load(R.drawable.level_up_main).into(imageView);
        //Button login = (Button) findViewById(R.id.login);
        //Button signup = (Button) findViewById(R.id.signup);


        if (auth.getCurrentUser() != null) {
            //TODO: Santi this line causes the issue
            new_user = auth.getCurrentUser();
            currentUsername = new_user.getDisplayName();
            currentEmailId = new_user.getEmail();
            currentUserId = new_user.getUid();
            currentProfile = getProfileFromDB();
            writeNewUser(currentUserId,currentUsername,currentEmailId,currentProfile);
            //Log.d("Santi","content"+ new_user.getUid());
            Toast.makeText(this,"Already signed in",Toast.LENGTH_SHORT).show();

//            startActivity(new Intent(this, Home_Activity.class));
            finish();
        } else {
            Toast.makeText(this,"Not signed in",Toast.LENGTH_SHORT).show();
            // not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setTheme(R.style.GreenTheme)
                            .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);
        }




//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
// {
//                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
//                startActivity(intent);
//            }
//        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        TextView levelUpTitle = (TextView) findViewById(R.id.level_up_title);
//        Typeface customType = Typeface.createFromAsset(getAssets(), "fonts/MutantAcademyBB.ttf");
//        levelUpTitle.setTypeface(customType);
    }

    public Profile getProfileOfUser(){
        return currentProfile;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser()!=null ){
                            new_user = firebaseAuth.getCurrentUser();
                            currentProfile = new Profile();
                            currentUsername = new_user.getDisplayName();
                            currentEmailId = new_user.getEmail();
                            currentUserId = new_user.getUid();
                            currentProfile = getProfileFromDB();
                            listernerForChild();
                            writeNewUser(currentUserId, currentUsername, currentEmailId, currentProfile);
                        }
                        else{
                            //Not yet initialized
                        }
                    }
                });
                Intent intent = new Intent( this, Home_Activity.class );
                intent.putExtra( "profile", this.getProfileOfUser() );
//                startActivity(new Intent(this, Home_Activity.class));
                startActivity( intent );
                finish();
            } else {
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
            }
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }






}
