package com.example.mohseenmukaddam.levelup.baseclasses;

import java.util.List;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class Task {
    private String name, description;
    private Update update;
    private List<Constants.Skillsets> listOfSkills;

    public Task( String name, String description, Update update, List<Constants.Skillsets> listOfSkills ){
        this.name = name;
        this.description = description;
        this.update = update;
        this.listOfSkills = listOfSkills;
    }
//TODO: task run()
//TODO: task pause()
//TODO: task stop()
    public String name(){
        return this.name;
    }
    public void name( String name ) {
        this.name = name;
    }
    public String description(){
        return this.description;
    }
    public void description( String description ) {
        this.description = description;
    }
    public Update update(){
        return this.update;
    }
    public void update( Update update ){
        this.update = update;
    }
    public List<Constants.Skillsets> listOfSkills(){
        return this.listOfSkills;
    }
    public void listOfSkills( List<Constants.Skillsets> listOfSkills ){
        this.listOfSkills = listOfSkills;
    }
}
