package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/26/16.
 */

public class Skillset {
    private double iq, creativity, strength, charisma, endurance, leadership;

    public Skillset(){
        this.iq = this.creativity = this.strength = this.charisma = this.endurance = this.leadership = -1;
    }

    public Skillset( double iq, double creativity, double strength, double charisma, double endurance, double leadership ){
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
    public double iq(){
        return this.iq;
    }
    public void iq( double iq ){
        this.iq = iq;
    }
    public double creativity(){
        return this.creativity;
    }
    public void creativity( double creativity ){
        this.creativity= creativity;
    }
    public double strength(){
        return this.strength;
    }
    public void strength( double strength ){
        this.strength = strength;
    }
    public double charisma(){
        return this.charisma;
    }
    public void charisma( double charisma ){
        this.charisma = charisma;
    }
    public double endurance(){
        return this.endurance;
    }
    public void endurance( double endurance ){
        this.endurance = endurance;
    }
    public double leadership(){
        return this.leadership;
    }
    public void leadership( double leadership ){
        this.leadership = leadership;
    }
}