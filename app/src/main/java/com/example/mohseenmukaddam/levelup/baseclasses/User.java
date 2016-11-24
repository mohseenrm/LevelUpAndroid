package com.example.mohseenmukaddam.levelup.baseclasses;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohd on 11/24/2016.
 */

public class User implements Serializable {
    private String emailid, username;
    private Profile profile;


    public User( String emailid,Profile profile,String username ){
        this.emailid =  emailid;
        this.profile = profile;
        this.username = username;

    }
    public User() {
        this.emailid = "email@email.com";
        this.username = "Default";
        //TODO
        this.profile = new Profile();
    }


    public String getEmailid(){
        return this.emailid;
    }

    public String getUsername(){
        return this.username;
    }
    public Profile getprofile(){
        return this.profile;
    }
  }
