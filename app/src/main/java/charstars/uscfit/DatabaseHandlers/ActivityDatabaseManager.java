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
import java.util.List;

import charstars.uscfit.Activity;

public class ActivityDatabaseManager {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static ActivityDatabaseManager adm = null;
    private static List<Activity> allActivities = new ArrayList<>();
    private static List<Activity> customActivities;
    private static List<Activity> defaultActivities;
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
        setDefaultActivities();
        this.customActivities = new ArrayList<Activity>();
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
                customActivities = new ArrayList<Activity>();
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
            activitiesMap.put(e.getID(), e);
            this.updateActivitiesDB();
        }
    }

    public void removeActivity(Activity e) {
        boolean removed = customActivities.remove(e);
        activitiesMap.remove(e.getID());

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
        defaultActivities.add(new Activity("Aerobics", 384));
        defaultActivities.add(new Activity("Badminton", 266));
        defaultActivities.add(new Activity("Ballet", 266));
        defaultActivities.add(new Activity("Ballroom Dancing", 177));
        defaultActivities.add(new Activity("Baseball", 295));
        defaultActivities.add(new Activity("Softball", 295));
        defaultActivities.add(new Activity("Basketball", 400));
        defaultActivities.add(new Activity("Biking ", 390));
        defaultActivities.add(new Activity("Bowling", 177));
        defaultActivities.add(new Activity("Boxing", 354));
        defaultActivities.add(new Activity("Football", 472));
        defaultActivities.add(new Activity("Gardening", 236));
        defaultActivities.add(new Activity("General Cleaning", 207));
        defaultActivities.add(new Activity("General Housework", 208));
        defaultActivities.add(new Activity("Golf", 266));
        defaultActivities.add(new Activity("Hiking", 354));
        defaultActivities.add(new Activity("Hockey", 472));
        defaultActivities.add(new Activity("Horseback Riding", 236));
        defaultActivities.add(new Activity("Jumping Rope", 472));
        defaultActivities.add(new Activity("Lacrosse", 472));
        defaultActivities.add(new Activity("Martial Arts", 590));
        defaultActivities.add(new Activity("Mowing Lawn", 325));
        defaultActivities.add(new Activity("Painting", 266));
        defaultActivities.add(new Activity("Ping Pong", 236));
        defaultActivities.add(new Activity("Playing with Children", 236));
        defaultActivities.add(new Activity("Rock Climbing", 472));
        defaultActivities.add(new Activity("Roller Blading ", 708));
        defaultActivities.add(new Activity("Running", 472));
        defaultActivities.add(new Activity("Skateboarding", 295));
        defaultActivities.add(new Activity("Skiing", 295));
        defaultActivities.add(new Activity("Soccer", 450));
        defaultActivities.add(new Activity("Swimming", 354));
        defaultActivities.add(new Activity("Tennis", 472));
        defaultActivities.add(new Activity("Ultimate Frisbee", 472));
        defaultActivities.add(new Activity("Volleyball", 177));
        defaultActivities.add(new Activity("Walking", 195));
        defaultActivities.add(new Activity("Walking the Dog", 177));
        defaultActivities.add(new Activity("Watering the Garden", 89));
        defaultActivities.add(new Activity("Weight Lifting", 354));
        defaultActivities.add(new Activity("Yoga", 234));
    }

}

