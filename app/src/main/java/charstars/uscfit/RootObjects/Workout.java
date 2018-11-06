package charstars.uscfit.RootObjects;

import charstars.uscfit.Activity;


public class Workout {

    static int _ID = 0;
    private int id;
    private boolean completed;
    private Activity activity;
    private Quantifier quant;
    private int length;
    Date date = null;
    private long caloriesBurned;

    public Workout(Activity action, Quantifier quant, int length, Date d) {
        this.activity = action;
        this.quant = quant;
        this.length = length;
        completed = false;
        this.date = d;
        this.caloriesBurned = action.getDefaultCalorieValue();
        this.id = _ID++;
    }

    public Workout(Activity action, Quantifier quant, int length, Date d, boolean c ) {
        this.activity = action;
        this.quant = quant;
        this.length = length;
        this.completed = c;
        this.date = d;
        this.caloriesBurned = action.getDefaultCalorieValue();
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

    public void setActivity(Activity action) { this.activity = action; }
    public Activity getActivity() { return this.activity;}

    public void setLength(int len) { this.length = len; }
    public int getLength() { return this.length; }

    public void setQuant(Quantifier quant) { this.quant = quant; }
    public Quantifier getQuant () { return this.quant; }


    public Date getDate() {
        return date;
    }

    public String stringDate() {
        return date.toStringDate();
    }



    public long getCaloriesBurned() { return this.caloriesBurned; }

    public int getID() { return this.id; }
}

