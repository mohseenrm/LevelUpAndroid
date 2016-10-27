package com.example.mohseenmukaddam.levelup.baseclasses;

import java.util.List;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class Profile {
    Player player;
    Skillset skillset;
    Update update;
    List<Task> taskList;

    public Profile( Player player, Skillset skillset, Update update, List<Task> taskList ){
        this.player = player;
        this.skillset = skillset;
        this.update = update;
        this.taskList = taskList;
    }

    public Player player(){
        return this.player;
    }
    public void player( Player player ){
        this.player = player;
    }
    public Skillset skillset(){
        return this.skillset;
    }
    public void skillset( Skillset skillset ){
        this.skillset = skillset;
    }
    public Update update(){
        return this.update;
    }
    public void update( Update update ){
        this.update = update;
    }
    public List<Task> taskList(){
        return this.taskList;
    }
    public void taskList( List<Task> taskList ){
        this.taskList = taskList;
    }
}
