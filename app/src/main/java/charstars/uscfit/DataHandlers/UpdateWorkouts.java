package charstars.uscfit.DataHandlers;

import java.util.ArrayList;
import java.util.List;

import charstars.uscfit.DatabaseHandlers.SampleWorkoutDatabase;
import charstars.uscfit.RootObjects.Workout;

public class UpdateWorkouts {

    public static boolean addWorkout(Workout w, String email) {
        SampleWorkoutDatabase.defaultWorkouts.add(w);
        return true;
    }
    public static boolean removeWorkout(Workout w, String email){
        SampleWorkoutDatabase.defaultWorkouts.remove(w);
        return true;
    }

    public static List<Workout> getWorkouts(String email){
        if(email == null)
            return new ArrayList<Workout>();
        return SampleWorkoutDatabase.defaultWorkouts;
    }

    //signal completion function
}
