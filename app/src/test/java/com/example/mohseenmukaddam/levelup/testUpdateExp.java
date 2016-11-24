package com.example.mohseenmukaddam.levelup;

import com.example.mohseenmukaddam.levelup.baseclasses.Player;
import com.example.mohseenmukaddam.levelup.baseclasses.Profile;
import com.example.mohseenmukaddam.levelup.baseclasses.Skillset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by mohseenmukaddam on 11/23/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class testUpdateExp {

    Profile testProfile;

    void setTestCase(){
        this.testProfile = new Profile();
        //adding two skillsets to modify
        this.testProfile.getTaskList().get(0).getListOfSkills().add( "IQ" );

    }

    @Test
    public void testUpdate(){
        double exp = 170;
        this.setTestCase();
        //15mins
        this.testProfile.taskComplete( 900000, this.testProfile.getTaskList().get(0).getListOfSkills() );
        assertEquals( 0, this.testProfile.getPlayer().getLevel());
        assertEquals( 170, this.testProfile.getPlayer().getExp() , 0.01 );
        assertEquals( 1, this.testProfile.getSkillset().getIq(), 0.01);
        assertEquals( 1, this.testProfile.getSkillset().getCreativity(), 0.01);
    }
}
