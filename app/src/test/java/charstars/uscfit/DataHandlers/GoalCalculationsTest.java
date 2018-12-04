package charstars.uscfit.DataHandlers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import charstars.uscfit.Goal;

public class GoalCalculationsTest {
    GoalCalculations gc;
    Goal a = new MilesGoal("walking", 4, 0);
    Goal a2 = new MilesGoal("biking", 4, 0);
    Goal a3 = new MilesGoal("swimming", 4, 0);


    Goal g = new MilesGoal("running", 4, 0);
    Goal g2 = new MilesGoal("running2", 4, 0);
    Goal g3 = new MilesGoal("running3", 4, 0);

    @Before
    public void setUp() throws InterruptedException {
        gc = new GoalCalculations();
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void addGoal() {
//        gc.addGoal(g, "email");
//        Assert.assertEquals(gdb.getGoals().contains(g), true);
//        Assert.assertEquals(gdb.getGoals().contains(g3), false);
//
//    }
//
//    @Test
//    public void removeGoal() {
//        gc.addGoal(g2, "email");
//        boolean removed = gc.removeGoal(g2, "email");
//        Assert.assertEquals(removed, true);
//    }

//    @Test
//    public void calculateGoalProgress() {
//        Workout w = new Workout(new Activity("running", 500), Quantifier.MILES, 2, new GregorianCalendar());
//        gc.calculateGoalProgress(w, "email");
//        Assert.assertEquals(0, a.getTrackingNum());
//        Workout w2 = new Workout(new Activity("walking", 500), Quantifier.MILES, 2, new GregorianCalendar());
//        gc.calculateGoalProgress(w2, "email");
//        Assert.assertEquals(2, a.getTrackingNum());
//    }

    @Test
    public void resetWeek() {
        gc.resetWeek();
        Assert.assertEquals(0, gc.getGoalsThisWeek());
    }

//    @Test
//    public void getGoalsThisWeek() {
//        gc.resetWeek();
//        gc.alertOnCompletion(a, "email");
//        gc.alertOnCompletion(a2, "email");
//        Assert.assertEquals(2, gc.getGoalsThisWeek());
//    }
}