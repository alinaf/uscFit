package charstars.uscfit.DatabaseHandlers;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import charstars.uscfit.Activity;

public class ActivityDatabaseManager {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static ActivityDatabaseManager adm = null;
    private static List<Activity> allActivities = new ArrayList<>();
    private static List<Activity> customActivities;
    private static List<Activity> defaultActivities;
    private static Set<String> activityNames;
    private HashMap<Integer, Activity> activitiesMap;
    private boolean oninit = true;




    public static ActivityDatabaseManager getInstance() {
        if(adm == null){
            adm  = new ActivityDatabaseManager();
        }
        return adm;
    }

    private ActivityDatabaseManager() {
        this.defaultActivities = new ArrayList<Activity>();
        this.activityNames = new HashSet<String>();
        this.customActivities = new ArrayList<Activity>();
        setDefaultActivities();

        for(Activity a : defaultActivities) {
            activityNames.add(a.getCategory().toLowerCase());
        }
        //updateActivitiesDB();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        activitiesMap = new HashMap<Integer, Activity>();

        if(currentUser == null){
            return;
        }
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("Activities");

        // This method is called once with the initial value and again
        // whenever data at this location is updated.

        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                boolean onInit = true;
               // customActivities = new ArrayList<Activity>();
                GenericTypeIndicator<List<Activity>> t = new GenericTypeIndicator<List<Activity>>() {};
                List<Activity> am = dataSnapshot.getValue(t);
                if(am == null){
                    return;
                }
                Log.d("Hello", am.toString());
                if (am != null && am.size()!=0){
                    for (Activity entry : am) {
                        Activity amlist = entry;
                        Activity a = new Activity(amlist.getCategory(), amlist.getDefaultCalorieValue());
                        customActivities.add(a);

                        //checking if string exists
                        activityNames.add(a.getCategory().toLowerCase());
                        //Log.d("Hello", quant);

                    }
                }else{
                    Log.d("GoalDB", "Goals don't exist");
                }
                activitiesMap = new HashMap<Integer, Activity>();
                for(Activity w: customActivities){
                    activitiesMap.put(w.getID(), w);
                }
/*
                if(onInit){
                    WorkoutList.onChangeData(workouts);
                    onInit = false;
                }*/

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }

    public void addActivity(Activity e) {
        Log.d("tag", "trying to add to list");
        if(!activitiesMap.containsKey(e.getID())){
            Log.d("tag", "succesfully added to list");
            customActivities.add(e);
            activityNames.add(e.getCategory());
            activitiesMap.put(e.getID(), e);
            this.updateActivitiesDB();
        }
    }

    public void removeActivity(Activity e) {
        boolean removed = customActivities.remove(e);
        activitiesMap.remove(e.getID());
        activityNames.remove(e.getCategory());
        if(removed){
            this.updateActivitiesDB();
        }

    }

    public List<Activity> getActivities() {
        Log.d("inside gdb, get goals", this.defaultActivities.toString());
        allActivities.clear();
        allActivities.addAll(defaultActivities);
        allActivities.addAll(customActivities);
        return this.allActivities;
    }

    public void updateActivitiesDB() {
        Log.d("updating db", customActivities.toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("Activities");
        myRef2.setValue(customActivities);
    }

    /* Would need to update bruhhhh
    public Activity getActivity(Activity activity) {
        for(Activity a: defaultActivities){
            if(a == activity ){
                return a;
            }
            if(g.getDescription().equals(goal.getDescription()) && g.getGoalNum()==goal.getGoalNum() && g.getQuantifier().equals(goal.getQuantifier())){
                return g;
            }
        }
        return null;
    }*/


    public void setDefaultActivities() {
        defaultActivities.add(new Activity("Biking (competitive)", 8.5));
        defaultActivities.add(new Activity("Biking (leisure)", 4));
        defaultActivities.add(new Activity("Weight lifting", 6));
        defaultActivities.add(new Activity("Ballet", 4.8));
        defaultActivities.add(new Activity("Rowing (intense)", 8.5));
        defaultActivities.add(new Activity("Rowing (leisure)", 3.5));
        defaultActivities.add(new Activity("Stretching", 2.5));
        defaultActivities.add(new Activity("Aerobics", 6.5));
        defaultActivities.add(new Activity("House cleaning", 3));
        defaultActivities.add(new Activity("Lawn mowing", 5.5));
        defaultActivities.add(new Activity("Gardening", 4));
        defaultActivities.add(new Activity("Playing drums", 4));
        defaultActivities.add(new Activity("Carpentry", 3.5));
        defaultActivities.add(new Activity("Jogging", 7));
        defaultActivities.add(new Activity("Running (12 min/mile)", 8));
        defaultActivities.add(new Activity("Running (10 min/mile)", 10));
        defaultActivities.add(new Activity("Running (9 min/mile)", 11));
        defaultActivities.add(new Activity("Running (8 min/mile)", 12.5));
        defaultActivities.add(new Activity("Running (7 min/mile)", 14));
        defaultActivities.add(new Activity("Running (6 min/mile)", 16));
        defaultActivities.add(new Activity("Running, cross country", 9));
        defaultActivities.add(new Activity("Badminton (leisure)", 4.5));
        defaultActivities.add(new Activity("Badminton (competitive)", 7));
        defaultActivities.add(new Activity("Basketball (leisure)", 7));
        defaultActivities.add(new Activity("Basketball (competitive)", 4.5));
        defaultActivities.add(new Activity("Boxing (in ring)", 12));
        defaultActivities.add(new Activity("Boxing (punching bag)", 6));
        defaultActivities.add(new Activity("Coaching sports", 4));
        defaultActivities.add(new Activity("Cricket", 5));
        defaultActivities.add(new Activity("Croquet", 2.5));
        defaultActivities.add(new Activity("Fencing", 6));
        defaultActivities.add(new Activity("Football (leisure)", 9));
        defaultActivities.add(new Activity("Football (competitive)", 8));
        defaultActivities.add(new Activity("Frisbee (leisure)", 3));
        defaultActivities.add(new Activity("Frisbee (competitive)", 8));
        defaultActivities.add(new Activity("Golf", 4.5));
        defaultActivities.add(new Activity("Field hockey", 8));
        defaultActivities.add(new Activity("Ice hockey", 8));
        defaultActivities.add(new Activity("Horseback riding", 4));
        defaultActivities.add(new Activity("Skateboarding", 5));
        defaultActivities.add(new Activity("Soccer (leisure)", 10));
        defaultActivities.add(new Activity("Soccer (competitive)", 7));
        defaultActivities.add(new Activity("Tennis", 7));
        defaultActivities.add(new Activity("Volleyball (leisure)", 3));
        defaultActivities.add(new Activity("Volleyball (competitive)", 8));
        defaultActivities.add(new Activity("Hiking", 6));
        defaultActivities.add(new Activity("Swimming", 9));
        defaultActivities.add(new Activity("Water polo", 10));
        defaultActivities.add(new Activity("Skiing", 7));
    }

    public boolean doesExist(String activityName) {
        return activityNames.contains(activityName);
    }
}

