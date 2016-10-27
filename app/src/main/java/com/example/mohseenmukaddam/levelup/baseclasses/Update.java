package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class Update implements UpdateModules{
    UpdateArgs args;

    public Update( UpdateArgs args ){
        this.args = args;
    }
    public void args( UpdateArgs args ){
        this.args = args;
    }
    public UpdateArgs args(){
        return this.args;
    }
    public UpdateArgs levelUp( UpdateArgs args ){
        //TODO: needs to be implemented
        //process data
        return args;
    }
    public Skillset skillsetUpgrade( Skillset args ){
        //TODO: needs to be implemented
        //process data
        return args;
    }
}
