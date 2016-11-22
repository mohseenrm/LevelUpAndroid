package com.example.mohseenmukaddam.levelup.baseclasses;

/**
 * Created by Mohd on 11/21/2016.
 */

public class TaskTest
    {
        private String name;
        private String description;
        private String update;
        private String  listOfSkills;

        public TaskTest() {
    }

        public TaskTest(String name, String description,String update, String listOfSkills) {
        this.name = name;
        this.description = description;
        this.update = update;
        this.listOfSkills = listOfSkills;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



}
