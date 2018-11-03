package charstars.uscfit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import charstars.uscfit.DataHandlers.UpdateWorkouts;

import static org.junit.Assert.*;

public class StepsTest {

    StepsDisplay sd;

    @Before
    public void setUp() throws Exception {
        sd = new StepsDisplay();
    }

    @Test
    public void addStepsGoalInvalidNum() {
        Assert.assertFalse(sd.addStepsGoal("step", "a", "walk"));
        Assert.assertFalse(sd.addStepsGoal("step", "!a", "walk"));
        Assert.assertFalse(sd.addStepsGoal("step", "122 00", "walk"));
        Assert.assertFalse(sd.addStepsGoal("step", "sfdkslABC", "walk"));
    }

    @Test
    public void addStepsGoalEmptyNum() {
        Assert.assertFalse(sd.addStepsGoal("step", null, "walk"));
        Assert.assertFalse(sd.addStepsGoal("step", "", "walk"));
    }

    @Test
    public void addStepsGoalNegativeNum() {
        Assert.assertFalse(sd.addStepsGoal("step", "-0", "walk"));
        Assert.assertFalse(sd.addStepsGoal("step", "-1", "walk"));
        Assert.assertFalse(sd.addStepsGoal("step", "-5000", "walk"));
    }

}