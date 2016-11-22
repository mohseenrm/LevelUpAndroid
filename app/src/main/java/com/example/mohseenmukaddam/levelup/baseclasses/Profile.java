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
    private Player player;
    private Skillset skillset;
    private Update update;
    private List<Task> taskList;

    public Profile( Player player, Skillset skillset, Update update, List<Task> taskList ){
        this.player = player;
        this.skillset = skillset;
        this.update = update;
        this.taskList = taskList;
    }

    private void syncPlayer(){
        UpdateArgs tempArgs = this.update.getArgs();
        if( this.player.getLevel() != tempArgs.getLevel() )
            this.player.setLevel( tempArgs.getLevel() );
        if( this.player.getExp() != tempArgs.getCurrentExp() )
            this.player.setExp( tempArgs.getCurrentExp() );
    }

    private void taskComplete( double time, List<String> skills ){
        this.syncPlayer();

        int previousLevel = this.player.getLevel();
        this.update.getArgs().setAddExp( this.update.calculateExp( time ) );
        //state is now set
        this.update.setArgs( this.update.levelUp() );
        //this.update()
        //check if level update if true, call update.skilsetupgrade

        this.syncPlayer();

        if( this.player.getLevel() != previousLevel )
            this.update.skillsetUpgrade( this.skillset, skills );
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

    public Player getPlayer(){
        return this.player;
    }
    public void setPlayer( Player player ){
        this.player = player;
    }
    public Skillset getSkillset(){
        return this.skillset;
    }
    public void setSkillset( Skillset skillset ){
        this.skillset = skillset;
    }
    public Update getUpdate(){
        return this.update;
    }
    public void setUpdate( Update update ){
        this.update = update;
    }
    public List<Task> getTaskList(){
        return this.taskList;
    }
    public void setTaskList( List<Task> taskList ){
        this.taskList = taskList;
    }
}