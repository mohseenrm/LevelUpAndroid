package com.example.mohseenmukaddam.levelup.baseclasses;

import java.io.Serializable;

/**
 * Created by mohseenmukaddam on 10/26/16.
 */

public class Skillset implements Serializable {
    private double iq, creativity, strength, charisma, endurance, leadership,invalid_default;

    public Skillset(){
        this.iq = this.creativity = this.strength = this.charisma = this.endurance = this.leadership = this.invalid_default = -1;
    }

    public Skillset( double iq, double creativity, double strength, double charisma, double endurance, double leadership ,double invalid_default){
        this.iq = iq;
        this.creativity = creativity;
        this.strength = strength;
        this.charisma = charisma;
        this.endurance = endurance;
        this.leadership = leadership;
        this.invalid_default = invalid_default;
    }



    /**
     * getters and setters for members
     */
    public double getIq(){
        return this.iq;
    }
    public void setIq( double iq ){
        this.iq = iq;
    }
    public double getCreativity(){
        return this.creativity;
    }
    public void setCreativity( double creativity ){
        this.creativity= creativity;
    }
    public double getStrength(){
        return this.strength;
    }
    public void setStrength( double strength ){
        this.strength = strength;
    }
    public double getCharisma(){
        return this.charisma;
    }
    public void setCharisma( double charisma ){
        this.charisma = charisma;
    }
    public double getEndurance(){
        return this.endurance;
    }
    public void setEndurance( double endurance ){
        this.endurance = endurance;
    }
    public double getLeadership(){
        return this.leadership;
    }
    public void setLeadership( double leadership ){
        this.leadership = leadership;
    }
    public double getInvalid_default(){
        return this.invalid_default;
    }
    public void setInvalid_default( double invalid_default ){
        this.invalid_default = invalid_default;
    }
}