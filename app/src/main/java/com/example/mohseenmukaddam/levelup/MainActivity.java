package com.example.mohseenmukaddam.levelup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();


    @IgnoreExtraProperties
    public class User {

        public String username;
        public String email;
        public Profile profile;
        public Uri photoUrl;


        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email,Profile profile,Uri photoUrl) {
            this.username = username;
            this.email = email;
            this.profile = profile;
            this.photoUrl = photoUrl;
        }

    }

    private void writeNewUser(String userId, String name, String email, Profile profile,Uri photoUrl) {
        User user = new User(name, email,profile,photoUrl );
        //UpadteArgs -> 0 -> Update -> () -> profile
        mRootRef.child("users").child(userId).setValue(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting Default font
        CalligraphyConfig.initDefault( new CalligraphyConfig.Builder()
                .setDefaultFontPath( "fonts/Rixel.otf" )
                .setFontAttrId( R.attr.fontPath )
                .build()
        );

        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);

        Picasso.with(MainActivity.this).load(R.drawable.level_up_main).into(imageView);
        Button login = (Button) findViewById(R.id.login);
        Button signup = (Button) findViewById(R.id.signup);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser new_user = auth.getCurrentUser();
        if (auth.getCurrentUser() != null) {
            writeNewUser(new_user.getUid(),new_user.getDisplayName(),new_user.getEmail(),new Profile(),new_user.getPhotoUrl());
            Log.d("Santi","content"+ new_user.getUid());
            Toast.makeText(this,"Already signed in",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, TestLayout_.class));
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
//            public void onClick(View view) {
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                startActivity(new Intent(this, TestLayout_.class));
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
