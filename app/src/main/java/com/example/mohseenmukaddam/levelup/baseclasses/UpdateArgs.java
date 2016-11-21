package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class UpdateArgs {

    private double max, currentExp, addExp;
    private int level;
    private String rate;
    public UpdateArgs(){
        this.max  = this.currentExp = this.addExp = -1;
        this.level = -1;
        rate = "NORMAL";
    }
    public UpdateArgs( double max, int level, double currentExp, double addExp, String rate ){
        this.max = max;
        this.level = level;
        this.currentExp = currentExp;
        this.addExp = addExp;
        this.rate = rate;
    }

    public void addExp(double addExp) {
        this.addExp = addExp;
    }

    public double addExp() {
        return addExp;
    }

    public double max() {
        return max;
    }

    public double currentExp() {
        return currentExp;
    }

    public int level() {
        return level;
    }

    public String rate() {
        return rate;
    }

    public void currentExp(double currentExp) {
        this.currentExp = currentExp;
    }

    public void level(int level) {
        this.level = level;
    }

    public void max(double max) {
        this.max = max;
    }

    public void rate(String rate) {
        this.rate = rate;
    }
}