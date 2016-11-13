package com.example.mohseenmukaddam.levelup.baseclasses;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    public Profile(){
        this.player = new Player( 1, 100, 0 );
        this.skillset = new Skillset( 0, 0 ,0 ,0, 0, 0 ,0);
        this.taskList = new ArrayList<Task>(){{
            add(new Task());
        }};
        UpdateArgs temp = new UpdateArgs( 0, 0, 0, 0, Constants.LearningRate.NORMAL );
        this.update = new Update( temp );
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
