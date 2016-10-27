package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/26/16.
 */

public class Player {
    private float health, exp;
    private int level;

    //for undefined objects
    public Player(){
        this.health = this.exp = this.level = -1;
    }
    public Player( int level, float health, float exp ){
        this.level = level;
        this.exp = exp;
        this.health = health;
    }

    /**
     * getters and setters for members
     */
    public int level(){
        return this.level;
    }
    public void level( int level ){
        this.level = level;
    }
    public float health(){
        return this.health;
    }
    public void health( float health ){
        this.health = health;
    }
    public float exp(){
        return this.exp;
    }
    public void exp( float exp ){
        this.exp = exp;
    }
}
