package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class Task {
    private String name, description;
    private Update update;
    private Skillset skillset;

    public Task( String name, String description, Update update, Skillset skillset ){
        this.name = name;
        this.description = description;
        this.update = update;
        this.skillset = skillset;
    }

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
    public Skillset skillset(){
        return this.skillset;
    }
    public void skillset( Skillset skillset ){
        this.skillset = skillset;
    }
}
