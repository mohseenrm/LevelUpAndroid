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

    /**
     * essential functions for updating profile
     */
    public double getBasePoints(){
        int level = this.args.level;
        if( level > 1 && level < 16 )
            return( 2700 + ( 10 * level ) );
            //16-45
        else if( level > 15 && level < 46 )
            return( 100000 + ( 25 * level ) );
            //46-85
        else if( level > 45 && level < 86 )
            return( 300000 + ( 55 * level ) );
            //85-100
        else if( level > 85 && level < 101 )
            return( 800000 + ( 75 * level ) );
        //should not get inside else
        else
            return( -1 );
    }

    public double getMaxPoints(){
        double basePoints = this.getBasePoints();
        Constants.LearningRate rate = this.args.rate;
        double factor = 1;
        if( basePoints == -1 ) {
            //TODO: log error
        }
        switch ( rate ){
            case FAST:
                factor = 0.8;
                break;
            case SLOW:
                factor = 1.2;
                break;
            default:
                factor = 1;
        }
        return ( factor * Math.pow( basePoints, 3 ) );
    }
}