package charstars.uscfit;

import java.util.Date;

import charstars.uscfit.RootObjects.Badge;

// ADD TO THIS BY CALLING BadgeDatabase.badgeCollection.add("badge name");
public class BadgeFactory extends android.app.Activity
{
    //generates badges of different sizes by passing in name/desc and date

    public static Badge generateBronze(String name, Date d){
        return new Badge(R.drawable.bronze, name, d.toString());
    }

    public static Badge generateSilver(String name, Date d){
        return new Badge(R.drawable.silver, name, d.toString());
    }

    public static Badge generateGold(String name, Date d){
        return new Badge(R.drawable.gold, name, d.toString());
    }

    public static Badge generateSteps(String name, Date d){
        return new Badge(R.drawable.steps1, name, d.toString());
    }
    public static Badge generateDays(String name, Date d){
        return new Badge(R.drawable.day, name, d.toString());
    }

}
