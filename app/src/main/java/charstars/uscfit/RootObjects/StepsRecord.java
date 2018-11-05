package charstars.uscfit.RootObjects;
import java.io.Serializable;
import java.util.GregorianCalendar;

import charstars.uscfit.Goal;

public class StepsRecord implements Serializable {
    //static int _ID; do we need an id?
    protected GregorianCalendar date = null;
    protected Goal goal = null;
    protected int numSteps;

    public StepsRecord(GregorianCalendar date_, Goal g_, int numSteps_) {
        this.date = date_;
        this.goal = g_;
        this.numSteps = numSteps_;

    }

}
