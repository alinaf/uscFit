package charstars.uscfit;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class WorkoutTest {

    @Test
    public void workoutCompletion() {
        Activity activity = new Activity("Running", 472);
        Workout w = new Workout(activity, Quantifier.MINUTES, 90, new GregorianCalendar(2018, 9, 18,22, 15));
        assertFalse(w.isCompleted());
        w.setCompleted(true);
        assertTrue(w.isCompleted());
    }

}