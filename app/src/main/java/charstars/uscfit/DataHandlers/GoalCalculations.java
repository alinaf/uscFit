package charstars.uscfit.DataHandlers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import charstars.uscfit.Goal;
import charstars.uscfit.GoalDatabaseManager;
import charstars.uscfit.Workout;

public class GoalCalculations {

    private static int goalsThisWeek = 0;
    private static GoalDatabaseManager gdb = GoalDatabaseManager.getInstance();

    public static boolean addGoal(Goal e, String email){
        Log.d("updating gc", e.toString());
        gdb.addGoal(e);
        return true;
    }
    public static boolean removeGoal(Goal e, String email){
        gdb.removeGoal(e);
        return true;
    }

    //THIS CAN BE INVOKED BY OTHER CLASSES WHEN STEPS ARE COMPLETED OR AN ACTIVITY IS COMPLETED
    public static void calculateGoalProgress(Workout a, String email){
        List<Goal> completed = new ArrayList<Goal>();
        int size = gdb.getGoals().size();
        for(int i = 0; i<size; i++){
            Goal g = gdb.getGoals().get(i);
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

        gdb.updateGoalsDB();
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

        Log.d("inside GoalCalc", gdb.getGoals().toString());
        return
                gdb.getGoals();
    }

    public static void editGoal(Goal copy, Goal orig) {
       Goal g = gdb.getGoal(orig);
        Log.d("GOAL ID", g.id()+" "+copy.id());

               g.setDescription(copy.getDescription());
               g.setGoalNum(copy.getGoalNum());
               g.setTrackingNum(copy.getTrackingNum());


               for(Goal ggg: gdb.getGoals()){
                   Log.d("goal", ggg.getDescription());
               }
        gdb.updateGoalsDB();
    }
}
