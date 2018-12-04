package charstars.uscfit.DatabaseHandlers;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import charstars.uscfit.RootObjects.Quantifier;
import charstars.uscfit.RootObjects.Workout;
import charstars.uscfit.WorkoutList;

public class WorkoutDatabaseManager {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static WorkoutDatabaseManager wdm = null;
    private static List<Workout> workouts;
    private HashMap<Integer, Workout> workoutsMap;
    private boolean oninit = true;

    public static WorkoutDatabaseManager getInstance() {
        if(wdm == null){
            wdm  = new WorkoutDatabaseManager();
        }
        return wdm;
    }

    private WorkoutDatabaseManager() {
        this.workouts = new ArrayList<Workout>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        workoutsMap = new HashMap<Integer, Workout>();

        if(currentUser == null){
            return;
        }
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("Workouts");

        // This method is called once with the initial value and again
        // whenever data at this location is updated.

        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                boolean onInit = true;
                workouts = new ArrayList<Workout>();
                GenericTypeIndicator<List<Workout>> t = new GenericTypeIndicator<List<Workout>>() {};
                List<Workout> wm = dataSnapshot.getValue(t);
                if(wm == null){
                    return;
                }
                Log.d("Hello", wm.toString());
                if (wm != null && wm.size()!=0){
                    for (Workout entry : wm) {
                        Workout wmlist = entry;
                        Quantifier quant = wmlist.getQuant();
                        Workout w = new Workout(wmlist.getActivity(), wmlist.getQuant(), wmlist.getLength(), wmlist.getDate(), wmlist.isCompleted());
                        workouts.add(w);
                        //Log.d("Hello", quant);

                    }
                }else{
                    Log.d("GoalDB", "Goals don't exist");
                }
                workoutsMap = new HashMap<Integer, Workout>();
                for(Workout w: workouts){
                    workoutsMap.put(w.getID(), w);
                }

                if(onInit){
                    WorkoutList.onChangeData(workouts);
                    onInit = false;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }

    public void addWorkout(Workout e) {
        Log.d("tag", "trying to add to list");
        if(!workoutsMap.containsKey(e.getID())){
            java.util.Date date1 = new java.util.Date();
            //Log.d(e.getDate().getTime() - date1.getTime(),e.getDate().getTime() - date1.getTime());
//            Toast toast = Toast.makeText(getApplicationContext(),Long.toString(e.getDate().getTime() - date1.getTime()) , Toast.LENGTH_SHORT);
//            toast.show();
            System.out.println("TIME DIFFERENCE: " + Long.toString(e.getDate().getTime() - date1.getTime()));
            if(date1.getTime() - e.getDate().getTime() < 5274000000L) {
                Log.d("tag", "succesfully added to list");
                workouts.add(e);
                workoutsMap.put(e.getID(), e);
                this.updateWorkoutsDB();
            }
        }
    }

    public void removeWorkout(Workout e) {
        boolean removed = workouts.remove(e);
        workoutsMap.remove(e.getID());

        if(removed){
            this.updateWorkoutsDB();
        }

    }

    public List<Workout> getWorkouts() {
        Log.d("inside gdb, get goals", this.workouts.toString());
        return this.workouts;
    }

    public void updateWorkoutsDB() {
        Log.d("updating db", workouts.toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("Workouts");
        myRef2.setValue(workouts);
    }

    public Workout getWorkout(Workout workout) {
        for(Workout w: workouts){
            if(w == workout){
                return w;
            }
           /* if(g.getDescription().equals(goal.getDescription()) && g.getGoalNum()==goal.getGoalNum() && g.getQuantifier().equals(goal.getQuantifier())){
                return g;
            }*/
        }
        return null;
    }
}
