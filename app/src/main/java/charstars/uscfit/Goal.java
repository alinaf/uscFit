package charstars.uscfit;

import java.util.Date;
import java.util.Map;
enum Quantifier
{
    MILES("miles"), MINUTES("minutes"), STEPS("steps"), DAYS("days");

    private String measurement;

    // getter method
    public String getMeasurement()
    {
        return this.measurement;
    }

    // enum constructor - cannot be public or protected
    Quantifier(String action)
    {
        this.measurement = action;
    }
}
public abstract class Goal {

    String description = "";
    int goalNum = 100;
    int trackingNum = 0; //how many so far
    public Goal(String description, int goalNum, int trackingNum) {
        this.description = description;
        this.goalNum = goalNum;
        this.trackingNum = trackingNum;
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

    public abstract String getQuantifier();
    public abstract void setProgress();
    public abstract boolean setProgress(int n);

    //returns percentage of goal completed
    public double getProgress() {
        double ans = (double)(this.trackingNum)/(double)(this.goalNum);
        double newKB = Math.round(ans*100.0)/100.0;
        return newKB;
    }

}
