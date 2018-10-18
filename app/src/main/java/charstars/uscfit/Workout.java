package charstars.uscfit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Workout {

    private boolean completed;
    private Activity activity;
    private Quantifier quant;
    private int length;
    Date calendar;
    private int caloriesBurned;

    public Workout(Activity action, Quantifier quant, int length, int yr, int mnth, int dte, int hr, int min) {
        this.activity = action;
        this.quant = quant;
        this.length = length;
        completed = false;
        calendar = Calendar.getInstance().getTime();
        caloriesBurned = action.getDefaultCalorieValue();
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setActivity(Activity action) { this.activity = action; }
    public Activity getActivity() { return this.activity;}

    public void setLength(int len) { this.length = len; }
    public int getLength() { return this.length; }

    public void setQuant(Quantifier quant) { this.quant = quant; }
    public Quantifier getQuant () { return this.quant; }


    //public void setDate(int yr, int mnth, int dte, int hr, int min) { calendar.set(yr, mnth, dte, hr, min); }
    public String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(calendar);
        return today;
    }

    public int getCaloriesBurned() { return this.caloriesBurned; }
}
