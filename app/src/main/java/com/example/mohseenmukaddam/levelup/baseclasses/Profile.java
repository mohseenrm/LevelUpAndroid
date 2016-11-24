package com.example.mohseenmukaddam.levelup.baseclasses;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class Profile implements Serializable{
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

    public void taskComplete(long time, List<String> skills ){
        this.syncPlayer();

        int previousLevel = this.player.getLevel();
        this.update.getArgs().setAddExp( this.update.calculateExp( time ) );
        //state is now set
        this.update.setArgs( this.update.levelUp() );
        //setting current exp to add exp
        if(this.update.getArgs().getAddExp() != 0)
            this.update.getArgs().setCurrentExp(this.update.getArgs().getAddExp());
        this.update.getArgs().setAddExp(0);
        //this.update()
        //check if level update if true, call update.skilsetupgrade

        this.syncPlayer();

//        if( this.player.getLevel() != previousLevel )
//            this.update.skillsetUpgrade( this.skillset, skills );

        this.update.skillsetUpgrade( this.skillset, skills );
        //reset addExp after processing
        this.update.getArgs().setAddExp( 0 );
    }

    public Profile(){
        this.player = new Player( 1, 100, 0 );
        this.skillset = new Skillset( 0, 0 ,0 ,0, 0, 0 ,0);

        this.taskList = new ArrayList<Task>(){{
            add(new Task());
        }};

        UpdateArgs temp = new UpdateArgs( 0, 1, 0, 0, "NORMAL" );

        this.update = new Update( temp );
        //setting maxpoints for given level
        this.getUpdate().getArgs().setMax(this.getUpdate().getMaxPoints());
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