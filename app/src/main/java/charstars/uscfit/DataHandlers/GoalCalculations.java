package charstars.uscfit.DataHandlers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import charstars.uscfit.BadgeDatabase;
import charstars.uscfit.Goal;
import charstars.uscfit.DatabaseHandlers.GoalDatabaseManager;
import charstars.uscfit.RootObjects.Workout;

public class GoalCalculations {

    private static int goalsThisWeek = 0;

    public static boolean addGoal(Goal e, String email){
        Log.d("updating gc", e.toString());
        GoalDatabaseManager.getInstance().addGoal(e);
        return true;
    }
    public static boolean removeGoal(Goal e, String email){
        GoalDatabaseManager.getInstance().removeGoal(e);
        return true;
    }

    //THIS CAN BE INVOKED BY OTHER CLASSES WHEN STEPS ARE COMPLETED OR AN ACTIVITY IS COMPLETED
    public static void calculateGoalProgress(Workout a, String email){
        List<Goal> completed = new ArrayList<Goal>();
        int size = GoalDatabaseManager.getInstance().getGoals().size();
        for(int i = 0; i<size; i++){
            Goal g = GoalDatabaseManager.getInstance().getGoals().get(i);
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
            //.add(g.getDescription());
        }

        GoalDatabaseManager.getInstance().updateGoalsDB();
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
        String q = "";
        if(e.getGoalNum()==1){
            q = e.getQuantifier().substring(0, e.getQuantifier().length()-1);
        }else{
            q = e.getQuantifier();
        }
        String desc = "Completed: " + e.getDescription() + " " + e.getGoalNum() + " " + q;
        BadgeCalculator.addBadge(desc, e.getGoalNum(), (new Date()));
        removeGoal(e, email);

        goalsThisWeek++;

        if(goalsThisWeek == 50){
            BadgeCalculator.addBadge("Completed 50 goals", 50, (new Date()));
        }
        if(goalsThisWeek == 100){
            BadgeCalculator.addBadge("Completed 100 goals", 100, (new Date()));
        }
        if(goalsThisWeek == 500){
            BadgeCalculator.addBadge("Completed 500 goals", 500, (new Date()));
        }
        Log.d("GOALCALC", "FINISHED GOAL "+ e.getDescription());

    }
    public static List<Goal> getGoals(String email){

        Log.d("inside GoalCalc", GoalDatabaseManager.getInstance().getGoals().toString());
        return
                GoalDatabaseManager.getInstance().getGoals();
    }

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
    }
}
