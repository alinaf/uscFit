package charstars.uscfit;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoalTest {

    @Test
    public void setProgress() {
        Goal g = new MilesGoal("running", 40, 0);
        g.setProgress(10);
        Assert.assertEquals(10, g.getTrackingNum());
    }
    @Test
    public void getProgress() {
        Goal gg = new MilesGoal("running", 40, 0);
        gg.setProgress(10);
        Assert.assertEquals(0.25, gg.getProgress(), 0.1);
    }
}