package charstars.uscfit;

import java.util.Date;

import charstars.uscfit.Goal;
import charstars.uscfit.RootObjects.Quantifier;

public class DaysGoal extends Goal {

    Date startingDate;

    public DaysGoal(Date d, String desc, int gNum, int tNum){
        super(null, desc, gNum, tNum);
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
        if(this.trackingNum == 7)
        {
            //BadgeDatabase.badgeCollection.add("Worked out every day for a week");
        }
        if(this.trackingNum == 20)
        {
            //BadgeDatabase.badgeCollection.add("Worked out for 20 days");
        }
        if(this.trackingNum == 365)
        {
            //BadgeDatabase.badgeCollection.add("Worked out every day for a year");
        }
        return true;
    }
}
