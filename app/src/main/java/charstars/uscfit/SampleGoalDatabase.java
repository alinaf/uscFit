package charstars.uscfit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleGoalDatabase {

    public static List<Goal> defaultGoals = new ArrayList<Goal>(){{
        add(new MilesGoal("run 40 miles", 40, 0));
        add(new StepsGoal("walk 3000 steps", 3000, 200));
    }};
}
