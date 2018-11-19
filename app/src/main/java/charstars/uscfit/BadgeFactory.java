package charstars.uscfit;

import java.util.Date;

import charstars.uscfit.RootObjects.Badge;

// ADD TO THIS BY CALLING BadgeDatabase.badgeCollection.add("badge name");
public class BadgeFactory extends android.app.Activity
{
    //generates badges of different sizes by passing in name/desc and date

    public static Badge generateFifty(String name, Date d){
        return new Badge(R.drawable.small_trophy, name, d.toString());
    }

    public static Badge generateHundred(String name, Date d){
        return new Badge(R.drawable.med_trophy, name, d.toString());
    }

    public static Badge generateFiveHundred(String name, Date d){
        return new Badge(R.drawable.big_trophy, name, d.toString());
    }

    public static Badge generateSteps(String name, Date d){
        return new Badge(R.drawable.big_trophy, name, d.toString());
    }
    public static Badge generateDays(String name, Date d){
        return new Badge(R.drawable.big_trophy, name, d.toString());
    }

}
