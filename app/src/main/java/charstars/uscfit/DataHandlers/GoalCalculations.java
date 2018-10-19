package charstars.uscfit.DataHandlers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import charstars.uscfit.Activity;
import charstars.uscfit.Goal;
import charstars.uscfit.GoalsDisplay;
import charstars.uscfit.SampleGoalDatabase;
import charstars.uscfit.Workout;

public class GoalCalculations {

    private static int goalsThisWeek = 0;

    public static boolean addGoal(Goal e, String email){
        SampleGoalDatabase.defaultGoals.add(e);
        return true;
    }
    public static boolean removeGoal(Goal e, String email){
        SampleGoalDatabase.defaultGoals.remove(e);
        return true;
    }

    //THIS CAN BE INVOKED BY OTHER CLASSES WHEN STEPS ARE COMPLETED OR AN ACTIVITY IS COMPLETED
    public static void calculateGoalProgress(Workout a, String email){
        //CLARIFY ON THIS
        List<Goal> completed = new ArrayList<Goal>();
        int size = SampleGoalDatabase.defaultGoals.size();
        for(int i = 0; i<size; i++){
            Goal g = SampleGoalDatabase.defaultGoals.get(i);
            String desc = g.getDescription().toLowerCase().trim();
            String workoutEx = a.getActivity().getCategory().toLowerCase().trim();

            if( g.getQuantifier().equals(a.getQuant().getMeasurement()) && desc.equals(workoutEx)){
                int length = a.getLength();
                g.setProgress(length);
                if(g.getProgress()==1.0){
                    completed.add(g);
                }
            }
        }

        for(Goal g: completed){
            alertOnCompletion(g, email);
        }


    }
    public static void resetWeek(){
        goalsThisWeek = 0;
    }
    public static int getGoalsThisWeek() {
        return goalsThisWeek;
    }

    public static void setGoalsThisWeek(int goalsThisWeek) {
        GoalCalculations.goalsThisWeek = goalsThisWeek;
    }
    public static void alertOnCompletion(Goal e, String email){
    //notification that goal is completed
        // add badge somwhere
        //remove from list
        removeGoal(e, email);
        goalsThisWeek++;
        Log.d("GOALCALC", "FINISHED GOAL "+ e.getDescription());

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
