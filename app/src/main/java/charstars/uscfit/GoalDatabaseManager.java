package charstars.uscfit;

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
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class GoalDatabaseManager {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static GoalDatabaseManager gm = null;
    private static List<Goal> goals;
    private Map<Integer, Goal> goalsMap = new HashMap<Integer, Goal>();
    private boolean oninit = true;

    public static GoalDatabaseManager getInstance() {
        if(gm == null){
            gm  = new GoalDatabaseManager();
        }
        return gm;
    }

    private GoalDatabaseManager() {
        this.goals = new ArrayList<Goal>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("GoalsList");

        // This method is called once with the initial value and again
        // whenever data at this location is updated.

        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean onInit = true;
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                goals = new ArrayList<Goal>();
                GenericTypeIndicator<List<Goal>> t = new GenericTypeIndicator<List<Goal>>() {};
                List<Goal> gm = dataSnapshot.getValue(t);
                Log.d("Hello", gm.toString());
                if (gm != null && gm.size()!=0){
                    for (Goal entry : gm) {
                        Goal gmlist = entry;
                        String quant = gmlist.getQuantifier();
                        Log.d("Hello", quant);
                        if(quant.equals("miles")){
                            MilesGoal g = new MilesGoal(gmlist.getDescription(), gmlist.getGoalNum(), gmlist.getTrackingNum());
                            goals.add(g);

                        }else if(quant.equals("minutes")){
                            MinutesGoal g = new MinutesGoal(gmlist.getDescription(), gmlist.getGoalNum(), gmlist.getTrackingNum());
                            goals.add(g);

                        }else if(quant.equals("steps")){
                            StepsGoal g = new StepsGoal(gmlist.getGoalNum(), gmlist.getTrackingNum());
                            goals.add(g);

                        }else if(quant.equals("days")){
                            DaysGoal g = new DaysGoal(new Date(), gmlist.getDescription(), gmlist.getGoalNum(), gmlist.getTrackingNum());
                            goals.add(g);

                        }
                    }
                }else{
                    Log.d("GoalDB", "Goals don't exist");
                }
                goalsMap = new HashMap<Integer, Goal>();
                for(Goal g: goals){
                    Log.d("inside whatsingoals", g.toString());
                    goalsMap.put(g.id(), g);
                }

                if(onInit){
                    GoalsDisplay.onChangeData(goals);
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

    public void addGoal(Goal e) {
        if(goalsMap.get(e.id())==null){
            goals.add(e);
            goalsMap.put(e.id(), e);
            this.updateGoalsDB();
        }
    }

    public void removeGoal(Goal e) {
        boolean removed = goals.remove(e);
        goalsMap.remove(e.id());

        if(removed){
            this.updateGoalsDB();
        }

    }

    public List<Goal> getGoals() {
        Log.d("inside gdb, get goals", this.goals.toString());
        return this.goals;
    }

    public void updateGoalsDB() {
        Log.d("updating db", goals.toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("GoalsList");
        myRef2.setValue(goals);
    }

    public Goal getGoal(Goal goal) {
        for(Goal g: goals){
            if(g == goal){
                return g;
            }
            if(g.getDescription().equals(goal.getDescription()) && g.getGoalNum()==goal.getGoalNum() && g.getQuantifier().equals(goal.getQuantifier())){
                return g;
            }
        }
        return null;
    }
}
