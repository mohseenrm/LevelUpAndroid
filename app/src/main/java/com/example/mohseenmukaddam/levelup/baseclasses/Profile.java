package com.example.mohseenmukaddam.levelup.baseclasses;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class Profile {
    public Player player;
    public Skillset skillset;
    public Update update;
    public List<Task> taskList;

    public Profile( Player player, Skillset skillset, Update update, List<Task> taskList ){
        this.player = player;
        this.skillset = skillset;
        this.update = update;
        this.taskList = taskList;
    }

    public Profile(){
        this.player = new Player( 1, 100, 0 );
        Log.d("momo", "player" + this.player);
        this.skillset = new Skillset( 0, 0 ,0 ,0, 0, 0 ,0);
        Log.d("momo", "skillset" + this.skillset);
        this.taskList = new ArrayList<Task>(){{
            add(new Task());
        }};
        Log.d("momo", "task list " + this.taskList);
        UpdateArgs temp = new UpdateArgs( 0, 0, 0, 0, "NORMAL" );
        this.update = new Update( temp );
        Log.d("momo", "update" + this.update);
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
