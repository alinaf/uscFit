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

import charstars.uscfit.RootObjects.Badge;
import charstars.uscfit.BadgesDisplay;

public class BadgeDatabaseManager {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static BadgeDatabaseManager bm = null;
    private static ArrayList<Badge> badges = null;
    private boolean changed = true;

    public static BadgeDatabaseManager getInstance() {
        if(bm == null){
            bm  = new BadgeDatabaseManager();
        }
        return bm;
    }

    public void query(){
        badges = new ArrayList<Badge>();
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
        DatabaseReference myRef2 = myRef1.child("BadgesList");

        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean onInit = true;
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                badges = new ArrayList<Badge>();
                GenericTypeIndicator<ArrayList<Badge>> t = new GenericTypeIndicator<ArrayList<Badge>>() {};
                ArrayList<Badge> bm = dataSnapshot.getValue(t);
                if(bm == null){
                    return;
                }
                Log.d("Hello", bm.toString());
                if (bm != null && bm.size()!=0){
                    for (Badge entry : bm) {
                        badges.add(entry);
                    }
                }else{
                    Log.d("BadgeDB", "Badges don't exist");
                }

                if(onInit){
                   BadgesDisplay.onChangeData(badges);
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

    private BadgeDatabaseManager() {
        query();

    }

    public void addBadge(Badge e) {
        if(!badges.contains(e)){
            badges.add(e);
        }
        this.updateBadgesDB();
    }

    public void removeBadge(Badge e) {
        boolean removed = badges.remove(e);

        if(removed){
            this.updateBadgesDB();
        }

    }

    public ArrayList<Badge> getBadges() {
        if(this.badges == null){
            query();
        }
        Log.d("inside gdb, get badges", this.badges.toString());
        return this.badges;
    }

    public void updateBadgesDB() {
        Log.d("updating db", badges.toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("BadgesList");
        myRef2.setValue(badges);
    }

    public Badge getBadge(Badge badge) {
        for(Badge g: badges){
            if(g == badge){
                return g;
            }
            if(g.getmTrophyNum()==badge.getmTrophyNum() && g.getmName().equals(badge.getmName())){
                return g;
            }
        }
        return null;
    }
}
