package charstars.uscfit.RootObjects;

import android.support.v4.app.NotificationCompat;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import charstars.uscfit.Activity;
import charstars.uscfit.NotificationHelper;
import charstars.uscfit.WorkoutPopUp;


public class Workout implements Serializable, Comparable<Workout>  {

    static int _ID = 0;
    private int id;
    private boolean completed;
    private Activity activity;
    private Quantifier quant;
    private int length;
    java.util.Date date = null;
    private long caloriesBurned;

    public Workout(Activity action, Quantifier quant, int length, java.util.Date d) {
        this.activity = action;
        this.quant = quant;
        this.length = length;
        completed = false;
        this.date = d;
        this.caloriesBurned = action.getDefaultCalorieValue()*length/60;
        this.id = _ID++;
    }

    public Workout(Activity action, Quantifier quant, int length, java.util.Date d, boolean c) {
        this.activity = action;
        this.quant = quant;
        this.length = length;
        this.completed = c;
        this.date = d;
        this.caloriesBurned = action.getDefaultCalorieValue()*length/60;
        this.id = _ID++;
    }

    public Workout() {
        //     calendar = GregorianCalendar.getInstance();
        //     calendar.getTimeInMillis();
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setActivity(Activity action) {
        this.activity = action;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setLength(int len) {
        this.length = len;
    }

    public int getLength() {
        return this.length;
    }

    public void setQuant(Quantifier quant) {
        this.quant = quant;
    }

    public Quantifier getQuant() {
        return this.quant;
    }


    public Date getDate() {
        return date;
    }

    public String stringDate() {
        return date.toString();
    }


    public long getCaloriesBurned() {
        return this.caloriesBurned;
    }

    public int getID() {
        return this.id;
    }



    @Override
    public int compareTo(Workout o) {
       return getDate().compareTo(o.getDate());
    }
}
