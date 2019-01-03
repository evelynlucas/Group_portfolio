package org.pursuit.Group_portfolio_HW_TEAM_THE_TRIPLE_THREAT;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void evelynActivityExists() {
        assertNotNull(EvelynActivity.class);
    }

    @Test
    public void johnActivityExists() {
        assertNotNull(JohnActivity.class);
    }

    @Test
    public void veenActivityExists() {
        assertNotNull(VeenActivity.class);
    }

    @Test
    public void noRepeatActivities() {
        assertNotEquals(VeenActivity.class, JohnActivity.class);
    }

    @Test
    public void splashScreenExists() {
        assertNotNull(SplashScreen.class);
    }

}