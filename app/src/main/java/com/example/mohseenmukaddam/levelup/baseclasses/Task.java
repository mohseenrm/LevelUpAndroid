package com.example.mohseenmukaddam.levelup.baseclasses;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class Task implements Serializable{
    private String name, description;
    private Update update;
    private List<String> listOfSkills;

    public Task( String name, String description, Update update, List<String> listOfSkills ){
        this.name = name;
        this.description = description;
        this.update = update;
        this.listOfSkills = listOfSkills;
    }
    public Task( String name, String description ){
        this.name = name;
        this.description = description;
        this.update = new Update(new UpdateArgs(0, 0, 0, 0, "NORMAL" ) );

        this.listOfSkills = new ArrayList<String>();
    }


    public Task(){
        this.name = "Workout";
        this.description = "Gym";
        //TODO
        this.update = new Update(new UpdateArgs(0, 0, 0, 0, "NORMAL" ) );

        this.listOfSkills = new ArrayList<String>();

//        this.listOfSkills = new ArrayList<String>(1);
        //this.listOfSkills.add( "STRENGTH" );

        Log.d("Santi", "skillset list " + this.listOfSkills);
    }
//TODO: task run()
//TODO: task pause()
//TODO: task stop()
    public String getName(){
        return this.name;
    }
    public void setName( String name ) {
        this.name = name;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription( String description ) {
        this.description = description;
    }
    public Update getUpdate(){
        return this.update;
    }
    public void setUpdate( Update update ){
        this.update = update;
    }
    public List<String> getListOfSkills(){
        return this.listOfSkills;
    }
    public void setListOfSkills( List<String> listOfSkills ){
        this.listOfSkills = listOfSkills;
    }
}
