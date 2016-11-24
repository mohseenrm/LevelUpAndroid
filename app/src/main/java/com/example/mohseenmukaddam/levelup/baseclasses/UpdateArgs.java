package com.example.mohseenmukaddam.levelup.baseclasses;

import java.io.Serializable;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class UpdateArgs implements Serializable {

    private double max, currentExp, addExp;
    private int level;
    private String rate;
    public UpdateArgs(){
        this.max  = this.currentExp = this.addExp = -1;
        this.level = 1;
        rate = "NORMAL";
    }
    public UpdateArgs( double max, int level, double currentExp, double addExp, String rate ){
        this.max = max;
        this.level = level;
        this.currentExp = currentExp;
        this.addExp = addExp;
        this.rate = rate;
    }

    public void setAddExp(double addExp) {
        this.addExp = addExp;
    }

    public double getAddExp() {
        return addExp;
    }

    public double getMax() {
        return max;
    }

    public double getCurrentExp() {
        return currentExp;
    }

    public int getLevel() {
        return level;
    }

    public String getRate() {
        return rate;
    }

    public void setCurrentExp(double currentExp) {
        this.currentExp = currentExp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}