package charstars.uscfit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleGoalDatabase {

    public static List<Goal> defaultGoals = new ArrayList<Goal>(){{
        add(new MilesGoal("run", 40, 0));
        add(new StepsGoal(3000, 200));
    }};
}
