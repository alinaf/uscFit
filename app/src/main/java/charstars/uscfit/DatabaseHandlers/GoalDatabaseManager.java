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
        import java.util.Date;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Map;
        import java.util.Set;
        import java.util.Timer;
        import java.util.TimerTask;

        import charstars.uscfit.DaysGoal;
        import charstars.uscfit.Goal;
        import charstars.uscfit.GoalsDisplay;
        import charstars.uscfit.MilesGoal;
        import charstars.uscfit.MinutesGoal;
        import charstars.uscfit.StepsGoal;

public class GoalDatabaseManager {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static GoalDatabaseManager gm = null;
    private static List<Goal> goals;
    private Set<Goal> goalsMap= new HashSet<Goal>();
    private boolean oninit = true;

    public static void resetDb() {
        gm = null;
    }

    public static GoalDatabaseManager getInstance() {
        if(gm == null){
            gm  = new GoalDatabaseManager();
        }
        return gm;
    }

    private void query(){
        this.goals = new ArrayList<Goal>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            return;
        }
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
                if(gm == null){
                    return;
                }
                Log.d("Hello", gm.toString());
                if (gm != null && gm.size()!=0){
                    for (Goal entry : gm) {
                        Goal gmlist = entry;
                        String quant = gmlist.getQuantifier();
                        Log.d("Hello", quant);

                        Date d = gmlist.getDueDate();
                        if(quant.equals("miles")){
                            MilesGoal g = new MilesGoal(d, gmlist.getDescription(), gmlist.getGoalNum(), gmlist.getTrackingNum());
                            g.setValid(entry.isValid());
                            goals.add(g);

                        }else if(quant.equals("minutes")){
                            MinutesGoal g = new MinutesGoal(d, gmlist.getDescription(), gmlist.getGoalNum(), gmlist.getTrackingNum());
                            g.setValid(entry.isValid());
                            goals.add(g);

                        }else if(quant.equals("steps")){
                            StepsGoal g = new StepsGoal(d, gmlist.getGoalNum(), gmlist.getTrackingNum());
                            g.setValid(entry.isValid());
                            goals.add(g);

                        }else if(quant.equals("days")){

                            //NOT SURE WHAT TO DO ABOUT DATE HERE
                            DaysGoal g = new DaysGoal(new Date(), gmlist.getDescription(), gmlist.getGoalNum(), gmlist.getTrackingNum());
                            g.setValid(entry.isValid());
                            goals.add(g);

                        }
                    }
                }else{
                    Log.d("GoalDB", "Goals don't exist");
                }
                goalsMap = new HashSet<Goal>();
                for(Goal g: goals){
                    Log.d("inside whatsingoals", g.toString());
                        goalsMap.add(g);
                }

                if(onInit){
                    //GoalsDisplay.onChangeData(goals);
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
    private GoalDatabaseManager() {
        query();
    }

    public void addGoal(Goal e) {
        if(!goals.contains(e)){
            goals.add(e);
            goalsMap.add(e);
            this.updateGoalsDB();
        }
    }

    public void removeGoal(Goal e) {
        boolean removed = goals.remove(e);
        goalsMap.remove(e);

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
