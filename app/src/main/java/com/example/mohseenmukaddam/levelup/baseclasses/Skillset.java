package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/26/16.
 */

public class Skillset {
    private float iq, creativity, strength, charisma, endurance, leadership;

    public Skillset(){
        this.iq = this.creativity = this.strength = this.charisma = this.endurance = this.leadership = -1;
    }

    public Skillset( float iq, float creativity, float strength, float charisma, float endurance, float leadership ){
        this.iq = iq;
        this.creativity = creativity;
        this.strength = strength;
        this.charisma = charisma;
        this.endurance = endurance;
        this.leadership = leadership;
    }
    /**
     * getters and setters for members
     */
    public float iq(){
        return this.iq;
    }
    public void iq( float iq ){
        this.iq = iq;
    }
    public float creativity(){
        return this.creativity;
    }
    public void creativity( float creativity ){
        this.creativity= creativity;
    }
    public float strength(){
        return this.strength;
    }
    public void strength( float strength ){
        this.strength = strength;
    }
    public float charisma(){
        return this.charisma;
    }
    public void charisma( float charisma ){
        this.charisma = charisma;
    }
    public float endurance(){
        return this.endurance;
    }
    public void endurance( float endurance ){
        this.endurance = endurance;
    }
    public float leadership(){
        return this.leadership;
    }
    public void leadership( float leadership ){
        this.leadership = leadership;
    }
}