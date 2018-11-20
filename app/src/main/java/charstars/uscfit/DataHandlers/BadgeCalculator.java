package charstars.uscfit.DataHandlers;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import charstars.uscfit.Goal;
import charstars.uscfit.NotificationHelper;
import charstars.uscfit.RootObjects.Badge;
import charstars.uscfit.DatabaseHandlers.BadgeDatabaseManager;
import charstars.uscfit.BadgeFactory;
import charstars.uscfit.RootObjects.Quantifier;

public class BadgeCalculator extends AppCompatActivity
{
    static BadgeDatabaseManager bm = BadgeDatabaseManager.getInstance();
    //takes in a name/description, quanitity of goal completed, and date of completion
    //generates correct badge and adds to database
    public static void addBadge(String b, int quant, Date date)
    {

        Log.d(("Adding Badge"), b+" "+quant+" "+date);

        Badge bb = null;
        if(quant>=10){
            bb = BadgeFactory.generateGold(b, date);

        }else if(quant>=5){
            bb = BadgeFactory.generateSilver(b, date);


        }else {

            bb = BadgeFactory.generateBronze(b, date);


        }
        BadgeDatabaseManager.getInstance().addBadge(bb);
//        Toast.makeText(BadgeCalculator.this, "Goal Completed! New Badge Added.",
//                Toast.LENGTH_SHORT).show();
    }
    public static NotificationHelper mNotificationHelper;

    public static void addBadge(Goal b, Date d)
    {
        Badge bb = generateBadge(b, d);
        if(bb!=null)
        {
            mNotificationHelper = new NotificationHelper(BadgeCalculator.mNotificationHelper);
            sendNotification("Badge earned!", "You have earned a badge: " + bb.getmName());
            BadgeDatabaseManager.getInstance().addBadge(bb);
        }
//        Toast.makeText(BadgeCalculator.this, "Goal Completed! New Badge Added.",
//                Toast.LENGTH_SHORT).show();
    }

    public static void sendNotification(String title, String message)
    {
        NotificationCompat.Builder nb = mNotificationHelper.getChannelNotification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build());
    }

    private static Badge generateBadge(Goal b, Date d) {
        if(b == null){
            return null;
        }
        String q = "";
        if(b.getGoalNum()==1){
            q = b.getQuantifier().substring(0, b.getQuantifier().length()-1);
        }else{
            q = b.getQuantifier();
        }
        String desc = "Completed: " + b.getDescription() + " " + b.getGoalNum() + " " + q;
        if(b.getQuantifier().equals(Quantifier.STEPS.getMeasurement())){
            return BadgeFactory.generateSteps(desc, d);
        }
        if(b.getQuantifier().equals(Quantifier.DAYS.getMeasurement())){
            return BadgeFactory.generateDays(desc, d);
        }
        if(b.getQuantifier().equals(Quantifier.MILES.getMeasurement())){
            Log.d("QUANT", "MILES");
            if(b.getGoalNum()>=20){
                return BadgeFactory.generateGold(desc, d);
            }else if(b.getGoalNum()>=10){
                return BadgeFactory.generateSilver(desc, d);
            }else{
                return BadgeFactory.generateBronze(desc, d);
            }

        }
        if(b.getGoalNum()>=60){
            return BadgeFactory.generateGold(desc, d);
        }else if(b.getGoalNum()>=30){
            return BadgeFactory.generateSilver(desc, d);
        }else{
            return BadgeFactory.generateBronze(desc, d);
        }

    }

    public static void removeBadge(Badge b){
        bm.removeBadge(b);
    }

    public static ArrayList<Badge> getBadges(){
        if(bm==null){
           initDB();
        }
        return bm.getBadges();
    }

    public static void initDB(){
         bm = BadgeDatabaseManager.getInstance();
    }

}
