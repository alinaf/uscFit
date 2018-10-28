package charstars.uscfit;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Goal implements Serializable{

    static int _ID = 0;
    private int id;
    protected String description = "";
    protected String quantifier = "";
    protected int goalNum = 100;
    protected int trackingNum = 0; //how many so far
    public Goal(String description, int goalNum, int trackingNum) {
        this.description = description;
        this.goalNum = goalNum;
        this.trackingNum = trackingNum;
        this.id = _ID;
        _ID++;
    }

    public Goal(){

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
