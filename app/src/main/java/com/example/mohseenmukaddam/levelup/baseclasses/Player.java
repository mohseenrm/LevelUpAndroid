package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/26/16.
 */

public class Player {
    private double health, exp;
    private int level;

    //for undefined objects
    public Player(){
        this.health = this.exp = this.level = -1;
    }
    public Player( int level, double health, double exp ){
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
    public double health(){
        return this.health;
    }
    public void health( double health ){
        this.health = health;
    }
    public double exp(){
        return this.exp;
    }
    public void exp( double exp ){
        this.exp = exp;
    }
}
