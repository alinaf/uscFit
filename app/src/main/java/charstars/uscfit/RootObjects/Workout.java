package charstars.uscfit.RootObjects;

import android.support.v4.app.NotificationCompat;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import charstars.uscfit.Activity;
import charstars.uscfit.NotificationHelper;
import charstars.uscfit.UserInfo;
import charstars.uscfit.WorkoutPopUp;


public class Workout implements Serializable, Comparable<Workout>  {

    static int _ID = 0;
    private int id;
    private boolean completed;
    private Activity activity;
    private Quantifier quant;
    private int length;
    java.util.Date date = null;
    private double caloriesBurned;
    UserInfo ui = new UserInfo();

    public Workout(Activity action, Quantifier quant, int length, java.util.Date d) {
        this.activity = action;
        this.quant = quant;
        this.length = length;
        completed = false;
        this.date = d;
        double calories;
        if (ui != null) {
            /* equation from https://www.businessinsider.com/how-to-calculate-calories-burned-exercise-met-value-2017-8 */
            calories = (ui.getWeight() / 2.2) * action.getDefaultCalorieValue()*length/60;
        }
        else { // average weight
            calories = 80.7 * action.getDefaultCalorieValue()*length/60;
        }
        this.caloriesBurned = Math.floor(calories * 100) / 100;
        this.id = _ID++;
    }

    public Workout(Activity action, Quantifier quant, int length, java.util.Date d, boolean c) {
        this.activity = action;
        this.quant = quant;
        this.length = length;
        this.completed = c;
        this.date = d;
        double calories;
        if (ui != null) {
            /* equation from https://www.businessinsider.com/how-to-calculate-calories-burned-exercise-met-value-2017-8 */
            calories = (ui.getWeight() / 2.2) * action.getDefaultCalorieValue()*length/60;
        }
        else { // average weight
            calories = 80.7 * action.getDefaultCalorieValue()*length/60;
        }
        this.caloriesBurned = Math.floor(calories * 100) / 100;
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


    public double getCaloriesBurned() {
        return this.caloriesBurned;
    }

    public int getID() {
        return this.id;
    }



    @Override
    public int compareTo(Workout o) {
        if(isCompleted() && o.isCompleted())
            return getDate().compareTo(o.getDate());
        else if(!isCompleted() && !o.isCompleted())
            return getDate().compareTo(o.getDate());
        else if(isCompleted() && !o.isCompleted())
            return 1;
        else
            return -1;

    }
}
