package charstars.uscfit;

import java.util.Date;

import charstars.uscfit.RootObjects.Quantifier;

public class MilesGoal extends Goal{

    public MilesGoal(Date d, String desc, int gNum, int tNum){
        super(d, desc, gNum, tNum);
    }

    @Override
    public String getQuantifier() {
        return Quantifier.MILES.getMeasurement();
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
