package charstars.uscfit;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ADD TO THIS BY CALLING BadgeDatabase.badgeCollection.add("badge name");
public class BadgeDatabase
{
    public static ArrayList<String> badgeCollection = new ArrayList<String>()
    {{
        add("7 Day Workout");
        add("20 Day Workout");
        add("Ran 2 miles");
    }};
//    public static List<Badge> badgeCollection = new ArrayList<Badge>()
//    {{
//        add(new Badge("7 Day Workout", "Feb 05, 2018"));
//        add(new Badge("20 Day Workout", "Feb 08, 2018"));
//        add(new Badge("Ran 2 miles", "March 20, 2018"));
//    }};

    //ArrayList<String> list = getIntent().getStringArrayListExtra("name");


}
