package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class UpdateArgs {

    float max, level, currentExp, addExp;
    Constants.LearningRate rate;
    public UpdateArgs(){
        this.max = this.level = this.currentExp = this.addExp = -1;
        rate = Constants.LearningRate.NORMAL;
    }
    public UpdateArgs( float max, float level, float currentExp, float addExp, Constants.LearningRate rate ){
        this.max = max;
        this.level = level;
        this.currentExp = currentExp;
        this.addExp = addExp;
        this.rate = rate;
    }
}