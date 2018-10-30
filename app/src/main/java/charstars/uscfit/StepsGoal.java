package charstars.uscfit;

import charstars.uscfit.RootObjects.Quantifier;

public class StepsGoal extends Goal {

    public StepsGoal(int gNum, int tNum){
        super("walk", gNum, tNum);
    }

    @Override
    public String getQuantifier() {
        return Quantifier.STEPS.getMeasurement();
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
