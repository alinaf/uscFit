package charstars.uscfit;

import java.util.Date;

import charstars.uscfit.RootObjects.Quantifier;

public class StepsGoal extends Goal {

    public StepsGoal(Date d, int gNum, int tNum){
        super(d, "walk", gNum, tNum);
    }

    @Override
    public String getQuantifier() {
        return Quantifier.STEPS.getMeasurement();
    }

    @Override
    public void setProgress() {

    }


    @Override
    public void setDueDate(final Date dueDate) {
        this.dueDate = null;
        this.valid = true;
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
