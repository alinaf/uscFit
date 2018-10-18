package charstars.uscfit.DataHandlers;

import java.util.List;

import charstars.uscfit.Activity;
import charstars.uscfit.Goal;
import charstars.uscfit.SampleGoalDatabase;

public class GoalCalculations {

    public static boolean addGoal(Goal e, String email){
        SampleGoalDatabase.defaultGoals.add(e);
        return true;
    }
    public static boolean removeGoal(Goal e, String email){
        SampleGoalDatabase.defaultGoals.remove(e);
        return true;
    }

    //THIS CAN BE INVOKED BY OTHER CLASSES WHEN STEPS ARE COMPLETED OR AN ACTIVITY IS COMPLETED
    public static void calculateGoalProgress(Activity a, String email){
        //CLARIFY ON THIS


    }
    public static void alertOnCompletion(Goal e, String email){
    //notification that goal is completed
        // add badge somwhere
        //remove from list
        removeGoal(e, email);

    }
    public static List<Goal> getGoals(String email){
        return SampleGoalDatabase.defaultGoals;
    }

    public static void editGoal(Goal goal) {
        for(Goal g : SampleGoalDatabase.defaultGoals){
            if(g.id() == goal.id()){
                g.setDescription(goal.getDescription());
                g.setGoalNum(goal.getGoalNum());
                g.setTrackingNum(goal.getTrackingNum());
                return;
            }
        }
    }
}
