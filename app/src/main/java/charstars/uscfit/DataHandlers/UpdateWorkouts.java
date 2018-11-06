package charstars.uscfit.DataHandlers;



import java.util.List;

import charstars.uscfit.DatabaseHandlers.GoalDatabaseManager;
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

    public static void changeWorkoutCompletionStatus(Workout workout) {
        Workout w = WorkoutDatabaseManager.getInstance().getWorkout(workout);
        if(w.isCompleted())
            w.setCompleted(false);
        else if(!w.isCompleted())
            w.setCompleted(true);
        WorkoutDatabaseManager.getInstance().updateWorkoutsDB();
    }

    //signal completion function
}


