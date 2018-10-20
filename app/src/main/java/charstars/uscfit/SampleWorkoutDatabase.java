package charstars.uscfit;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleWorkoutDatabase {

    public static List<Workout> defaultWorkouts = new ArrayList<Workout>() {{
        add(new Workout(new Activity("Basketball", 100), Quantifier.MINUTES, 60, new GregorianCalendar(2018, 11, 17, 6, 30)));
        add(new Workout(new Activity("Gardening", 50), Quantifier.MINUTES, 60, new GregorianCalendar(2018, 11, 17, 6, 30)));
        add(new Workout(new Activity("Baseball", 10), Quantifier.MINUTES, 60, new GregorianCalendar(2018, 11, 17, 6, 30)));
        add(new Workout(new Activity("Swimming", 50), Quantifier.MILES, 1, new GregorianCalendar(2018, 11, 17, 6, 30)));
    }};

}