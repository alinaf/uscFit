package charstars.uscfit.DataHandlers;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

//import charstars.uscfit.BadgeDatabase;
import charstars.uscfit.Goal;
import charstars.uscfit.DatabaseHandlers.GoalDatabaseManager;
import charstars.uscfit.LoginActivity;
import charstars.uscfit.NotificationHelper;
import charstars.uscfit.RootObjects.Workout;

public class GoalCalculations extends AppCompatActivity {

    private static int goalsThisWeek = 0;
    private static Map<Goal, Timer> timerMap = new HashMap<Goal, Timer>();
    //private st

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
        GoalCalculations gc = new GoalCalculations();
        int size = GoalDatabaseManager.getInstance().getGoals().size();
        for(int i = 0; i<size; i++){
            Goal g = GoalDatabaseManager.getInstance().getGoals().get(i);
            String desc = g.getDescription().toLowerCase().trim();
            String workoutEx = a.getActivity().getCategory().toLowerCase().trim();
            Log.d("COMPLETION", desc+" "+workoutEx);
            if( g.getQuantifier().toLowerCase().equals(a.getQuant().getMeasurement().toLowerCase()) && desc.equals(workoutEx)){
                int length = a.getLength();
                g.setProgress(length);
                if(g.getProgress()==1.0){
                    completed.add(g);
                    //GoalCalculations gc = new GoalCalculations();
                    //gc.sendNotifs(g);
                }
            }
        }

        for(Goal g: completed){
            alertOnCompletion(g, email);
            //.add(g.getDescription());
        }

        GoalDatabaseManager.getInstance().updateGoalsDB();
    }

    public void sendNotifs(Goal g)
    {
        mNotificationHelper = new NotificationHelper(GoalCalculations.this);
        sendNotification("Goal completed!", "You have successfully completed your goal: " + g.getDescription());
    }
    private NotificationHelper mNotificationHelper;
    public void sendNotification(String title, String message)
    {
        NotificationCompat.Builder nb = mNotificationHelper.getChannelNotification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build());
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
        BadgeCalculator.addBadge(e, new Date());
        GoalDatabaseManager.getInstance().getGoals().remove(e);
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
        Log.d("ENTERS THIS FUNCTION", "LOL");
       Goal g = GoalDatabaseManager.getInstance().getGoal(orig);
       if(g == null){
           Log.d("goal is NULL", "eferf");
           return;
       }
        Log.d("GOAL ID", g.id()+" "+copy.id());

               g.setDescription(copy.getDescription());
               g.setGoalNum(copy.getGoalNum());
               g.setTrackingNum(copy.getTrackingNum());
               g.setDueDate(copy.getDueDate());
               g.setValid(copy.isValid());


               for(Goal ggg: GoalDatabaseManager.getInstance().getGoals()){
                   Log.d("goal", ggg.getDescription());
               }
        GoalDatabaseManager.getInstance().updateGoalsDB();
    }
}
