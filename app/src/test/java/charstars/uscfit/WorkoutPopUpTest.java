package charstars.uscfit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import charstars.uscfit.DataHandlers.UpdateWorkouts;

import static org.junit.Assert.*;

public class WorkoutPopUpTest {

    WorkoutPopUp wpu;

    @Before
    public void setUp() throws Exception {
        wpu = new WorkoutPopUp();
    }

    @Test
    public void addActivityNotInSpinner() {
        Activity a = new Activity("Kickball", 200);
        assertTrue(wpu.addWorkout(a, Quantifier.MINUTES, 60, 2018, 11, 17, 12, 45));
    }

    @Test
    public void addWorkoutEmptyDate() {
        Activity a = new Activity("Running", 472);
        assertFalse(wpu.addWorkout(a,Quantifier.MINUTES, 60, 0, 0, 0, -1, -1));
        assertFalse(wpu.addWorkout(a,Quantifier.MILES, 60,2018, 11, 17, -1, -1));
        assertFalse(wpu.addWorkout(a,Quantifier.MINUTES, 60, 0, 0, 0, 12, 30));
    }

    @Test
    public void addWorkoutInvalidDate() {
        Activity a = new Activity("Running", 472);
        assertFalse(wpu.addWorkout(a, Quantifier.MINUTES, 60, 1950, 11, 15, 12, 0));
        assertFalse(wpu.addWorkout(a,Quantifier.MINUTES, 60, 2019, 14, 15, 12, 0));
        assertFalse(wpu.addWorkout(a,Quantifier.MINUTES, 60, 2019, 11, 35, 12, 0));
        assertFalse(wpu.addWorkout(a,Quantifier.MINUTES, 60, 2019, 11, 15, 27, 0));
        assertFalse(wpu.addWorkout(a,Quantifier.MINUTES, 60, 2019, 11, 15, 12, 67));
    }

    public void addWorkoutDifferentQuants() {
        Activity a = new Activity("Running", 472);
        assertTrue(wpu.addWorkout(a, Quantifier.MINUTES, 60, 1950, 11, 15, 12, 0));
        assertTrue(wpu.addWorkout(a,Quantifier.MILES, 10, 2019, 14, 15, 12, 0));
    }

}