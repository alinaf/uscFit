package charstars.uscfit;

import java.util.Date;

public class DaysGoal extends Goal{

    Date startingDate;

    public DaysGoal(Date d, String desc, int gNum, int tNum){
        super(desc, gNum, tNum);
        this.startingDate = d;
    }
    @Override
    public String getQuantifier() {
        return Quantifier.MINUTES.getMeasurement();
    }

    @Override
    public void setProgress() {

    }

    @Override
    //adds the integer quantity to tracking number
    public boolean setProgress(int n) {
        //add limits?
        this.trackingNum+=n;
        if(this.trackingNum>this.goalNum){
            this.trackingNum = goalNum;
        }
        return true;
    }
}
