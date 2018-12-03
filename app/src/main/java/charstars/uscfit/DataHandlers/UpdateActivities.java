package charstars.uscfit.DataHandlers;

import java.util.List;

import charstars.uscfit.Activity;
import charstars.uscfit.DatabaseHandlers.ActivityDatabaseManager;
import charstars.uscfit.RootObjects.Workout;

public class UpdateActivities {
    public static boolean addActivity(Activity a) {
        ActivityDatabaseManager.getInstance().addActivity(a);
        return true;
    }
    public static boolean removeActivity(Activity a){
        ActivityDatabaseManager.getInstance().removeActivity(a);
        return true;
    }

    public static List<Activity> getActivities(){
        return ActivityDatabaseManager.getInstance().getActivities();
    }

    //does activity already exist function
    public static boolean activityExists(String activityName) {
        return ActivityDatabaseManager.getInstance().doesExist(activityName);
    }

}



