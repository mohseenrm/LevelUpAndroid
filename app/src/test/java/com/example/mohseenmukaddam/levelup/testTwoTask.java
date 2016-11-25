package com.example.mohseenmukaddam.levelup;

import com.example.mohseenmukaddam.levelup.baseclasses.Profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by mohseenmukaddam on 11/24/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class testTwoTask {
    Profile testProfile;

    void setTestCase(){
        this.testProfile = new Profile();
        //adding two skillsets to modify
        this.testProfile.getTaskList().get(0).getListOfSkills().add( "IQ" );

        //this.testProfile.getPlayer().setExp(19902510831.0);
        //this.testProfile.getUpdate().getArgs().setCurrentExp(19902510831.0);

    }

    @Test
    public void testUpdate(){
        double exp = 170;
        this.setTestCase();
        //15mins
        this.testProfile.taskComplete( 600000, this.testProfile.getTaskList().get(0).getListOfSkills() );
        this.testProfile.taskComplete( 900000, this.testProfile.getTaskList().get(0).getListOfSkills() );


        assertEquals( 1, this.testProfile.getPlayer().getLevel());
        assertEquals( 304.92908941729695, this.testProfile.getPlayer().getExp() , 0.01 );
//        assertEquals( 1, this.testProfile.getSkillset().getIq(), 0.01);
        assertEquals( 0, this.testProfile.getSkillset().getStrength(), 0.01);
    }
}
