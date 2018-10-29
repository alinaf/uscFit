package charstars.uscfit;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// ADD TO THIS BY CALLING BadgeDatabase.badgeCollection.add("badge name");
public class BadgeFactory extends android.app.Activity
{
    public static Badge generateFifty(String name, Date d){
        return new Badge(1, name, "TODAY");
    }

    public static Badge generateHundred(String name, Date d){
        return new Badge(2, name, "TODAY");
    }

    public static Badge generateFiveHundred(String name, Date d){
        return new Badge(3, name, "TODAY");
    }

    public static Badge generateThousand(String name, Date d){
        return new Badge(4, name, "TODAY");
    }

}
