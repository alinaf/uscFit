package charstars.uscfit.DataHandlers;



import java.util.List;

import charstars.uscfit.DatabaseHandlers.GoalDatabaseManager;
import charstars.uscfit.DatabaseHandlers.WorkoutDatabaseManager;
import charstars.uscfit.DayAtAGlance;
import charstars.uscfit.RootObjects.Quantifier;
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
        DayAtAGlance dayAtAGlance = new DayAtAGlance();
        Workout w = WorkoutDatabaseManager.getInstance().getWorkout(workout);
        if(w.isCompleted())   {
            w.setCompleted(false);
            dayAtAGlance.updateDailyCalories(w.getCaloriesBurned()*-1);
            if (w.getQuant() == Quantifier.MINUTES) {
                dayAtAGlance.updateDailyMinutes(w.getLength()*-1);
            }
        } else if(!w.isCompleted()) {
            w.setCompleted(true);
            dayAtAGlance.updateDailyCalories(w.getCaloriesBurned());
            if (w.getQuant() == Quantifier.MINUTES) {
                dayAtAGlance.updateDailyMinutes(w.getLength());
              //  dayAtAGlance.updateDailyMinutes(w.getLength()*-1);

            }
        }
        DayAtAGlance.updateDB();
        GoalCalculations.calculateGoalProgress(workout, "");
        WorkoutDatabaseManager.getInstance().updateWorkoutsDB();
    }

    //signal completion function
}


