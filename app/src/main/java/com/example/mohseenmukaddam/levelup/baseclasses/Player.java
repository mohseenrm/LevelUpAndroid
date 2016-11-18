package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/26/16.
 */

public class Player {
    public double health, exp;
    public int level;
    //1, 100, 0
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
    public int getLevel(){
        return this.level;
    }
    public void setLevel( int level ){
        this.level = level;
    }
    public double getHealth(){
        return this.health;
    }
    public void setHealth( double health ){
        this.health = health;
    }
    public double getExp(){
        return this.exp;
    }
    public void setExp( double exp ){
        this.exp = exp;
    }
}
