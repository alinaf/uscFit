package charstars.uscfit;

import android.util.Log;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import charstars.uscfit.DataHandlers.GoalCalculations;
import charstars.uscfit.DatabaseHandlers.GoalDatabaseManager;

public class Goal implements Serializable{

    static int _ID = 0;
    private int id;

    protected Date dueDate;
    protected boolean valid = true;
    transient private Timer timer = null;
    protected String description = "";
    protected String quantifier = "";
    protected int goalNum = 100;
    protected int trackingNum = 0; //how many so far
    public Goal(Date dueDate, String description, int goalNum, int trackingNum) {
        this.description = description;
        this.goalNum = goalNum;
        this.trackingNum = trackingNum;
        this.id = _ID;
        this.valid = true;
        setDueDate(dueDate);
        _ID++;
    }

    public Goal(){

    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(final Date dueDate) {
//
//        final Calendar cal = Calendar.getInstance();
//        cal.setTime(dueDate);
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//
//        if(this.dueDate!=null){
//
//        cal.setTime(this.dueDate);
//        int year0 = cal.get(Calendar.YEAR);
//        int month0 = cal.get(Calendar.MONTH);
//        int day0 = cal.get(Calendar.DAY_OF_MONTH);
//
//            cal.setTime(new Date());
//            int yearC = cal.get(Calendar.YEAR);
//            int monthC = cal.get(Calendar.MONTH);
//            int dayC = cal.get(Calendar.DAY_OF_MONTH);
//        if((year == year0 && month == month0 && day == day0)){
//            //SAME DAY AS CURRENTLY SET
//            valid = false;
//            return;
//        }
//
//        }
//
//        cal.set(year, month, day, 23, 59);
       // this.dueDate = cal.getTime();
        this.dueDate = dueDate;
        if(this.dueDate == null){
            return;
        }
        if(timer == null) {
            timer = new Timer();
        }else{
            timer.cancel();
            timer = new Timer();
        }
        this.valid = true;

        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                Log.d("RUNNING", "inside timer "+dueDate.toString()+" current date" + (new Date()).toString());
                valid = false;
                //UPDATE SINGLE GOAL HERE
            }
        };

 try {
            timer.schedule(t, this.dueDate);
     Log.d("CHANGED TIMER", ""+dueDate.toString() + " current date: "+ (new Date()).toString());

 }catch (IllegalStateException e){
            Log.d("RUNNING", "inside illegalstateexception"+dueDate.toString() + " current date: "+ (new Date()).toString());
            valid = false;
            //UPDATE THIS GOAL ONLY
        }catch(IllegalArgumentException e){

             e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGoalNum() {
        return goalNum;
    }

    public void setGoalNum(int goalNum) {
        this.goalNum = goalNum;
    }

    public int getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(int trackingNum) {
        this.trackingNum = trackingNum;
    }

    public String getType(){
        return this.getClass().getName();
    }

    public int id(){
        return id;
    }

    public String getQuantifier(){
        return this.quantifier;
    };
    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }

    public void setProgress(){};
    public boolean setProgress(int n){
        return true;
    };

    //returns percentage of goal completed
    public double getProgress() {
        double ans = (double)(this.trackingNum)/(double)(this.goalNum);
        double newKB = Math.round(ans*100.0)/100.0;
        return newKB;
    }

}
