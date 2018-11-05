package charstars.uscfit.DataHandlers;



import java.util.List;

import charstars.uscfit.DatabaseHandlers.WorkoutDatabaseManager;
import charstars.uscfit.RootObjects.Workout;

public class UpdateWorkouts {

    public static boolean addWorkout(Workout w) {
        WorkoutDatabaseManager.getInstance().addWorkout(w);
        return true;
    }
    public static boolean removeWorkout(Workout w){
        WorkoutDatabaseManager.getInstance().removeWorkout(w);
        return true;
    }

    public static List<Workout> getWorkouts(){
        return WorkoutDatabaseManager.getInstance().getWorkouts();
    }

    /*
    public static void editGoal(Goal copy, Goal orig) {
        Goal g = GoalDatabaseManager.getInstance().getGoal(orig);
        Log.d("GOAL ID", g.id()+" "+copy.id());

        g.setDescription(copy.getDescription());
        g.setGoalNum(copy.getGoalNum());
        g.setTrackingNum(copy.getTrackingNum());


        for(Goal ggg: GoalDatabaseManager.getInstance().getGoals()){
            Log.d("goal", ggg.getDescription());
        }
        GoalDatabaseManager.getInstance().updateGoalsDB();
    }*/

    //signal completion function
}


