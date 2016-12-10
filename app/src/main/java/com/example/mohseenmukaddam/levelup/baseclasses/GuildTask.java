package com.example.mohseenmukaddam.levelup.baseclasses;

import java.io.Serializable;

/**
 * Created by Mohd on 11/21/2016.
 */

public class GuildTask implements Serializable
    {
        private String name;
        private String description;


        public GuildTask() {
    }

        public GuildTask(String name, String description) {
        this.name = name;
        this.description = description;


    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



}
