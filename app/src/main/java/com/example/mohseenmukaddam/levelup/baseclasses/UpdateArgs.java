package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class UpdateArgs {

    public double max, currentExp, addExp;
    public int level;
    public String rate;
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
}