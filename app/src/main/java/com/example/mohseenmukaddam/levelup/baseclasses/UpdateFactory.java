package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by mohseenmukaddam on 10/27/16.
 */

public class UpdateFactory {
    public static Update generate( UpdateArgs args ){
        Update returnObj;
        switch ( args.rate ){
            case FAST: returnObj = new FastUpdate( args );
                break;
            case SLOW: returnObj = new SlowUpdate( args );
                break;
            default: returnObj = new Update( args );
        }
        return returnObj;
    }
}
