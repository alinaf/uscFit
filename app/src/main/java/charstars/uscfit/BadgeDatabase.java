package charstars.uscfit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BadgeDatabase
{
    public static List<Badge> badgeCollection = new ArrayList<Badge>()
    {{
        add(new Badge("7 Day Workout", "Feb 05, 2018"));
        add(new Badge("20 Day Workout", "Feb 08, 2018"));
        add(new Badge("Ran 2 miles", "March 20, 2018"));
    }};

}
