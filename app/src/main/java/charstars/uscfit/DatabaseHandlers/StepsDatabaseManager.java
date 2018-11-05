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
import java.util.GregorianCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import charstars.uscfit.RootObjects.StepsRecord;

public class StepsDatabaseManager {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static StepsDatabaseManager sm = null;
    //have a list of steps for past 7 days
    //for now just get all steps

    //if user has been using app for less than 7 days, take care of that somehow

    public static StepsDatabaseManager getInstance() {
        if(sm == null){
            sm  = new StepsDatabaseManager();
        }
        return sm;
    }

    //when adding steps to database: add date, associated goal, and # of steps
    public boolean addStepsRecord(StepsRecord sr){
        return true;
    }

    //some notes for Qianze
    //want to display:
    //graph for past week
    //current daily goal + progress
    //current weekly goal + progress

    //can only set daily goal if not already set, otherwise edit
    //can only set weekly goal if not already set, otherwise edit

    private boolean oninit = true;
}
